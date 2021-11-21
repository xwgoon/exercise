import config from "config";
import path from "path";
import sharp from "sharp";
import fs from "fs";
import { downloadImage } from "../images/imageAccess";
import serverInnerLogger from "../log/serverInnerLog";

const downloadHandler = (req, res) => {
    const imageHash = req.params.imageId;
    const { width, height } = req.query;
    downloadImage(imageHash).then((storagePath) => {
        const imagePath = path.join(config.get("File.fileStorageDir"), storagePath);
        const resizedPath = `resized-${imageHash}`;
        sharp(imagePath).resize(width, height, { fit: "outside" }).toFile(resizedPath)
            .then(() => {
            res.set({ "Cache-Control": "public, max-age=31536000" });
            res.download(resizedPath, (err) => {
                fs.unlinkSync(resizedPath);
                if (err) {
                    serverInnerLogger.error(`Unsuccessfully download image with the path: ${imagePath}, error: `, err);
                } else {
                    serverInnerLogger.info(`Successfully download image with the path: ${imagePath}, send the data to client`);
                }
            });
            return 0;
        })
            .catch((err) => {
                serverInnerLogger.error("Unsuccessfully download image, error: ", err, "send the result message");
                if (err.message === `Not found imageMeta for the image: ${req.params.imageId}`) {
                    res.status(404)
                        .json({ message: err.message })
                        .end();
                } else {
                    res.status(500)
                        .json({ message: err.message })
                        .end();
                }
            });
        return 0;
    });
};

export default downloadHandler;
