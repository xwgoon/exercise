/*
 * Copyright  2018 Linkel Technology Co., Ltd, Beijing
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BA SIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
// 提供在Browser端的本地数据持久化功能，基于indexedDB，实现模拟的Rest接口服务
import Dexie from "dexie";
import flatPlainObj from "flat-plain-object";

// The private attribute
const indexedDBInstance = new WeakMap();

class IndexedDBRest {
    /**
     * 构造函数 
     * @param {String} dbName indexedDB实例的名字
     * @param {Number} version  indexedDB实例的版本号，高版本将覆盖低版本
     * @param {Object} schema indexedDB实例的schema，使用json object表示，其中每一个属性表示一个object store，类似关系数据库中的一个table
     * 详情参考http://dexie.org/docs/Version/Version.stores()  
     * @param {Object} initData 设置indexedDB实例的初始数据，
     * 其形式应为{<resource1>: [record1, ..., recordN], ... ,<resourceN>: [record1, ..., recordN]}
     */
    constructor(dbName, version, schema, initData) {
        const db = new Dexie(dbName);
        db.version(version).stores(schema);
        db.open().catch((e) => {
            throw new Error(`IndexedDB open failed: ${e.stack}`);
        });

        db.on("populate", () => {
            for (const key in initData) {
                if (initData.hasOwnProperty(key)) { 
                    for (const record of initData[key]) {
                        db.table(key).add(record);
                    } 
                }
            }
        });

        indexedDBInstance.set(this, db);
    }

    getCollection(resource, query) {
        const db = indexedDBInstance.get(this);
        const filter = flatPlainObj(query.filter);
        const { sort } = query;
        const { range } = query; 

        let collection = JSON.stringify(filter) === "{}" 
            ? db.table(resource).toCollection() 
            : db.table(resource).toCollection().filter((record) => {
                const flattedRecord = flatPlainObj(record);
                for (const field in filter) {
                    if (filter[field] instanceof Array) {
                        const filterParams = filter[field];
                        switch (filterParams[0]) {
                            case "below":
                                if (flattedRecord[field] >= filterParams[1]) { return false; }
                                break;
                            case "belowOrEqual":
                                if (flattedRecord[field] > filterParams[1]) { return false; }
                                break;
                            case "above":
                                if (flattedRecord[field] <= filterParams[1]) { return false; }
                                break;
                            case "aboveOrEqual":
                                if (flattedRecord[field] < filterParams[1]) { return false; }
                                break;
                            case "between":
                                if (flattedRecord[field] < Math.min(filterParams[1]) 
                                    || flattedRecord[field] > Math.max(filterParams[1])) { 
                                    return false; 
                                }
                                break;
                            case "equal":
                                if (flattedRecord[field] !== filterParams[1]) { return false; }
                                break;
                            case "notEqual":
                                if (flattedRecord[field] === filterParams[1]) { return false; }
                                break;
                            case "in": { 
                                let result = false;
                                for (const val of filterParams[1]) {
                                    result = result || (flattedRecord[field] === val); 
                                    if (result) { break; }
                                }
                                if (!result) { return false; }
                                break;
                            }
                            case "notIn":
                                for (const val of filterParams[1]) {
                                    if (flattedRecord[field] === val) { return false; }
                                }
                                break;
                            default:
                                break;
                        }
                    } else {
                        const regExpPatternStr = filter[field];
                        const regExp = new RegExp(regExpPatternStr, "i");
                        if (!(regExp.test(flattedRecord[field]))) { return false; }
                    }
                }
                return true;
            });
        if (sort[1] === "DESC") { collection = collection.reverse(); }
        return collection.sortBy(sort[0]).then(rs => ({
            result: rs.slice(range[0], range[1] + 1),
            totalCount: rs.length,
        }));
    }

    getOne(resource, id) {
        const db = indexedDBInstance.get(this);
        return db.table(resource).get(id).then(r => ({ result: r }));
    }

    create(resource, data) {
        const db = indexedDBInstance.get(this);
        return db.table(resource)
            .add(data).then(rID => db.table(resource).get(rID)).then(r => ({ result: r }));
    }

    update(resource, id, data) {
        const db = indexedDBInstance.get(this);
        return db.table(resource).update(id, data).then((r) => {
            if (r) { return db.table(resource).get(id).then(r => ({ result: r })); } 
            throw new Error(`Update ${resource} id:${id} failed, the resource doesn't exist or provided data is the same with the orogin one`);
        });
    }

    delete(resource, id) {
        const db = indexedDBInstance.get(this);
        let prevR;
        return db.transaction("rw", db.table(resource), async () => {
            await db.table(resource).get(id).then((r) => {
                if (!r) { throw new Error(`Not found the ${resource} id:${id}`); }
                prevR = r;
            });
            await db.table(resource).delete(id);
            return db.table(resource).get(id).then((r) => {
                if (r) { throw new Error(`Delete operation failed for the ${resource} id:${id}`); }
                return { result: prevR };
            });
        });
    }
}

export default IndexedDBRest;
