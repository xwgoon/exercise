import RepChainSynchronizer from "repchain-synchronizer";
import config from "config";
import { prisma } from "../db/generated/prisma-client";
import serverInnerLogger from "../log/serverInnerLog";
import CopRegObserver from "../copRegs/copRegObserver";

export const repChainSynchronizer = new RepChainSynchronizer({
    url: config.get("RepChain.default.url_api"),
    subscribeUrl: config.get("RepChain.default.url_subscribe"),
    prisma,
    logPath: config.get("Log.repchainSynchronizerLogPath"),
});

export const synchronizeRepchain = () => {
    repChainSynchronizer.subscribeObserver(new CopRegObserver());
    repChainSynchronizer.startSync();
    serverInnerLogger.info("Started the repchain synchronizer");
};
