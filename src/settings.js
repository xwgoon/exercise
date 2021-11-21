/*
 * Copyright  2018 Linkel Technology Co., Ltd, Beijing
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
const settings = {
    RepChain: {
        default: {
            url_api: "http://localhost:8081",
            chaincodeName: "CopyrightRegisterContract",
            chaincodeVersion: 1,
        },
    },
    Prisma: {
        endpoint: "http://localhost:4466",
        url_subscribe: "ws://localhost:4466/",
    },
    Server: {
        wsBaseUrl: "ws://localhost:5000",
    },
};

export default settings;
