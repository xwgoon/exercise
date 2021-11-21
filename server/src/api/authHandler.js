import jwt from "jsonwebtoken";
import config from "config";
import { VerifySign, GetHashVal } from "rclink/lib/crypto";
import uuid from "uuid/v1";
import { Transaction } from "rclink";
import { isEqual } from "lodash";
import getPubkey from "../account/getPubKey";
import signAndSendTx from "../RepChain/signAndSendTransaction";
import serverInnerLogger from "../log/serverInnerLog";
import { repChainSynchronizer } from "../RepChain/repchainSynchronize";

const getRole = (userId) => { // 获取用户角色
    // 为了简化流程, 本示例中用户角色只有2类: 
    // 1. 机构用户(AUTHORITY_USER，可审核版权登记请求，发起确认或拒绝操作)
    // 2. 普通用户(NORMAL_USER，能发起版权登记请求)
    // 并且假定表示RepChain网络节点的节点账户即是机构用户，而其他用户都为普通用户
    const authorities = [ // 代表RepChain网络节点的账户Id
        "121000005l35120456",
        "12110107bi45jh675g",
        "122000002n00123567",
        "921000005k36123789",
        "921000006e0012v696",
    ];
    if (authorities.includes(userId)) return "AUTHORITY_USER";
    return "NORMAL_USER";
};

// 交互式登录验证
// stage1: 客户端发起登录请求，服务端返回签名挑战信息；
// stage2: 客户端返回签名结果，服务端验证签名，以验证用户身份
// 前端与服务端的后续数据交互基于JWT来验证身份

const loginSignChallenge = new Map();

export const loginHandler = async (req, res) => {
    const {
        userId, certName, signAlg, signature,
    } = req.body;
    const challengeKey = `${userId}#${certName}`;
    if (!signAlg || !signature) { // in stage1，则返回一个随机签名挑战信息
        const challenge = GetHashVal({ data: `${userId}#${certName}#${uuid()}` }).toString("base64");
        loginSignChallenge.set(challengeKey, challenge);
        res.status(200)
            .json({ challenge })
            .end();
    } else { // in stage2，验证签名信息
        const { pubKey, status } = await getPubkey(userId, certName);
        if (!pubKey) { // 账户或账户证书未注册
            loginSignChallenge.delete(challengeKey);
            res.status(404)
                .json({ message: `Canot find the user public key or certificate: userId(${userId}) certName(${certName})` })
                .end();
            return;
        }
        if (!status) { // 账户证书已被禁用
            loginSignChallenge.delete(challengeKey);
            res.status(403)
                .json({ message: "The user certificate is forbidden to use any more" })
                .end();
            return;
        }
        if (!VerifySign({
            pubKey,
            sigValue: Buffer.from(signature, "hex"),
            data: loginSignChallenge.get(challengeKey),
            alg: signAlg,
        })) {
            loginSignChallenge.delete(challengeKey);
            res.status(400)
                .json({ message: "Bad signature or signAlg" })
                .end();
            return;
        }
        loginSignChallenge.delete(challengeKey);
        const secrect = config.get("ServerCrypto.jwtSecrect");
        const algorithm = config.get("ServerCrypto.jwtSignAlg");
        const token = jwt.sign({
            userId,
            certName,
            role: getRole(userId),
        }, secrect, {
            algorithm,
            expiresIn: "30m",
        });
        res.status(200)
            .json({
                token,
            }).end();
    }
};

