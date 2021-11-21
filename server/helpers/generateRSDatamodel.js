import RepchainSynchronizer from "repchain-synchronizer";
import fs from "fs-extra";
import path from "path";

const generateRSDatamodel = () => {
    const targetDir = path.join(__dirname, "..", "src", "db", "generated");
    const targetPath = path.join(targetDir, "rsDatamodel.gql");
    const repchainDatamodel = RepchainSynchronizer.getDatamodel();
    fs.ensureDirSync(targetDir);
    fs.writeFileSync(targetPath, repchainDatamodel);
};

generateRSDatamodel();
