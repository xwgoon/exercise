import { check, validationResult } from "express-validator/check";
import validator from "validator";
import fs from "fs";
import _ from "lodash";
import { sanitize } from "express-validator/filter";
import { GetHashVal } from "rclink/lib/crypto";
import serverInnerLogger from "../log/serverInnerLog";

const nonEmptyErrorMsg = field => `The ${field} is required and should not be empty`;

// Common fields validation for every request 
const commonFieldValidators = [];

// Fields validation for GET /copRegs (copReg list query)
const CopRegListQueryValidators = [
    check("operationTime").custom((value) => {
        if (value
            && !validator.isInt(value)
            && !validator.isLength(value, { min: 13, max: 13 })
            && !(parseInt(value[0], 10) < 5)
        ) {
            throw new Error("The operationTime must be an unix epoch timestamp in milli seconds");
        }
        return true;
    }),
    check("status").custom((value) => {
        const statusRange = ["REQUESTED", "REGISTERED", "REJECTED"];
        if (value && ![].indexOf(value)) {
            throw new Error(`The status must be one of ${statusRange}`);
        }
        return true;
    }),
    check("sortBy").custom((value) => {
        if (value && !_.endsWith(value, "_ASC") && !_.endsWith(value, "_DESC")) {
            throw new Error("The sortBy must end with _ASC or with _DESC");
        }
        return true;
    }),
    check("perPage").custom((value) => {
        if (value && !validator.isInt(value) && !_.inRange(parseInt(value, 10), 0, 2 ** 31)) {
            throw new Error("The perPage must be an integer in range[0, 2**31)");
        }
        return true;
    }),
    check("page").custom((value) => {
        if (value && !validator.isInt(value) && !_.inRange(parseInt(value, 10), 0, 2 ** 31)) {
            throw new Error("The page must be an integer in range[0, 2**31)");
        }
        return true;
    }),
].concat(commonFieldValidators);

const timeSanitizers = [
    sanitize("operationTime").toInt(10),
];
const paginationSanitizers = [
    sanitize("perPage").toInt(10),
    sanitize("page").toInt(10),
];
const CopRegListQuerySanitizers = [].concat(timeSanitizers, paginationSanitizers);

const CopRegQueryValidators = [].concat(commonFieldValidators);

// Fields validation for POST /images (image upload request)
const uploadValidators = [
    check("images").not().isEmpty()
        .withMessage(nonEmptyErrorMsg("images")),
    check("hashAlg").not().isEmpty()
        .withMessage(nonEmptyErrorMsg("hashAlg")),
    check("imageHashes").not().isEmpty()
        .withMessage(nonEmptyErrorMsg("imageHashes"))
        .custom((value, { req }) => {
            if (!value) return true; // if imageHashes is undefined, use the previous error message
            let imageHashes;
            try {
                imageHashes = JSON.parse(value);
                if (!_.isArray(imageHashes)) {
                    throw new Error();
                }
                _(imageHashes).each((hash) => {
                    if (!_.isString(hash)) throw new Error();
                });
            } catch (e) {
                throw new Error(`The imageHashes "${value}" is a bad json string of Array[String]`);
            }
            if (!req.body.images || imageHashes.length !== req.body.images.length) {
                throw new Error("The images and imageHashes contain different element count");
            }
            _(imageHashes).each((element, index) => {
                if (!validator.isHash(element, req.body.hashAlg)) {
                    throw new Error(`The image hash "${element}" is not a hash of algorithm ${req.body.hashAlg}`);
                }
                const hash = GetHashVal({ data: fs.readFileSync(req.body.images[index].path), alg: req.body.hashAlg }).toString("hex");
                if (hash !== element) {
                    throw new Error(`The image hash "${element}" mismatched the image uploaded`);
                }
            });
            return true;
        }),
].concat(commonFieldValidators);

const ImageHashesSanitizer = sanitize("imageHashes").customSanitizer(value => JSON.parse(value));

// Fields validation for GET /images/:imageId/download (image download request)
const downloadValidators = [].concat(commonFieldValidators);
const downloadSanitizer = [
    sanitize("width").customSanitizer((value) => {
        if (value) {
            if (value === "auto" || value.endsWith("%")) return 680;
            return parseInt(value, 10);
        }
        return null;
    }),
    sanitize("height").customSanitizer((value) => {
        if (value) {
            if (value === "auto" || value.startsWith("%")) return 570;
            return parseInt(value, 10);
        }
        return null;
    }),
];

const AuthLoginValidators = [
    check("userId").not().isEmpty()
        .withMessage(nonEmptyErrorMsg("userId")),
    check("certName").not().isEmpty()
        .withMessage(nonEmptyErrorMsg("certName")),
    check("signature")
        .custom((value, { req }) => {
            if (value && !req.body.signAlg) {
                throw new Error("the signAlg should be given when signature is given");
            }
            return true;
        }),
];

const AuthRegisterValidators = [
    check("creditCode").not().isEmpty()
        .withMessage(nonEmptyErrorMsg("creditCode")),
    check("username").not().isEmpty()
        .withMessage(nonEmptyErrorMsg("username")),
    check("phone").not().isEmpty()
        .withMessage(nonEmptyErrorMsg("phone")),
    check("cert").not().isEmpty()
        .withMessage(nonEmptyErrorMsg("cert")),
];

const fieldErrorHandler = (req, res, next) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        serverInnerLogger.error(`Not pass the request params validation for request ${req.method}-${req.originalUrl}`, "send the result message");
        console.log(errors.array());
        res.status(400).json({
            message: "Bad request, with error(s)",
            errors: errors.array(),
        });
    } else {
        serverInnerLogger.info(`Pass the request params validation for request ${req.method}-${req.originalUrl}`);
        next();
    }
};

const validateHandlers = {
    ImageUpload: uploadValidators.concat([fieldErrorHandler,
        ImageHashesSanitizer]),
    ImageDownload: downloadValidators.concat([fieldErrorHandler,
        downloadSanitizer]),
    CopRegListQuery:
        CopRegListQueryValidators.concat([fieldErrorHandler,
            ...CopRegListQuerySanitizers]),
    CopRegQuery:
        CopRegQueryValidators.concat([fieldErrorHandler]),
    authLogin:
        AuthLoginValidators,
    authRegister:
        AuthRegisterValidators,
    default:
        (req, res, next) => next(),
};

export default validateHandlers;
