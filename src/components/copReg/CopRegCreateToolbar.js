import React, { useState } from "react";
import { connect } from "react-redux";
import {
    showNotification,
    SaveButton,
    Toolbar,
    useRedirect,
} from "react-admin";
import { RestAPI, Transaction, Crypto } from "rclink";
import { push } from "react-router-redux";
import { Publish } from "@material-ui/icons";
import { LinearProgress } from "@material-ui/core";
import { useFormState } from "react-final-form";
import settings from "../../settings";
import { getKeypair } from "../../dataprovider/keypairsStore";
import newWebsocketClient from "../../utils/websocketClient";

const CopRegTxSubmitButtonView = (props) => {
    const formState = useFormState();
    const redirectTo = useRedirect();

    const handleClick = async () => {
        const {
            redirect,
            // eslint-disable-next-line no-shadow
            showNotification,
            // eslint-disable-next-line no-shadow
            push,
            setProgress,
        } = props;

        const record = { ...formState.values };
        const { name } = record.image;
        const { desc } = record.image;
        const hashAlg = "sha256";
        const { imagesToUpload } = record;
        const images = imagesToUpload.map(value => value.rawFile);
        // eslint-disable-next-line no-undef
        const imageHashPromises = imagesToUpload.map(value => new Response(value.rawFile)
            .arrayBuffer()
            .then(imageBuffer => Crypto.GetHashVal({
                data: Buffer.from(imageBuffer), alg: hashAlg,
            }).toString("hex")),
        );
        const imageHashes = await Promise.all(imageHashPromises)
            .then(imageHashArray => JSON.stringify(imageHashArray));
        const chaincodeFunctionArgs = [JSON.stringify({
            hashArray: JSON.parse(imageHashes),
            hashAlg,
            desc,
            name,
        })];
        const tx = new Transaction({
            type: "CHAINCODE_INVOKE",
            chaincodeName: settings.RepChain.default.chaincodeName,
            chaincodeVersion: settings.RepChain.default.chaincodeVersion,
            chaincodeInvokeParams: {
                chaincodeFunction: "Request",
                chaincodeFunctionArgs,
            },
        });
        // eslint-disable-next-line no-undef
        const creditCode = localStorage.getItem("userId");
        const { prvKeyPEM, pubKeyPEM, certName } = await getKeypair(creditCode)
            .catch((e) => {
                showNotification(`${e}`, "warning");
                throw e;
            });
        let txSignedBuffer;
        try {
            txSignedBuffer = tx.sign({
                prvKey: prvKeyPEM,
                pubKey: pubKeyPEM,
                alg: "ecdsa-with-SHA1",
                pass: record.password,
                creditCode,
                certName,
            });
        } catch (e) {
            showNotification(`${e}`, "warning");
            return;
        }

        const rest = new RestAPI(settings.RepChain.default.url_api);
        const wsClient = newWebsocketClient();
        wsClient.onerror = ((ev) => {
            console.error("websocket error: ", ev);
        });
        const imageHashArray = JSON.parse(imageHashes);
        wsClient.onmessage = (ev) => {
            const copReg = JSON.parse(ev.data);
            if (copReg && imageHashArray.indexOf(copReg.copRegKey) !== -1) {
                imageHashArray.splice(imageHashArray.indexOf(copReg.copRegKey), 1);
            }
            if (imageHashArray.length === 0) {
                setProgress(75);
                showNotification("版权登记请求已上链", "info");
                // upload the images
                // eslint-disable-next-line no-undef
                const formData = new FormData();
                images.forEach((image) => {
                    formData.append("images", image);
                });
                formData.append("hashAlg", hashAlg);
                formData.append("imageHashes", imageHashes);
                // eslint-disable-next-line no-undef
                fetch("/images", {
                    method: "POST",
                    body: formData,
                    headers: {
                        token: localStorage.getItem("token"),
                    }
                }).then(res => {
                    if (res.status === 401 || res.status === 403) {
                        const err = new Error("请先登录");
                        err.status = res.status;
                        throw err;
                    }
                    return res.json();
                })
                    .then((res) => {
                        setProgress(100);
                        showNotification(`已成功上传${res.count}张图片`, "info");
                        redirectTo(redirect, "/copRegs");
                        return 0;
                    }).catch((err) => {
                        redirectTo("/login");
                        showNotification(`${err.message}`, "warning");
                    })
                    .finally(() => wsClient.close());
            }
        };
        setProgress(25);
        await rest.sendTransaction(txSignedBuffer)
            .then((r) => {
                if (r.err) throw r.err;
                setProgress(50);
                showNotification("resources.Transaction.notification.send_success",
                    "info", { messageArgs: { txId: r.txid } });
                console.log("TX send feedback:\n", JSON.stringify(r));
            })
            .catch((e) => {
                showNotification(`${e}`, "warning");
            });
    };

    const { handleSubmitWithRedirect, ...newProps } = props;

    return (
        <div>
            <SaveButton
                handleSubmitWithRedirect={handleClick}
                type="submit"
                id="submitTransaction"
                {...newProps}
            />
        </div>
    );
};

const CopRegTxSubmitButton = connect(undefined, {
    showNotification,
    push,
})(CopRegTxSubmitButtonView);

const CopRegCreateToolbar = (props) => {
    const [progress, setProgress] = useState(0);
    return (
        <div>
            <Toolbar {...props}>
                <CopRegTxSubmitButton
                    label="申请登记"
                    icon={<Publish />}
                    redirect="list"
                    submitOnEnter={false}
                    variant="contained"
                    setProgress={setProgress}
                />
            </Toolbar>
            <LinearProgress variant="determinate" value={progress} />
        </div>
    );
};

export default CopRegCreateToolbar;
