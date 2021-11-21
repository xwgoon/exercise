import React from "react";
import { get } from "lodash";
import { withStyles } from "@material-ui/core/styles";

const styles = () => ({
    pre: {
        background: "rgba(0,0,0,0.1)",
        border: "1px solid #ccc",
        padding: 20,
        width: "100%",
    },
});

const ArgField = ({ record = {}, source, classes }) => {
    let arg = get(record, source);
    try {
        const jsonArg = JSON.parse(arg);
        arg = JSON.stringify(jsonArg, 0, 2);
    } catch (e) {
        console.error(e);
    }
    return (<pre className={classes.pre}>{arg}</pre>);
};

const ArgFieldWithLabel = withStyles(styles)(ArgField);
ArgFieldWithLabel.defaultProps = {
    addLabel: true,
};

export default ArgFieldWithLabel;