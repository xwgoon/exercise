import request from "supertest";
import app, { server } from "../../src/server";
import {
    createSomeTestUsers,
    createSomeTestCopRegs,
    // clearTestUsers,
    // clearTestRecords,
} from "../helpers/prismaOps";

describe("copRges api test", () => {
    const agent = request(app);
    const baseUrl = "/copRegs";
    const testIdentifier = "test-copRegs";
    let testUsers;
    let testCopRegs;


    beforeAll(async () => {
        testUsers = await createSomeTestUsers(40, testIdentifier);
        testCopRegs = await createSomeTestCopRegs(testUsers, testIdentifier);
    });

    afterAll(async () => {
        // await clearTestRecords(testIdentifier);
        // await clearTestUsers(testIdentifier);
        server.close();
    });

    // test.only("clear", () => {});

    test("Should get success when querying copReg", async () => {
        const url = `${baseUrl}/${testCopRegs[0].id}`;
        await agent.get(url)
            .query({ userId: testUsers[0].userID })
            .then((res) => {
                expect(res.status).toBe(200);
                expect(res.body.copReg).toBeDefined();
                expect(res.body.copReg).toEqual(testCopRegs[0]);
                return 0;
            });
    });

    test("Should get success when querying copRegList", async () => {
        const url = baseUrl;
        // with default params
        await agent.get(url)
            .query({ userId: testUsers[0].userID })
            .then((res) => {
                expect(res.status).toBe(200);
                expect(res.body.copRegList).toBeDefined();
                expect(res.body.totalCount).toBeGreaterThanOrEqual(testCopRegs.length);
                return 0;
            });

        // with filter params 
        await agent.get(url)
            .query({
                userId: testUsers[0].userID,
                imageDesc: testIdentifier,
                ownerCreditCode: testUsers[0].userID,
            })
            .then((res) => {
                expect(res.status).toBe(200);
                expect(res.body.copRegList).toBeDefined();
                expect(res.body.totalCount).toBe(1);
                return 0;
            });

        // with sortBy param
        await agent.get(url)
            .query({
                userId: testUsers[0].userID,
                imageDesc: testIdentifier,
                sortBy: "id_DESC",
            })
            .then((res) => {
                expect(res.status).toBe(200);
                expect(res.body.copRegList).toBeDefined();
                expect(res.body.copRegList.length).toBe(testCopRegs.length);
                expect(res.body.copRegList).toEqual(testCopRegs.sort((a, b) => { 
                    if (b.id > a.id) return 1;
                    if (b.id < a.id) return -1;
                    return 0;
                }));
                return 0;
            });
    });
});
