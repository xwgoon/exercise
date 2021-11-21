/* eslint-disable no-nested-ternary */
/* eslint-disable import/no-extraneous-dependencies */
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {
    Filter,
    Responsive,
    List,
    TextInput,
    SelectInput,
    CardActions,
    CreateButton,
    Pagination,
    useRefresh,
} from "react-admin";
import {
    Card,
    Tabs,
    Tab,
} from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";

import ImageField from "./ImageField";
import { DateInput } from "../../raMuiDateInput";
import settings from "../../settings";
import newWebsocketClient from "../../utils/websocketClient";

const CopRegFilter = props => (
    <Filter {...props}>
        <TextInput label="图片名称" source="imageName" alwaysOn />
        <TextInput label="图片描述" source="imageDesc" />
        <SelectInput label="登记状态" source="status" choices={[
            { id: "REQUESTED", name: "已请求" },
            { id: "REGISTERED", name: "已登记" },
            { id: "REJECTED", name: "已拒绝" },
        ]} />
        <DateInput
            label="操作时间"
            source="operationTime"
            parse={(v) => {
                const date = new Date(v);
                const dateStr = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`;
                console.log(dateStr);
                const dateTime = new Date(dateStr).getTime();
                return dateTime;
            }}
            options={{
                format: "yyyy/MM/dd",
                showTodayButton: true,
            }}
        />
    </Filter>
);

const PostActions = ({
    basePath,
    displayedFilters,
    filters,
    filterValues,
    resource,
    showFilter,
}) => (
    <div>
        <CardActions>
            {
                filters && React.cloneElement(filters, {
                    resource,
                    showFilter,
                    displayedFilters,
                    filterValues,
                    context: "button",
                })
            }
            <CreateButton basePath={basePath} />
        </CardActions>
    </div>
);

const styles = {
    imageGrid: {
        margin: "1em",
        display: "flex",
        flexWrap: "wrap",
    },
    child: {
        flex: "1 0 20%",
    },
    card: {
        minHeight: 180,
        maxHeight: 280,
        margin: "0.5em",
        display: "inline-block",
        verticalAlign: "top",
    },
    textField: {
        wordBreak: "break-all",
    },
    dialog: {
        width: 750,
        maxWidth: 800,
    },
    bar: {
        marginBottom: "1em",
        flexGrow: 1,
    },
    flex: {
        flexGrow: 1,
    },
    backButton: {
        marginLeft: "-0.6em",
        marginRight: "0.5em",
    },
};
const ImageGrid = withStyles(styles)(({
    ids, data, basePath, classes, ...props
}) => {
    const refresh = useRefresh();

    useEffect(() => {
        const wsClient = newWebsocketClient();
        wsClient.onerror = ((ev) => {
            console.error("websocket error: ", ev);
        });
        wsClient.onmessage = () => {
            refresh();
        };

        return () => wsClient.close();
    }, [data]);

    return <div className={classes.imageGrid}>
        {
            ids.map(id => (
                <Link key={id} to={{ pathname: `${basePath}/${id}/show` }} className={classes.child}>
                    <Card
                        className={classes.card}
                    >
                        <Responsive
                            medium={
                                <ImageField
                                    record={data[id]}
                                    source="image.url"
                                    width="100%"
                                    height={260}
                                />
                            }
                            small={
                                <ImageField
                                    record={data[id]}
                                    source="image.url"
                                    width="100%"
                                    height={230}
                                />
                            }
                        />
                    </Card>
                </Link>
            ))
        }
    </div>;
});

const CopRegList = ({ permissions, filter, ...props }) => (
    <List
        actions={<PostActions />}
        filters={<CopRegFilter />}
        filter={filter}
        sort={{ field: "createdAt", order: "DESC" }}
        {...props}
        perPage={8}
        pagination={<Pagination rowsPerPageOptions={[8, 12, 25, 50]} />}
    >
        <ImageGrid permissions={permissions} />
    </List>
);

const CopRegListWithTabs = (props) => {
    const [value, setValue] = useState(0);
    return (
        <div>
            <Responsive
                medium={
                    <Tabs
                        value={value}
                        onChange={(e, newValue) => setValue(newValue)}
                        indicatorColor="primary"
                        textColor="primary"
                        style={{ marginBottom: 15 }}
                    >
                        <Tab label="公示图片" />
                        <Tab label="我的图片" />
                    </Tabs>
                }
                small={
                    <Tabs
                        value={value}
                        onChange={(e, newValue) => setValue(newValue)}
                        indicatorColor="primary"
                        textColor="primary"
                        variant="fullWidth"
                        style={{ marginBottom: 15 }}
                    >
                        <Tab label="公示图片" />
                        <Tab label="我的图片" />
                    </Tabs>
                }
            />

            {
                value === 0
                    ? <CopRegList {...props} />
                    // eslint-disable-next-line no-undef
                    : <CopRegList filter={{ ownerCreditCode: localStorage.getItem("userId") }} {...props} />
            }
        </div>
    );
};

export default CopRegListWithTabs;
