import config from "config";
import path from "path";
import { prisma } from "../db/generated/prisma-client";

export const getImageMeta = hash => prisma.imageMeta({ hash });

export const saveImageMeta = ({
    hash, storagePath, url,
}) => {
    const storageRelativePath = storagePath.replace(new RegExp(`${config.get("File.fileStorageDir")}${path.sep}`), "");
    return prisma.updateImageMeta({
        where: {
            hash,
        },
        data: {
            storagePath: storageRelativePath,
            url,
        },
    });
};
