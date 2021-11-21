import { GetHashVal } from "rclink/lib/crypto";
import fs from "fs-extra";

const uploadOneImage = ({
    agent, filePath, userId, testDataIdentifier,
}) => {
    const hashAlg = "sha1";
    const url = "/images";
    let imageHashes = GetHashVal({ data: fs.readFileSync(filePath), alg: hashAlg }).toString("hex");
    imageHashes = JSON.stringify([imageHashes]);
    const body = {
        imageHashes,
        hashAlg,
        userId,
        description: `${testDataIdentifier} upload image`,
    };
        
    return agent.post(url)
        .field(body)
        .attach("images", filePath)
        .then((res) => {
            const { hash } = res.body.imageMetas[0];
            return hash;
        });
};

export default uploadOneImage;
