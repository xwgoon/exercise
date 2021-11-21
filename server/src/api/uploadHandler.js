import multer from "multer";
import config from "config";
import fs from "fs-extra";
import uuidv1 from "uuid/v1"; 
import serverInnerLogger from "../log/serverInnerLog";

const storage = multer.diskStorage({
    destination: (req, file, cb) => {
        const des = config.get("File.fileUploadDir");
        fs.ensureDirSync(des);
        cb(null, des);
    },
    filename: (req, file, cb) => {
        cb(null, `${file.originalname}-${uuidv1()}`);
    },
});

const limits = {
    fields: config.get("File.uploadLimits.fields"),
    fileSize: config.get("File.uploadLimits.fileSize"),
    files: config.get("File.uploadLimits.files"),
};

const upload = multer({ storage, limits }).array("images");

const uploadHandler = (req, res, next) => {
    upload(req, res, (err) => {
        if (err) {
            serverInnerLogger.error("Failed when uploading image(s), error: ", err);
            res.status(400)
                .json({ message: `Bad request, ${err.message}` })
                .end();
        } else {
            serverInnerLogger.info(`Temporarily save ${req.files ? req.files.length : 0} image(s) into the directory ${config.get("File.fileUploadDir")}`);
            // special workaround for images validator with express-validator
            req.body.images = req.files; 
            next();
        }
    });
};

export default uploadHandler;
