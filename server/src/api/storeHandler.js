import config from "config";
import path from "path";
import { storeImage } from "../images/imageAccess";
import serverInnerLogger from "../log/serverInnerLog";

const storeHandler = (req, res) => {
    Promise.all(req.files.map((file, index) => storeImage({
        tempPath: file.path,
        storagePath: path.join(config.get("File.fileStorageDir"), file.filename),
        name: file.originalname,
        hashAlg: req.body.hashAlg,
        hash: req.body.imageHashes[index],
        desc: req.body.description,
    })))
        .then((imageMetas) => {
            serverInnerLogger.info(`Uploaded ${imageMetas.length} file(s) successfully, send the result message`);
            res.status(201)
                .json({
                    message: "Image(s) uploaded successfully",
                    count: imageMetas.length,
                    imageMetas,
                })
                .end();
            return 0;
        })
        .catch((err) => {
            serverInnerLogger.error("Uploaded unsuccessfully, error: ", err, "send the result message");
            res.status(500)
                .json({ message: `Error: ${err.message}` })
                .end();
            return 0;
        });
};

export default storeHandler;
