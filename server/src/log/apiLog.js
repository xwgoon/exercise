import config from "config";
import morgan from "morgan";
import fs from "fs-extra";
import path from "path";

const { 
    enabled, level, format, logDir, showErrorOnConsole,
} = config.get("Log.api");

const formats = {
    combined: ":date[iso] CRBB-rest-api :status :remote-addr - :remote-user [:date[clf]] \":method :url HTTP/:http-version\" :res[content-length] \":referrer\" \":user-agent\"",
    dev: ":date[iso] CRBB-rest-api :status :method :url :response-time ms - :res[content-length]",
};

const setLogger = (app) => {
    if (enabled) {
        fs.ensureDirSync(logDir);
        const apiLogger = morgan(formats[format], {
            skip: (req, res) => {
                switch (level) {
                    case "common":
                        return res.statusCode < 200;
                    case "clientError":
                        return res.statusCode < 400;
                    case "serverError":
                        return res.statusCode < 500;   
                    default:
                        return res.statusCode < 100;
                }
            },
            stream: fs.createWriteStream(path.join(logDir, "access.log"), { flags: "a" }),
        });
        app.use(apiLogger);
    }
    if (showErrorOnConsole) {
        const apiLogger = morgan(formats[format], {
            skip: (req, res) => res.statusCode < 400,
        });
        app.use(apiLogger);
    }
};

export default setLogger;
