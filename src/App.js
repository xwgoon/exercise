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
import React, { Component } from "react";
import { Admin, Resource } from "react-admin/lib";

import TransIcon from "@material-ui/icons/Receipt";
import BlockIcon from "@material-ui/icons/ViewColumn";
import CopyrightIcon from "@material-ui/icons/Copyright";
import { includes } from "lodash";
import polyglotI18nProvider from "ra-i18n-polyglot";
import { Route } from "react-router-dom";
import { TransList, TransShow } from "./components/transaction";
import { BlockList, BlockShow } from "./components/block";
import { CopRegCreate, CopRegList, CopRegShow } from "./components/copReg";

import Dashboard from "./components/dashboard/Dashboard";

import zhMessages from "./i18n/cn";
import enMessages from "./i18n/en";

// import  dataProvider from './dataprovider/data-provider'
import buildGraphQLProvider from "./adaptator";
import copRegServerRestProvider from "./dataprovider/ra-data-copRegServer-rest";
import addUploadCapabilities from "./dataprovider/addUploadFeature";
import createRealtimeSaga from "./createRealtimeSaga";
import settings from "./settings";
import authProvider from "./auth/authProvider";
import Login from "./auth/login";
import Register from "./auth/register";
import LogoutButton from "./auth/logout";

const messages = {
    zh: zhMessages,
    en: enMessages,
};
const i18nProvider = polyglotI18nProvider(
    locale => messages[locale],
    "zh",
);

const customRoutes = [
    <Route key="register" exact path="/register" component={Register} noLayout />,
];

class App extends Component {
    constructor() {
        super();
        this.state = { dataProvider: null };
    }

    componentDidMount() {
        buildGraphQLProvider({
            clientOptions: { uri: settings.Prisma.endpoint },
        }).then((dataProvider) => {
            const upDataProvider = addUploadCapabilities(dataProvider);
            const realTimeSaga = createRealtimeSaga(upDataProvider);
            return this.setState({
                customSagas: realTimeSaga,
                dataProvider: (type, resource, params) => {
                    if (includes(["copRegs", "images"], resource)) { return copRegServerRestProvider(type, resource, params); }
                    return upDataProvider(type, resource, params);
                },
            });
        });
    }

    render() {
        const { dataProvider, customSagas, title } = this.state;

        if (!dataProvider) {
            return <div id="data-provider-loading">Loading</div>;
        }

        return (
            <Admin authProvider={authProvider} dataProvider={dataProvider}
                loginPage={Login}
                logoutButton={LogoutButton}
                title={title}
                customSagas={[customSagas]}
                customRoutes={customRoutes}
                i18nProvider={i18nProvider}
                dashboard={Dashboard}
            >
                <Resource name="Block" list={BlockList} show={BlockShow} icon={BlockIcon} />
                <Resource name="Transaction" list={TransList} show={TransShow} icon={TransIcon} />
                <Resource name="copRegs" list={CopRegList} show={CopRegShow} create={CopRegCreate} icon={CopyrightIcon} />
                <Resource name="images" />
            </Admin>
        );
    }
}
export default App;
