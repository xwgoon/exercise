import { prisma } from "../db/generated/prisma-client";
import { COMBINATION_DELIMITER } from "../const";

const getPubKey = (userID, certName) => {
    const creditCodeCombineName = `${userID}${COMBINATION_DELIMITER}${certName}`;
    return prisma.cert({ creditCodeCombineName }).then((cert) => {
        if (!cert) {
            return { pubKey: null, status: null };
        }
        return { pubKey: cert.certPEM, status: cert.status };
    });
};
export default getPubKey;
