import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Link from "@material-ui/core/Link";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import CircularProgress from "@material-ui/core/CircularProgress";
import { FieldTitle, useNotify, Notification } from "react-admin";
import { useTranslate } from "ra-core";
import {
    CreateKeypair,
    CreateSelfSignedCertificate,
    GetKeyPEM,
    GetHashVal,
} from "rclink/lib/crypto";
import { storeKeypair } from "../dataprovider/keypairsStore";

function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {"Copyright © "}
            <Link color="inherit" href="">
                CRBB
            </Link>{" "}
            {new Date().getFullYear()}
            {"."}
        </Typography>
    );
}

const useStyles = makeStyles(theme => ({
    paper: {
        marginTop: theme.spacing(8),
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: "100%", // Fix IE 11 issue.
        marginTop: theme.spacing(3),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
}));

const registerAccount = ({ history, ...params }) => {
    const { password, creditCode } = params;
    // 创建非对称密钥对及自签名数字证书
    const keypair = CreateKeypair("EC", "secp256k1");
    const prvKeyPEM = GetKeyPEM(keypair.prvKeyObj, password);
    const pubKeyPEM = GetKeyPEM(keypair.pubKeyObj);
    const serialNumber = GetHashVal({
        data: GetHashVal({ data: pubKeyPEM }),
        alg: "RIPEMD160",
    }).toString("hex");
    const certValidStartUnixTime = parseInt(Date.now() / 1000, 10);
    const certValidEndUnixTime = certValidStartUnixTime + 365 * 24 * 3600;
    const certDN = `/CN=copRegUser${creditCode}`;
    const certPEM = CreateSelfSignedCertificate({
        serialNumber,
        sigAlg: "SHA256withECDSA",
        DN: certDN,
        notBefore: certValidStartUnixTime,
        notAfter: certValidEndUnixTime,
        keypair,
    });

    const url = "/auth/register";
    // eslint-disable-next-line no-undef
    return fetch(url, {
        method: "POST",
        body: JSON.stringify({
            ...params,
            certPEM,
        }),
        headers: {
            "Content-Type": "application/json",
        },
    }).then(async (res) => {
        if (res.status !== 200) {
            const resJson = await res.json();
            throw new Error(resJson.message);
        }
        await storeKeypair({
            ownerCreditCode: creditCode,
            certPEM,
            certName: "default",
            prvKeyPEM,
            pubKeyPEM,
        });
        history.push({ pathname : "/login", state: { creditCode } });
    });
};

export default function SignUp() {
    const classes = useStyles();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [creditCode, setCreditCode] = useState("");
    const [phone, setPhone] = useState("");
    const [showProgress, setShowProgress] = useState(false);
    const history = useHistory();
    const notify = useNotify();

    const onSubmit = (e) => {
        setShowProgress(true);
        e.preventDefault();
        registerAccount({
            username, password, creditCode, phone, history
        }).catch((err) => notify(err.message, "warning"))
        .finally(() => setShowProgress(false));
    };

    const translate = useTranslate();

    return (
        <Container component="main" maxWidth="xs">
            <CircularProgress 
                style={{
                    width: 65,
                    height: 65,
                    position: "absolute",
                    left: "48%",
                    top: "30%",
                    visibility: showProgress ? "visible" : "hidden",
                }}
                color="primary"
            />
            <CssBaseline />
            <div className={classes.paper}>
                <Avatar className={classes.avatar}>
                    <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                    {translate("auth.sign_up")}
                </Typography>
                <form className={classes.form} noValidate>
                    
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="username"
                                label={<FieldTitle label="auth.username" />}
                                name="username"
                                autoFocus
                                autoComplete="current-username"
                                value={username}
                                onChange={e => setUsername(e.target.value)}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="creditCode"
                                label={<FieldTitle label="auth.creditCode" />}
                                name="creditCode"
                                autoComplete="current-creditCode"
                                value={creditCode}
                                onChange={e => setCreditCode(e.target.value)}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="phone"
                                label={<FieldTitle label="auth.phone" />}
                                name="phone"
                                autoComplete="current-phone"
                                value={phone}
                                onChange={e => setPhone(e.target.value)}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                name="password"
                                label={<FieldTitle label="auth.password" />}
                                type="password"
                                id="password"
                                autoComplete="current-password"
                                value={password}
                                onChange={e => setPassword(e.target.value)}
                            />
                        </Grid>
                    </Grid>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                        onClick={onSubmit}
                    >
                        {translate("auth.sign_up")}
                    </Button>
                    <Grid container justify="flex-end">
                        <Grid item>
                            <Link href="#/login" variant="body2">
                                Already have an account? Sign in
                            </Link>
                        </Grid>
                    </Grid>
                </form>
                <Notification />
            </div>
            <Box mt={5}>
                <Copyright />
            </Box>
        </Container>
    );
}