export const registerHandler = async (req, res) => {
    const {
        username, creditCode, phone, certPEM,
    } = req.body;

    // 用户注册过程需要先向RepChain注册账户，然后注册账户证书
    // RepChain已经内置了账户及账户证书的管理链码chaincode（智能合约）
    // 这里构造调用账户及账户证书注册链码方法的签名交易，并提交到RepChain网络

    // 首先订阅观察账户注册事件，以便下一步注册相应账户证书
    const accountRegisteredPromise = new Promise((resolve) => {
        const accountObserver = new (function AccountObserver() {
            const isMatch = worldState => worldState.chaincodeName === "ContractCert"
                && isEqual(worldState.functions, ["SignUpSigner"])
                && worldState.key === creditCode;
            this.update = (worldState) => {
                if (isMatch(worldState)) {
                    repChainSynchronizer.unsubscribeObserver(this);
                    resolve(true);
                }
            };
        })();
        repChainSynchronizer.subscribeObserver(accountObserver);
    });

    // 构造并发送用于注册账户的签名交易
    let chaincodeFunctionArgs = [JSON.stringify({
        name: username,
        creditCode,
        mobile: phone,
        certNames: [],
    })];
    let tx = new Transaction({
        type: "CHAINCODE_INVOKE",
        chaincodeName: "ContractCert",
        chaincodeVersion: 1,
        chaincodeInvokeParams: {
            chaincodeFunction: "SignUpSigner",
            chaincodeFunctionArgs,
        },
    });
    let result;
    try {
        result = await signAndSendTx(tx);
        if (result.err) {
            const errMsg1 = `Failed to register account(username: ${username}, creditCode: ${creditCode}), `;
            const errMsg2 = `with error message from RepChain: ${result.err}`;
            const message = `${errMsg1}${errMsg2}`;
            serverInnerLogger.error(message);

            res.status(400)
                .json({ message })
                .end();
            return;
        }
    } catch (err) {
        const errMsg1 = `Failed to register account(username: ${username}, creditCode: ${creditCode}), `;
        const errMsg2 = `with error: ${err}`;
        const message = `${errMsg1}${errMsg2}`;
        serverInnerLogger.error(message);

        res.status(500)
            .json({ message })
            .end();
        return;
    }

    // 等待RepChain账户注册完成
    await accountRegisteredPromise;

    // 订阅观察账户证书注册事件
    const certRegisteredPromise = new Promise((resolve) => {
        const certObserver = new (function CertObserver() {
            const isMatch = worldState => worldState.chaincodeName === "ContractCert"
                && isEqual(worldState.functions, ["SignUpCert"])
                && worldState.key === `${creditCode}.default`;
            this.update = (worldState) => {
                if (isMatch(worldState)) {
                    repChainSynchronizer.unsubscribeObserver(this);
                    resolve(true);
                }
            };
        })();
        repChainSynchronizer.subscribeObserver(certObserver);
    });

    // 构造并提交用于注册账户证书的签名交易
    const now = Date.now();
    chaincodeFunctionArgs = [JSON.stringify({
        name: "default",
        credit_code: creditCode,
        cert: {
            certificate: certPEM,
            algType: "",
            certValid: true,
            regTime: {
                seconds: parseInt(now / 1000, 10),
                nanos: (now % 1000) * 1000000,
            },
        },
    })];
    tx = new Transaction({
        type: "CHAINCODE_INVOKE",
        chaincodeName: "ContractCert",
        chaincodeVersion: 1,
        chaincodeInvokeParams: {
            chaincodeFunction: "SignUpCert",
            chaincodeFunctionArgs,
        },
    });
    try {
        result = await signAndSendTx(tx);
        if (result.err) {
            const errMsg1 = `Failed to register certificate(user creditCode: ${creditCode}, cert name: "default")`;
            const errMsg2 = `with error message from RepChain: ${result.err}`;
            const message = `${errMsg1}${errMsg2}`;
            serverInnerLogger.error(message);

            res.status(400)
                .json({ message })
                .end();
            return;
        }
    } catch (err) {
        const errMsg1 = `Failed to register certificate(user creditCode: ${creditCode}, cert name: "default")`;
        const errMsg2 = `with error: ${err}`;
        const message = `${errMsg1}${errMsg2}`;
        serverInnerLogger.error(message);

        res.status(500)
            .json({ message })
            .end();
        return;
    }

    // 等待RepChain账户证书注册完成
    await certRegisteredPromise;

    res.status(200).end();
};

export const tokenVerifyHandler = async (req, res, next) => {
    const secrect = config.get("ServerCrypto.jwtSecrect");
    const algorithm = config.get("ServerCrypto.jwtSignAlg");
    const token = req.headers.token || req.body.token || req.query.token;
    let decoded;
    try {
        decoded = jwt.verify(token, secrect, { algorithm });
        req.userId = decoded.userId;
        req.certName = decoded.certName;
        next();
    } catch (e) {
        res.status(401).end();
    }
};
