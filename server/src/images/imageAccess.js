import fs from "fs-extra";
import { saveImageMeta, getImageMeta } from "./imageMeta";
import serverInnerLogger from "../log/serverInnerLog";

export const downloadImage = hash => getImageMeta(hash).then((imageMeta) => {
    if (!imageMeta) {
        return Promise.reject(new Error(`Not found imageMeta for the fileID: ${hash}`));
    }
    serverInnerLogger.info(`Successfully query imageMeta from prisma for the image ${hash}`);
    return imageMeta.storagePath;
});

export const storeImage = ({
    tempPath, storagePath, name, hashAlg, hash, desc,
}) => fs.pathExists(tempPath).then((exists) => {
    if (!exists) {
        return Promise.reject(new Error(`The temp path: "${tempPath}" doesn't exist`));
    }
    fs.move(tempPath, storagePath)
        .then(() => serverInnerLogger.info(`Move file from temp path: ${tempPath} to formal path ${storagePath}`))
        .catch(err => Promise.reject(new Error(`File storage error: ${err.message}`)));
    return saveImageMeta({
        hash, hashAlg, name, storagePath, desc, url: `/images/${hash}/download`,
    });
}).catch(e => serverInnerLogger.error(e));
