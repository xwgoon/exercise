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
import Card from "@material-ui/core/Card";
import { withStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import { translate } from "react-admin/lib";
import { Link } from "react-router-dom";
import CardIcon from "./CardIcon";

const styles = {
    main: {
        flex: "1",
        marginLeft: "1em",
        marginTop: 20,
    },
    titleLink: { textDecoration: "none", color: "inherit" },
    card: {
        overflow: "inherit",
        textAlign: "right",
        padding: 16,
        minHeight: 52,
    },
};

const dashItem = ({
    icon, iconBgColor, title, value, to, translate, classes,
}) => (
        <div className={classes.main}>
            <CardIcon Icon={icon} bgColor={iconBgColor} />
            <Card className={classes.card}>
                <Link to={{
                    pathname: to,
                    query: { filter: JSON.stringify({ status: "pending" }) },
                }} className={classes.titleLink}>
                    <Typography className={classes.title} color="textSecondary">
                        {translate(title)}
                    </Typography>
                    <Typography variant="headline" component="h2">
                        {value}
                    </Typography>
                </Link>
            </Card>
        </div>
    );

export default translate(withStyles(styles)(dashItem));
