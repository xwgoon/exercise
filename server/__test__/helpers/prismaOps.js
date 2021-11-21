// eslint-disable-next-line import/no-extraneous-dependencies
import faker from "faker";
import { Crypto } from "rclink";
import fs from "fs-extra";
import path from "path";
import { prisma } from "../../src/db/generated/prisma-client";
import { COMBINATION_DELIMITER } from "../../src/const";
import { copRegFragment } from "../../src/copRegs/copRegs";

export const createSomeTestUsers = async (count, identifier = "test") => {
    if (count < 1) {
        throw Error("Bad params, count should not be less than 1");
    }
    const accounts = [];
    const certs = [];
    const users = [];
    for (let i = 0; i < count; i++) {
        accounts.push({
            name: `${identifier}-account-${i}`,
            creditCode: `${identifier}-creditCode-${i}`,
            phone: faker.phone.phoneNumber("132########"),
        });

        const keypair = Crypto.CreateKeypair();
        const certPEM = Crypto.CreateSelfSignedCertificate({
            serialNumber: faker.random.number({ min: 100000000, max: 999999999 }),
            sigAlg: "SHA256withECDSA",
            DN: `/CN=${identifier}-${i}`,
            notBefore: Date.now(),
            notAfter: Date.now() / 1000 + 365 * 24 * 3600,
            keypair,
        });
        certs.push({
            creditCode: accounts[i].creditCode,
            name: `${identifier}-cert-${i}`,
            creditCodeCombineName: `${accounts[i].creditCode}${COMBINATION_DELIMITER}${identifier}-cert-${i}`,
            certPEM,
            status: true,
            account: {
                connect: { creditCode: accounts[i].creditCode },
            },
        });

        users.push({
            userID: accounts[i].creditCode,
            certificateName: certs[i].name,
            prvKeyPEM: Crypto.GetKeyPEM(keypair.prvKeyObj),
        });
    }

    await Promise.all(accounts.map(
        account => prisma.createAccount(account)));
    await Promise.all(certs.map(cert => prisma.createCert(cert)));

    return users;
};

export const createSomeTestCopRegs = async (users, identifier = "test") => {
    const imageStorageDir = path.join("..", "testFiles");
    const images = fs.readdirSync(path.join(__dirname, "..", "acceptanceTest", "testFiles"));
    if (images.length < users.length) throw new Error("The test image count should greater than or equal to test user count");
    const createPromises = users.map((user, index) => {
        const imageHash = faker.random.alphaNumeric(64);
        return prisma.createCopyrightRegistration({
            copRegKey: imageHash,
            owner: { connect: { creditCode: user.userID } },
            status: faker.random.arrayElement(["REQUESTED", "REGISTERED", "REJECTED"]),
            image: {
                create: {
                    hash: imageHash,
                    hashAlg: "sah256",
                    name: images[index].split(".")[0],
                    desc: `${identifier}${faker.lorem.sentence()}`,
                    storagePath: path.join(imageStorageDir, images[index]),
                    url: `/images/${imageHash}/download`,
                },
            },
        }).$fragment(copRegFragment);
    });
    const copRegs = await Promise.all(createPromises);
    return copRegs;
};

export const clearTestUsers = async (identifier = "test") => {
    await prisma.deleteManyCerts({
        creditCodeCombineName_starts_with: identifier,
    });
    await prisma.deleteManyAccounts({
        creditCode_starts_with: identifier,
    });
};

export const clearTestRecords = async (identifier = "test") => {
    await prisma.deleteManyCopyrightRegistrations({
        owner: {
            creditCode_starts_with: identifier,
        },
    });
    await prisma.deleteManyImageMetas({
        desc_starts_with: identifier,
    });
};
