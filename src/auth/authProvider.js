/* eslint-disable no-undef */
import {
    AUTH_LOGIN, AUTH_LOGOUT, AUTH_ERROR, AUTH_CHECK, AUTH_GET_PERMISSIONS,
} from "react-admin/lib";
import {
    Sign,
    ImportKey,
    GetKeyPEM,
} from "rclink/lib/crypto";
import decodeJwt from "jwt-decode";
import { getKeypair, storeKeypair } from "../dataprovider/keypairsStore";

export default async (type, params) => {
    // called when the user attempts to log in
    if (type === AUTH_LOGIN) {
        const {
            creditCode, password, keypairImported, certName,
        } = params;
        // 若新导入密钥对信息则使用导入的密钥信息
        let keypair;
        if (keypairImported) {
            // eslint-disable-next-line no-useless-escape
            const prvKeyRex = /-*BEGIN.*\s+PRIVATE.*\s+KEY-*(\r\n)*[\w+=\/\r\n]*-*END.*\s+PRIVATE.*\s+KEY-*(\r\n)*/i;
            // eslint-disable-next-line no-useless-escape
            const certRex = /-*BEGIN.*\s+CERTIFICATE-*(\r\n)*[\w+=\/\r\n]*-*END.*\s+CERTIFICATE-*(\r\n)*/i;
            let prvKeyPEM = prvKeyRex.exec(keypairImported)[0];
            const certPEM = certRex.exec(keypairImported)[0];
            const pubKeyObj = ImportKey(certPEM);
            const pubKeyPEM = GetKeyPEM(pubKeyObj);
            const prvEncryptedRex = /^-*BEGIN.*\s+ENCRYPTED.*\s+PRIVATE.*\s+KEY-*/i;
            if (!prvEncryptedRex.test(prvKeyPEM)) {
                // 若导入的私钥未加密则使用用户提供的密码加密
                const prvKeyObj = ImportKey(prvKeyPEM);
                if (prvKeyObj.pubKeyHex === undefined) {
                    prvKeyObj.pubKeyHex = pubKeyObj.pubKeyHex;
                }
                prvKeyPEM = GetKeyPEM(prvKeyObj, password);
            }
            keypair = {
                ownerCreditCode: creditCode,
                certPEM,
                certName,
                prvKeyPEM,
                pubKeyPEM,
            };
        }
        const userId = creditCode;
        const { prvKeyPEM, pubKeyPEM, certName: localCertName } = keypair || await getKeypair(userId);
        let prvKeyObj;
        try {
            prvKeyObj = ImportKey(prvKeyPEM, password);
        } catch (e) {
            return Promise.reject(e);
        }
        if (prvKeyObj.pubKeyHex === undefined) prvKeyObj.pubKeyHex = ImportKey(pubKeyPEM).pubKeyHex;

        const loginUrl = "/auth/login";
        let challengeRes = await fetch(loginUrl, {
            method: "POST",
            body: JSON.stringify({
                userId, certName: localCertName,
            }),
            headers: {
                "Content-Type": "application/json",
            },
        });
        if (challengeRes.status !== 200) return Promise.reject(new Error(challengeRes.statusText));
        challengeRes = await challengeRes.json();
        const { challenge } = challengeRes;
        const signAlg = "sha256";
        const signature = Sign({
            prvKey: GetKeyPEM(prvKeyObj),
            data: challenge,
            alg: signAlg,
        }).toString("hex");
        return fetch(loginUrl, {
            method: "POST",
            body: JSON.stringify({
                userId,
                certName: localCertName,
                signAlg,
                signature,
            }),
            headers: {
                "Content-Type": "application/json",
            },
        }).then(async (res) => {
            const resJson = await res.json();
            if (res.status !== 200) throw new Error(resJson.message);
            return resJson;
        }).then((res) => {
            // 将导入的密钥对信息存入浏览器
            if (keypairImported) {
                storeKeypair(keypair);
            }
            localStorage.setItem("token", res.token);
            const decodedToken = decodeJwt(res.token);
            localStorage.setItem("userId", decodedToken.userId);
            localStorage.setItem("role", decodedToken.role);
            return 0;
        }).catch(err => Promise.reject(err));
    }
    // called when the user clicks on the logout button
    if (type === AUTH_LOGOUT) {
        localStorage.removeItem("token");
        localStorage.removeItem("userId");
        localStorage.removeItem("role");
        return Promise.resolve();
    }
    // called when the API returns an error
    if (type === AUTH_ERROR) {
        const { status } = params;
        if (status === 401 || status === 403) {
            return Promise.reject({ message: false });
        }
        return Promise.resolve();
    }
    // called when the user navigates to a new location
    if (type === AUTH_CHECK) {
        if (window.location.href.match(/(login)|(register)/)) {
            return Promise.resolve();
        }
        return localStorage.getItem("token")
            ? Promise.resolve()
            : Promise.reject({ message: "请先登录" });
    }
    // called when need permissions
    if (type === AUTH_GET_PERMISSIONS) {
        const role = localStorage.getItem("role");
        console.log(role);
        return role ? Promise.resolve(role) : Promise.reject(false);
    }
    return Promise.reject(new Error("Unknown method"));
};
