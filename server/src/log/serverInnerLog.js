import config from "config";
import { createLogger, format, transports } from "winston";
import path from "path";
import fs from "fs-extra";

const { 
    enabled, level, showErrorOnConsole, logDir, 
} = config.get("Log.serverInner");

const serverInnerLogger = createLogger({
    level,
    format: format.combine(
        format.timestamp(),
        format.errors({ stack: true }),
        format.label({ label: "CRBB-service" }),
        format.printf(info => `${info.timestamp} ${info.label} ${info.level} "${info.message}" ${info.stack ? `stack:{ ${info.stack} }` : ""}`),
    ),
    transports: [
    ],
});

if (enabled) {
    fs.ensureDirSync(logDir);
    const filename = path.join(logDir, "server.log");
    serverInnerLogger.add(new transports.File({ filename }));
    if (showErrorOnConsole) {
        serverInnerLogger.add(new transports.Console({
            format: format.combine(
                format.timestamp(),
                format.colorize({ all: true }),
                format.errors({ stack: true }),
                format.printf(info => `${info.timestamp} ${info.label} ${info.level} "${info.message}" ${info.stack ? `stack:{ ${info.stack} }` : ""}`),
            ),
            level: "error",
        }));
    }
}

export default serverInnerLogger;
