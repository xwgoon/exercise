import request from "supertest";
import path from "path";
import fs from "fs-extra";
import config from "config";
import { GetHashVal } from "rclink/lib/crypto";
import rimraf from "rimraf";
import app, { server } from "../../src/server";
import { createSomeTestUsers, clearTestRecords, clearTestUsers } from "../helpers/prismaOps";
import uploadOneImage from "../helpers/uploadImage";

describe("Images api test", () => {
    const hashAlg = "sha1";
    let userId;
    const agent = request(app);
    const baseUrl = "/images";
    const testDataIdentifier = "test";

    const testFilesDir = config.get("File.testFilesDir");
    if (!fs.existsSync(testFilesDir)
        || fs.readdirSync(testFilesDir).length === 0) {
        throw Error("You should create 'testFiles' directory firstly, and put your files into it.");
    }

    const items = fs.readdirSync(testFilesDir);

    const testUploadsDir = config.get("File.fileUploadDir");
    rimraf.sync(testUploadsDir);


    beforeAll(() => createSomeTestUsers(1).then((users) => {
        userId = users[0].userID;
        return 0;
    }));

    afterAll(async () => {
        await clearTestUsers();
        server.close();
    });

    afterEach(async () => clearTestRecords());

    // test.only("clear", () => {});

    test("Should success when uploading a single image", () => {
        const url = baseUrl;
        let imageHashes = GetHashVal({ data: fs.readFileSync(path.join(testFilesDir, items[0])), alg: hashAlg }).toString("hex");
        imageHashes = JSON.stringify([imageHashes]);
        const description = `${testDataIdentifier} First import rule reported by eslint`;
        const body = {
            imageHashes, hashAlg, userId, description,
        };
        return agent
            .post(url)
            .field(body)
            .attach("images", path.join(testFilesDir, items[0]))
            .then((res) => {
                expect(res.status).toBe(201);
                expect(res.body.message).toBe("Image(s) uploaded successfully");
                expect(res.body.count).toBe(1);
                expect(res.body.imageMetas).toBeDefined();
                expect(res.body.imageMetas.length).toBe(1);
                return 0;
            })
            .catch(e => console.error(e));
    });

    test("Should success when uploading multi images", () => {
        const url = baseUrl;
        let imageHashes = [];
        const interVal = agent.post(url);
        items.slice(1).forEach((imagePath) => {
            imageHashes.push(GetHashVal({
                data: fs.readFileSync(path.join(testFilesDir, imagePath)), alg: hashAlg,
            }).toString("hex"));
            interVal.attach("images", path.join(testFilesDir, imagePath));
        });
        imageHashes = JSON.stringify(imageHashes);
        const description = `${testDataIdentifier} My screen capture images`;
        const body = {
            imageHashes, hashAlg, userId, description,
        };
        return interVal
            .field(body)
            .then((res) => {
                expect(res.status).toBe(201);
                expect(res.body.message).toBe("Image(s) uploaded successfully");
                expect(res.body.count).toBe(items.length - 1);
                expect(res.body.imageMetas).toBeDefined();
                expect(res.body.imageMetas.length).toBe(items.length - 1);
                return 0;
            }).catch(e => console.error(e));
    });

    test("Should success when downloading a image", async () => {
        const imageHash = await uploadOneImage({
            agent, filePath: path.join(testFilesDir, items[0]), userId, testDataIdentifier,
        });
        const url = `${baseUrl}/${imageHash}/download`;
        return agent
            .get(url)
            .query({ userId })
            .then((res) => {
                expect(res.status).toBe(200);
                expect(res.body).toEqual(fs.readFileSync(path.join(testFilesDir, items[0])));
                return 0;
            }).catch(e => console.error(e));
    });
});
