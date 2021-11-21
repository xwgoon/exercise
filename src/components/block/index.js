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
import React from 'react';
import {
    Filter, DateField,
    Show, TabbedShowLayout, Tab,
    Responsive, SimpleList, List, Datagrid, TextField,
    ShowButton, ReferenceManyField, TextInput,
    ReferenceField, ChipField, Pagination,
    ArrayField,
} from 'react-admin/lib';

import { CardActions, RefreshButton, } from 'react-admin';
import { Card, CardContent, Typography } from "@material-ui/core";

const PostActions = ({ resource, filters, displayedFilters, filterValues, basePath, showFilter }) => (
    <CardActions>
        {filters && React.cloneElement(filters, {
            resource,
            showFilter,
            displayedFilters,
            filterValues,
            context: 'button',
        })}
        <RefreshButton />
    </CardActions>
);

const BlockFilter = props => (
    <Filter {...props}>
        <TextInput label="hash" source="hash_contains" alwaysOn />
    </Filter>
);

export const BlockList = (props) => (
    <List {...props}
        filters={<BlockFilter />}
        sort={{ field: "timestamp", order: "DESC" }}
        bulkActionButtons={false}
        actions={<PostActions />}
    >

        <Responsive
            small={
                <SimpleList
                    primaryText={record => record.id}
                    secondaryText={record => record.height}
                    tertiaryText={record => new Date(record.timestamp).toLocaleDateString()}
                    linkType="show"
                />
            }
            medium={
                <Datagrid>
                    <TextField source="height" />
                    <DateField source="timestamp" showTime options={{ hour12: false }} />
                    <TextField source="hash" />
                    <ShowButton />
                </Datagrid>
            }
        />
    </List>
);

const BlockTitle = ({ record }) => {
    return <span>区块 #{record ? `${record.id}` : ''}</span>;
};

const SignaturePanel = props => (
    <Card>
        <CardContent>
            <Typography variant="h6" color="default" gutterBottom > 
                签名信息
            </Typography>
            <Typography variant="h5" color="secondary" style={{wordBreak: "break-all"}} >
                {props.record.signature} 
            </Typography>
        </CardContent>
    </Card>
);

export const BlockShow = (props) => (
    <Show title={<BlockTitle />} {...props}>
        <TabbedShowLayout>
            <Tab label="resources.Block.tabs.tab1">
                <TextField source="id" />
                <TextField source="version" />
                <TextField source="height" />
                <TextField source="transCount" />
                <DateField source="timestamp" showTime options={{ hour12: false }} />
                <ReferenceField label="前块哈希值" source="prevBlock.id" reference="Block" linkType="show">
                    <TextField source="hash" style={{wordBreak: "break-all"}}/>
                </ReferenceField>
                <TextField source="hash" style={{wordBreak: "break-all"}} /> <ReferenceField label="后块哈希值" source="followBlock.id" reference="Block" linkType="show">
                    <TextField source="hash" style={{wordBreak: "break-all"}}/>
                </ReferenceField>
                <TextField source="stateHash" style={{wordBreak: "break-all"}} />
            </Tab>
            <Tab label="resources.Block.tabs.tab2">
                <ArrayField source="endorsements" addLabel={false}>
                    <Datagrid expand={<SignaturePanel />}>
                        <TextField label="背书人信用代码" source="certID.creditCode" sortable={false} />
                        <TextField label="背书人证书名称" source="certID.certName" sortable={false} />
                        <DateField label="背书时间" source="timestamp" options={{ hour12: false }} showTime sortable={false} />
                    </Datagrid>
                </ArrayField>
            </Tab>
            <Tab label="resources.Block.tabs.tab3">
                <ReferenceManyField
                    reference="Transaction"
                    target="block.id"
                    addLabel={false}
                    pagination={<Pagination />}
                >
                    <Datagrid>
                        <TextField source="txid" />
                        <TextField source="orderInBlock" />
                        <DateField source="timestamp" showTime options={{ hour12: false }} />
                        <ChipField source="type" />
                        <ShowButton />
                    </Datagrid>
                </ReferenceManyField>
            </Tab>

        </TabbedShowLayout>
    </Show>
);
