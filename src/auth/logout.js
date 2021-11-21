import React, { forwardRef } from "react";
import { useLogout, useTranslate } from "ra-core";
import { MenuItem } from "@material-ui/core";
import { 
    ExitToApp as LogoutIcon, 
    VpnKey as KeypairIcon,
} from "@material-ui/icons";
import { withStyles } from "@material-ui/core/styles";
import fileDownload from "js-file-download";
import { getKeypair } from "../dataprovider/keypairsStore";

const styles = themes => ({
    icon: {
        marginRight: 15,
    },
});

// eslint-disable-next-line react/display-name
const LogoutButton = forwardRef((props, ref) => {
    const logout = useLogout();
    const translate = useTranslate();
    const { classes } = props;
    // eslint-disable-next-line no-undef
    const role = translate(`auth.${localStorage.getItem("role")}`);
    // eslint-disable-next-line no-undef
    const userId = localStorage.getItem("userId");

    const handleLogout = () => logout();
    const handleExportKeypair = async () => {
        const { certName, certPEM, prvKeyPEM } = await getKeypair(userId);
        const content = `${prvKeyPEM}${certPEM}`;
        const suffix = ".pem";
        const fileName = `${userId}.${certName}${suffix}`;
        fileDownload(content, fileName);
    };

    return ([
        <MenuItem
            ref={ref}
            key="userId"
        >
            {userId}
        </MenuItem>,
        <MenuItem
            ref={ref}
            key="role"
        >
            {role}
        </MenuItem>,
        <MenuItem
            onClick={handleExportKeypair}
            ref={ref}
            key="exportKeypair"
        >
            <KeypairIcon className={classes.icon} />  {translate("auth.keypair")}
        </MenuItem>,
        <MenuItem
            onClick={handleLogout}
            ref={ref}
            key="logout"
        >
            <LogoutIcon className={classes.icon} />  {translate("ra.auth.logout")}
        </MenuItem>,
    ]);
});

export default withStyles(styles)(LogoutButton);
