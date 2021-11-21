
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
import { Responsive, Title } from "react-admin";
import {
    Copyright as CopyrightIcon,
    Receipt as TransIcon,
    ViewColumn as BlockIcon,
} from "@material-ui/icons";
import { GraphQLClient } from "graphql-request";
import DashItem from "./dashItem";
import settings from "../../settings";

const styles = {
    flex: { display: "flex" },
    flexColumn: { display: "flex", flexDirection: "column" },
    leftCol: { flex: 1, marginRight: "1em" },
    rightCol: { flex: 1, marginLeft: "1em" },
    singleCol: { marginTop: "2em", marginBottom: "2em" },
};

class Dashboard extends Component {
    constructor() {
        super();
        this.state = {};
    }

    componentDidMount() {
        const graphqlClient = new GraphQLClient(`${settings.Prisma.endpoint}`);
        graphqlClient.request(`
            {
                copyrightRegistrationsConnection{
                    aggregate {
                        count
                    }
                }
            }
        `).then((res) => {
            this.setState({
                copyrightCount: res.copyrightRegistrationsConnection.aggregate.count,
            });
            return 0;
        });
        graphqlClient.request(`
            {
                transactionsConnection{
                    aggregate {
                        count
                    }
                }
            }
        `).then((res) => {
            this.setState({
                transCount: res.transactionsConnection.aggregate.count,
            });
            return 0;
        });
        graphqlClient.request(`
            {
                blocksConnection{
                    aggregate {
                        count
                    }
                }
            }
        `).then((res) => {
            this.setState({
                blockCount: res.blocksConnection.aggregate.count,
            });
            return 0;
        });
    }

    render() {
        const {
            copyrightCount,
            transCount,
            blockCount,
        } = this.state;
        return (
            <Responsive
                xsmall={
                    <div>
                        <Title title="pos.dashboard.welcome.title" />
                        <div style={styles.flexColumn}>
                            <div style={styles.flex}>
                                <DashItem
                                    icon={CopyrightIcon}
                                    iconBgColor="#4caf50"
                                    title="pos.dashboard.copyright_title"
                                    value={copyrightCount}
                                    to="copRegs"
                                />
                            </div>
                            <div style={styles.flex}>
                                <DashItem
                                    icon={TransIcon}
                                    iconBgColor="#ff9800"
                                    title="pos.dashboard.transaction_title"
                                    value={transCount}
                                    to="Transaction"
                                />
                            </div>
                            <div style={styles.flex}>
                                <DashItem
                                    icon={BlockIcon}
                                    iconBgColor="#f44336"
                                    title="pos.dashboard.block_title"
                                    value={blockCount}
                                    to="Block"
                                />
                            </div>
                        </div>
                    </div>
                }
                medium={
                    <div style={styles.flexColumn}>
                        <Title title="pos.dashboard.welcome.title" />
                        <div style={styles.flex}>
                            <DashItem
                                icon={CopyrightIcon}
                                iconBgColor="#4caf50"
                                title="pos.dashboard.copyright_title"
                                value={copyrightCount}
                                to="copRegs"
                            />
                            <DashItem
                                icon={TransIcon}
                                iconBgColor="#ff9800"
                                title="pos.dashboard.transaction_title"
                                value={transCount}
                                to="Transaction"
                            />
                            <DashItem
                                icon={BlockIcon}
                                iconBgColor="#f44336"
                                title="pos.dashboard.block_title"
                                value={blockCount}
                                to="Block"
                            />
                        </div>
                    </div>
                }
                small={
                    <div style={styles.flex}>
                        <div style={styles.leftCol}>
                            <div style={styles.flex}>
                                <DashItem
                                    icon={CopyrightIcon}
                                    iconBgColor="#4caf50"
                                    title="pos.dashboard.copyright_title"
                                    value={copyrightCount}
                                    to="copRegs"
                                />
                            </div>
                            <div style={styles.flex}>
                                <DashItem
                                    icon={TransIcon}
                                    iconBgColor="#ff9800"
                                    title="pos.dashboard.transaction_title"
                                    value={transCount}
                                    to="Transaction"
                                />
                            </div>
                            <div style={styles.flex}>
                                <DashItem
                                    icon={BlockIcon}
                                    iconBgColor="#f44336"
                                    title="pos.dashboard.block_title"
                                    value={blockCount}
                                    to="Block"
                                />
                            </div>
                        </div>
                    </div>
                }
            />
        );
    }
}

export default Dashboard;
