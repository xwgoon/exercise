import config from "config";
import { RestAPI } from "rclink";
import fs from "fs-extra";

const signAndSendTx = (tx) => {
    const {
        prvKeyFilePath,
        prvKeyPassword,
        pubKeyFilePath,
        alg,
        creditCode,
        certName,
    } = config.get("ServerCrypto");

    const txSignedBytes = tx.sign({
        prvKey: fs.readFileSync(prvKeyFilePath).toString(),
        pubKey: fs.readFileSync(pubKeyFilePath).toString(),
        pass: prvKeyPassword,
        alg,
        creditCode,
        certName,
    });

    const { server, port } = config.get("RepChain");

    const blockchainRestAPIClient = new RestAPI(`${server}:${port}`);

    return blockchainRestAPIClient.sendTransaction(txSignedBytes);
};

export default signAndSendTx;
