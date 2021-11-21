/*
 * Copyright 2019 Linkel Technology Co., Ltd, Beijing
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BA SIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
import express from "express";
import bodyParser from "body-parser";
import http from "http";
import setRoute from "./route";
import setLogger from "./log/apiLog";
import serverInnerLogger from "./log/serverInnerLog";
import deployChaincode from "./RepChain/deployChaincode";
import setWebSocketServer from "./api/realtime";
import { synchronizeRepchain } from "./RepChain/repchainSynchronize";

deployChaincode();

const app = express();
const port = process.env.PORT || 5000;
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

setLogger(app);

setRoute(app);

export const server = http.createServer(app);
setWebSocketServer(server);

// 同步RepChain区块链数据
synchronizeRepchain();

server.listen(port, () => console.log(`Listening on port ${port}`));
serverInnerLogger.info(`Started the crbb server with express on port ${port}`);

export default app;
