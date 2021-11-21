import React, { useState } from "react";
import {
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    Button,
    TextField,
} from "@material-ui/core";
import { useTranslate } from "ra-core";
import CircularProgress from "@material-ui/core/CircularProgress";

export default function PasswordInputDialog({ onPasswordOk, open, setOpen, showProgress }) {
    const [password, setPassword] = useState("");
    const translate = useTranslate();

    const handleClose = () => {
        setOpen(false);
    };
    const handleOk = () => {
        onPasswordOk(password);
    };
    const handleCancel = () => {
        setOpen(false);
    };

    return (
        <Dialog
            open={open}
            onClose={handleClose}
            aria-labelledby="form-dialog-title"
        >
            <DialogTitle id="form-dialog-title">
                {translate("resources.copRegs.titles.passwordInputDialog")}
            </DialogTitle>
            <DialogContent>
                <TextField 
                    autoFocus
                    margin="dense"
                    id="password"
                    label={translate("resources.copRegs.fields.password")} 
                    type="password"
                    fullWidth
                    onChange={e => setPassword(e.target.value)}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={handleOk} color="primary">
                    {translate("resources.copRegs.buttons.ok")}
                </Button>
                <Button onClick={handleCancel} color="primary">
                    {translate("resources.copRegs.buttons.cancel")}
                </Button>
            </DialogActions>
            <CircularProgress 
                style={{
                    width: 65,
                    height: 65,
                    position: "absolute",
                    left: "40%",
                    top: "40%",
                    visibility: showProgress ? "visible" : "hidden",
                }}
                color="primary"
            />
        </Dialog>
    );
} 
