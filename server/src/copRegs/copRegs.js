import cfg from "config";
import { prisma } from "../db/generated/prisma-client";

export const copRegFragment = `
    fragment CopRegWithImageMeta on CopyrightRegistration {
        id
        copRegKey
        status
        ownerId
        requestTxId
        requestNotes
        registerTxId
        registerNotes
        rejectTxId
        rejectNotes
        image {
            hash
            hashAlg
            name
            desc
            url
        }
    }
`;

const accountIdFragment = `
    fragment AccountId on Account {
        id
    }
`;

const transactionIdFragment = `
    fragment transactionId on Transaction {
        id
    }
`;

export const getCopReg = copRegId => prisma.copyrightRegistration({ id: copRegId })
    .$fragment(copRegFragment);

export const getCopRegList = async ({
    filter: {
        operationTime,
        imageName,
        imageDesc,
        ownerName,
        ownerCreditCode,
        status,
    } = {},
    orderBy = "id_DESC",
    pagination: { page = 1, perPage = 10 } = { page: 1, perPage: 10 },
}) => {
    const filter = {};
    if (operationTime) {
        const txIds = await prisma.transactions({
            where: {
                timestamp_gte: new Date(operationTime).toISOString(),
                timestamp_lt: new Date(operationTime + 24 * 60 * 60 * 1000).toISOString(),
                chaincodeID: { 
                    chaincodeName: cfg.get("RepChain.chaincodeName"),
                },
                chaincodeInvokeParams: {
                    function_in: ["Request", "Register", "Reject"],
                },
            },
        }).$fragment(transactionIdFragment).then(res => res.map(el => el.id));
        filter.OR = [
            { requestTxId_in: txIds },
            { registerTxId_in: txIds },
            { rejectTxId_in: txIds },
        ];
    }
    if (imageName) filter.image = { name_contains: imageName };
    if (imageDesc) filter.image = { ...filter.image, desc_contains: imageDesc };
    if (ownerName) {
        const accountIds = await prisma.accounts({
            where: {
                name_contains: ownerName,
            },
        }).$fragment(accountIdFragment).then(res => res.map(el => el.id));
        filter.ownerId_in = accountIds;
    }
    if (ownerCreditCode) {
        const accountFilter = { creditCode: ownerCreditCode };
        const accountId = await prisma.account(accountFilter)
            .$fragment(accountIdFragment).then(res => res.id);
        filter.ownerId = accountId;
    }
    if (status) filter.status = status;

    return prisma.copyrightRegistrations({
        where: filter,
        orderBy,
        skip: (page - 1) * perPage,
        first: perPage,
    }).$fragment(copRegFragment)
        .then(copRegList => prisma.copyrightRegistrationsConnection({
            where: filter,
        }).aggregate()
            .count()
            .then(totalCount => ({ copRegList, totalCount })));
};
