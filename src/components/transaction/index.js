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
import React from "react";

import {
    ShowController,
    ShowView,
    SelectInput,
    TabbedShowLayout,
    Tab, RadioButtonGroupInput,
    FormTab, ReferenceInput,
    TabbedForm, FormDataConsumer,
    Filter,
    DateField,
    Responsive,
    SimpleList,
    List,
    Create,
    Datagrid,
    TextField,
    RichTextField,
    BooleanField,
    NumberField,
    ChipField,
    ReferenceField,
    ShowButton,
    LongTextInput,
    TextInput,
    NumberInput,
    ArrayInput,
    SimpleFormIterator,
    required,
} from "react-admin/lib";

import CodeField from "./CodeField";
import ArgField from "./ArgField";

const TransFilter = props => (
    <Filter {...props}>
        <TextInput label="txid" source="txid_contains" alwaysOn />
    </Filter>
);

export const TransList = props => (
    <List
        {...props}
        filters={<TransFilter />}
        sort={{ field: "timestamp", order: "DESC" }}
        bulkActionButtons={false}
    >
        <Responsive
            small={
                <SimpleList
                    primaryText={record => record.txid}
                    secondaryText={record => record.block.id}
                    tertiaryText={record => new Date(record.timestamp).toLocaleDateString()}
                    linkType="show"
                />
            }
            medium={
                <Datagrid>
                    <TextField source="txid" />
                    <DateField source="timestamp" showTime options={{ hour12: false }} />
                    <ChipField source="type" />
                    <TextField source="chaincodeID.chaincodeName" sortable={false} />
                    <ShowButton />
                </Datagrid>
            }
        />
    </List>
);

const TransTitle = ({ record }) => <span> {record ? `${record.txid}` : ""}</span>;

export const TransShow = props => (
    <ShowController title={<TransTitle />} {...props}>
        {
            controllerProps => <ShowView {...props} {...controllerProps}>
                <TabbedShowLayout>
                    <Tab label="resources.Transaction.tabs.tab1">
                        <TextField source="id" />
                        <TextField source="txid" />
                        <TextField source="orderInBlock" />
                        <ReferenceField label="所属区块哈希" source="block.id" reference="Block" linkType="show">
                            <TextField source="hash" style={{ wordBreak: "break-all" }} />
                        </ReferenceField>
                        <TextField source="signature.signature" style={{ wordBreak: "break-all" }} />
                        <TextField source="signature.certID.creditCode" />
                        <TextField source="signature.certID.certName" />
                        <DateField source="timestamp" showTime options={{ hour12: false }} />
                    </Tab>
                    <Tab label="resources.Transaction.tabs.tab2">
                        <TextField source="type" />
                        <TextField source="chaincodeID.chaincodeName" />
                        <NumberField source="chaincodeID.version" />
                        {
                            controllerProps.record && controllerProps.record.type === "CHAINCODE_DEPLOY"
                            && <CodeField
                                source="chaincodeDeployParams.codePackage"
                                language={
                                    controllerProps.record.chaincodeDeployParams.codeLanguageType.substring(
                                        controllerProps.record.chaincodeDeployParams.codeLanguageType.lastIndexOf("_") + 1,
                                    ).toLowerCase()
                                }
                                expansionSummary={[
                                    controllerProps.record.chaincodeID.chaincodeName,
                                    controllerProps.record.chaincodeID.version,
                                    controllerProps.record.chaincodeDeployParams.codeLanguageType,
                                ]}
                            />
                        }
                        {
                            controllerProps.record && controllerProps.record.type === "CHAINCODE_DEPLOY"
                            && <RichTextField source="chaincodeDeployParams.legalProse" />
                        }
                        {
                            controllerProps.record && controllerProps.record.type === "CHAINCODE_DEPLOY"
                            && <TextField source="chaincodeDeployParams.codeLanguageType" />
                        }
                        {
                            controllerProps.record && controllerProps.record.type === "CHAINCODE_INVOKE"
                            && <TextField source="chaincodeInvokeParams.function" />
                        }
                        {
                            controllerProps.record && controllerProps.record.type === "CHAINCODE_INVOKE"
                            && <ArgField source="chaincodeInvokeParams.args" />
                        }
                        {
                            controllerProps.record && controllerProps.record.type === "CHAINCODE_SET_STATE"
                            && <BooleanField source="chaincodeSetStateParams.state" />
                        }
                    </Tab>
                </TabbedShowLayout>
            </ShowView>
        }
    </ShowController>
);
