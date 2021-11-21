import cfg from "config";
import { parseWorldStateValue } from "repchain-synchronizer";
import serverInnerLogger from "../log/serverInnerLog";
import { getWSConnections } from "../api/realtime";
import { copRegFragment } from "./copRegs";
import { prisma } from "../db/generated/prisma-client";

class CopRegObserver {
    // eslint-disable-next-line class-methods-use-this
    _isMatch(worldState) {
        const { chaincodeName } = cfg.get("RepChain");
        return worldState.chaincodeName === chaincodeName
            && worldState.functions.length > 0;
    }

    async update(worldState) {
        if (!this._isMatch(worldState)) return;
        const valueBuf = Buffer.from(worldState.value, "base64");
        const copRegJsonStr = parseWorldStateValue("Default", valueBuf);
        const copReg = JSON.parse(copRegJsonStr);

        if (copReg.status === "REQUESTED") { // should create
            const {
                status, hash, hashAlg, name, desc,
            } = copReg;
            // eslint-disable-next-line no-await-in-loop
            const ownerId = await prisma.account({ creditCode: copReg.owner }).id();
            // eslint-disable-next-line no-await-in-loop
            const requestTxId = await prisma.transaction({ txid: copReg.txs.requestTx }).id();
            const create = {
                copRegKey: worldState.key,
                status,
                ownerId,
                requestTxId,
                requestNotes: copReg.notes.requestNotes,
                image: {
                    create: {
                        hash, hashAlg, name, desc,
                    },
                },
            };
            prisma.createCopyrightRegistration(create).$fragment(copRegFragment)
                .then((res) => {
                    const wsConnections = getWSConnections();
                    wsConnections.forEach((wsConnection) => {
                        const copRegStr = JSON.stringify(res);
                        wsConnection.sendUTF(copRegStr);
                        const logMessage1 = `Sent the CopReg(CopRegKey:${res.copRegKey}) created through the websocket connection`;
                        const logMessage2 = `(peer: ${wsConnection.remoteAddress}) to the client`;
                        serverInnerLogger.info(`${logMessage1}${logMessage2}`);
                    });
                    return res;
                });
        } else { // should update
            const update = {
                where: { copRegKey: worldState.key },
                data: {
                    status: copReg.status,
                },
            };
            if (copReg.status === "REGISTERED") {
                // eslint-disable-next-line no-await-in-loop
                const registerTxId = await prisma
                    .transaction({ txid: copReg.txs.registerTx }).id();
                update.data.registerTxId = registerTxId;
                update.data.registerNotes = copReg.notes.registerNotes;
            }
            if (copReg.status === "REJECTED") {
                // eslint-disable-next-line no-await-in-loop
                const rejectTxId = await prisma
                    .transaction({ txid: copReg.txs.rejectTx }).id();
                update.data.rejectTxId = rejectTxId;
                update.data.rejectNotes = copReg.notes.rejectNotes;
            }
            prisma.updateCopyrightRegistration(update).$fragment(copRegFragment)
                .then((res) => {
                    const wsConnections = getWSConnections();
                    wsConnections.forEach((wsConnection) => {
                        const copRegStr = JSON.stringify(res);
                        wsConnection.sendUTF(copRegStr);
                        const logMessage1 = `Sent the CopReg(CopRegKey:${res.copRegKey}) updated through the websocket connection`;
                        const logMessage2 = `(peer: ${wsConnection.remoteAddress}) to the client`;
                        serverInnerLogger.info(`${logMessage1}${logMessage2}`);
                    });
                    return res;
                });
        }
    }
}

export default CopRegObserver;
