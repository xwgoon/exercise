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

const styles = {
    card: {
        float: "left",
        margin: "-20px 20px 0 15px",
        zIndex: 100,
        borderRadius: 3,
    },
    icon: {
        float: "right",
        width: 54,
        height: 54,
        padding: 14,
        color: "#fff",
    },
};

const CardIcon = ({ Icon, classes, bgColor }) => (
    <Card className={classes.card} style={{ backgroundColor: bgColor }}>
        <Icon className={classes.icon} />
    </Card>
);

export default withStyles(styles)(CardIcon);
