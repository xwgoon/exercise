import React from "react";
import { ChipField } from "react-admin";
import { useTranslate } from "ra-core";
import {
    red, yellow, green, grey, 
} from "@material-ui/core/colors";
import { withStyles } from "@material-ui/core/styles";

const styles = () => ({
    requested: {
        color: grey[50],
        backgroundColor: yellow[800],
    },
    registered: {
        color: grey[50],
        backgroundColor: green[500],
    },
    rejected: {
        color: grey[50],
        backgroundColor: red[500],
    },
});

const StatusField = ({ record = {}, classes, ...props }) => {
    const translate = useTranslate();
    const translatedStatus = translate(`resources.copRegs.status.${record.status}`);
    const translatedRecord = { ...record, status: translatedStatus };
    let className;
    if (record.status === "REQUESTED") className = classes.requested;
    if (record.status === "REGISTERED") className = classes.registered;
    if (record.status === "REJECTED") className = classes.rejected;
    return (
        <ChipField className={className} record={translatedRecord} {...props} />
    );
};
StatusField.defaultProps = { addLabel: true };

export default withStyles(styles)(StatusField);
