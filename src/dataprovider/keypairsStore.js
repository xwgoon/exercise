import IndexedDBRest from "./indexedDBRest";

const schema = {
    keypairs: "ownerCreditCode",
};

const indexedDBRest = new IndexedDBRest("CRBB", 1, schema, {});

export const storeKeypair = keypair => indexedDBRest.create("keypairs", keypair).then(res => res.result);

export const getKeypair = ownerCreditCode => indexedDBRest.getOne("keypairs", ownerCreditCode).then(res => res.result);
