import React, { useState, useCallback, useRef } from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import Link from "@material-ui/core/Link";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import CircularProgress from "@material-ui/core/CircularProgress";
import { FieldTitle } from "react-admin";
import { useTranslate, useLogin, Notification, useNotify } from "react-admin";
import { useDropzone } from "react-dropzone";
import { Paper, RootRef } from "@material-ui/core";
import { getKeypair } from "../dataprovider/keypairsStore";

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
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
    p: {
        textAlign: "center",
    },
}));

function KeypairFileZone({ className, pClassName, onSelected }) {
    const onDrop = useCallback((acceptedFiles) => {
        acceptedFiles.forEach((file) => {
            // eslint-disable-next-line no-undef
            const reader = new FileReader();
            reader.onabort = () => console.log("file reading was aborted");
            reader.onerror = () => console.log("file reading was failed");
            reader.onload = () => {
                const keypairStr = reader.result;
                onSelected(keypairStr);
            };
            reader.readAsText(file);
        });
    }, []);

    const { acceptedFiles, getRootProps, getInputProps } = useDropzone({
        onDrop, accept: ".pem", multiple: false,
    });
    const { ref, ...rootProps } = getRootProps();

    const translate = useTranslate();

    return (
        <RootRef rootRef={ref} >
            <Paper {...rootProps} className={className}>
                <input {...getInputProps()} />
                <p className={pClassName}>
                    {translate("auth.keypairUpload")}
                </p>
                <aside className={pClassName}>
                    {
                        acceptedFiles.length > 0 
                            && acceptedFiles[0].name
                    }
                </aside>
            </Paper>
        </RootRef>
    );
}

export default function SignIn({ location }) {
    const classes = useStyles();
    const [creditCode, setCreditCode] = useState( 
        location?.state?.creditCode 
        ? location.state.creditCode 
        : ""
    );
    const [password, setPassword] = useState("");
    const [needImportKeypair, setNeedImportKeypair] = useState(false);
    const [keypairImported, setKeypairImported] = useState("");
    const [certName, setCertName] = useState("default");
    const [showProgress, setShowProgress] = useState(false);
    const login = useLogin();
    const notify = useNotify();
    const onSubmit = (e) => {
        setShowProgress(true);
        e.preventDefault();
        login({
            creditCode, password, keypairImported, certName, 
        }).catch((err) => notify(err.message, "warning"))
        .finally(() => setShowProgress(false));
    };

    const textFieldRef = useRef();
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
                    {translate("ra.auth.sign_in")}
                </Typography>
                <form className={classes.form} noValidate>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        ref={textFieldRef}
                        fullWidth
                        id="creditCode"
                        label={<FieldTitle label="auth.creditCode" />}
                        name="creditCode"
                        autoComplete="current-creditCode"
                        autoFocus
                        value={creditCode}
                        onChange={(e) => {
                            getKeypair(e.target.value).then((keypair) => {
                                if (!keypair) setNeedImportKeypair(true);
                                else setNeedImportKeypair(false);
                                return 0;
                            });
                            setCreditCode(e.target.value);
                        }}
                    />
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label={<FieldTitle label="ra.auth.password" />}
                        type="password"
                        id="password"
                        autoComplete="current-password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    />
                    {
                        needImportKeypair
                        && <KeypairFileZone
                            className={textFieldRef.current.className}
                            pClassName={classes.p}
                            onSelected={keypairSelected => setKeypairImported(keypairSelected)}
                        />
                    }
                    {
                        needImportKeypair
                        && <TextField
                            variant="outlined"
                            margin="normal"
                            required
                            fullWidth
                            name="certName"
                            label={<FieldTitle label="auth.certName" />}
                            id="certName"
                            autoComplete="current-certName"
                            value={certName}
                            onChange={e => setCertName(e.target.value)}
                        />
                    }
                    <FormControlLabel
                        control={<Checkbox value="remember" color="primary" />}
                        label={<FieldTitle label="auth.rememberme" />}
                    />
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                        onClick={onSubmit}
                    >
                        {translate("ra.auth.sign_in")}
                    </Button>
                    <Grid container>
                        <Grid item xs>
                            <Link href="#" variant="body2">
                                Forgot password?
                            </Link>
                        </Grid>
                        <Grid item>
                            <Link href="#/register" variant="body2">
                                {"Don't have an account? Sign Up"}
                            </Link>
                        </Grid>
                    </Grid>
                </form>
                <Notification />
            </div>
            <Box mt={8}>
                <Copyright />
            </Box>
        </Container>
    );
}
