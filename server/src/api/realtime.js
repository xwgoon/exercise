import { server as WebSocketServer, router as WebSocketRouter } from "websocket";
import serverInnerLogger from "../log/serverInnerLog";

const wsConnections = [];

export const getWSConnections = () => wsConnections;

const setWebSocketServer = (httpServer) => {
    const wsServer = new WebSocketServer({ httpServer });
    const wsRouter = new WebSocketRouter();
    wsRouter.attachServer(wsServer);
    serverInnerLogger.info("Bind the webscoket server to the http service server");

    const originIsAllowed = (origin) => {
        if (origin) return true;
        return false;
    };
    const validateHanlder = async (request) => {
        let reason;
        let isRejected = false;
        if (!originIsAllowed(request.origin)) {
            reason = `Bad origin: ${request.origin}`;
            request.reject(400, reason);
            isRejected = true;
        }
        if (isRejected) {
            serverInnerLogger.warn(`Rejected a websocket connection for the reason: ${reason}`);
        }
    };

    wsRouter.mount("/copRegs", "echo-protocol", async (request) => {
        await validateHanlder(request);
        const connection = request.accept(request.origin);
        serverInnerLogger.info(`Accepted a new websocket connection(peer: ${connection.remoteAddress})`);
        const connIndex = wsConnections.push(connection) - 1;
        serverInnerLogger.info(`Added the connection(peer: ${connection.remoteAddress}) to the websocket connection array with index: ${connIndex}`);
        connection.on("message", (message) => {
            if (message.type === "utf8") {
                connection.sendUTF(message.utf8Data);
                serverInnerLogger.info(`Reveived message: ${message.utf8Data} from the websocket connection(peer: ${connection.remoteAddress})`);
            } else {
                serverInnerLogger.info(`Bad websocket message type: ${message.type}`);
            }
        });
        connection.on("close", (reasonCode, description) => {
            serverInnerLogger.info(`The webscoket connection(peer: ${connection.remoteAddress}) closed for reason:${reasonCode}, ${description}`);
            wsConnections.splice(connIndex, 1);
            serverInnerLogger.info(`Removed the websocket connection(peer: ${connection.remoteAddress}) whose index is ${connIndex} from the websocket connection array`);
        });
        connection.on("error", (err) => {
            serverInnerLogger.warn(`Error for the websocket connection(peer: ${connection.remoteAddress}): `, err);
            wsConnections.splice(connIndex, 1);
            serverInnerLogger.info(`Removed the websocket connection(peer: ${connection.remoteAddress}) whose index is ${connIndex} from the websocket connection array`);
        });
    });
};

export default setWebSocketServer;
