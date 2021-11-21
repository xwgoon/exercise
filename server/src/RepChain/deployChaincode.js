import cfg from "config";
import fs from "fs-extra";
import path from "path";
import { Transaction } from "rclink";
import { prisma } from "../db/generated/prisma-client";
import serverInnerLogger from "../log/serverInnerLog";
import signAndSendTx from "./signAndSendTransaction";

const deployChaincode = async () => {
    const { chaincodeName, chaincodeVersion } = cfg.get("RepChain");
    const latestChaincodeVersion = await prisma.chaincodeIDs({
        where: { chaincodeName },
        orderBy: "version_DESC",
    }).then((res) => {
        if (res) return res[0].version;
        return -1;
    });
    if (latestChaincodeVersion < chaincodeVersion) { // 部署更新的合约，覆盖已部署的最新版本
        serverInnerLogger.info(`Start to deploy the chaincode(name: ${chaincodeName}, version: ${chaincodeVersion})`);
        const chaincodeContent = fs.readFileSync(path.join(__dirname, "copyrightRegisterChaincode.scala")).toString();
        const chaincodeLegalProse = fs.readFileSync(path.join(__dirname, "copyrightRegisterChaincodeLegalProse")).toString();
        const txArgs = {
            type: "CHAINCODE_DEPLOY",
            chaincodeName,
            chaincodeVersion,
            chaincodeDeployParams: {
                timeout: 1000,
                codePackage: chaincodeContent,
                legalProse: chaincodeLegalProse,
                codeLanguageType: "CODE_SCALA",
            },
        };
        const tx = new Transaction(txArgs);
        signAndSendTx(tx)
            .then((res) => {
                if (res.err) {
                    const errMsg1 = `Failed to deploy the chaincode(name: ${chaincodeName}, version: ${chaincodeVersion}), `;
                    const errMsg2 = `with error message from RepChain: ${res.err}`;
                    serverInnerLogger.error(`${errMsg1}${errMsg2}`);
                } else {
                    const msg1 = `Deployed the chaincode(name: ${chaincodeName}, version: ${chaincodeVersion}), `;
                    const msg2 = "without any error message from RepChain";
                    serverInnerLogger.info(`${msg1}${msg2}`);
                }
                return 0;
            }).catch((err) => {
                const errMsg1 = `Failed to deploy the chaincode(name: ${chaincodeName}, version: ${chaincodeVersion}), `;
                const errMsg2 = `with error message ${err}`;
                serverInnerLogger.error(`${errMsg1}${errMsg2}`);
            });
    }
};

export default deployChaincode;
