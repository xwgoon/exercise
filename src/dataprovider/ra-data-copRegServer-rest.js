/* eslint-disable no-undef */
import {
    GET_LIST,
    GET_ONE,
} from "react-admin";
import { stringify } from "query-string";

const log = (type, resource, params) => {
    console.log(`copRegServer-rest request:\n type: ${type}\n resource: ${resource}\n params: ${JSON.stringify(params)}`);
};

const getCollectionData = async (type, resource, params) => {
    const { filter } = params;

    const { page, perPage } = params.pagination;
    const { field, order } = params.sort;
    const urlPath = `/${resource}`;
    const query = {
        page,
        perPage,
        sortBy: `${field}_${order}`,
        ...filter,
        timestamp: Date.now(),
    };
    const url = `${urlPath}?${stringify(query)}`;
    return fetch(url, {
        headers: {
            token: localStorage.getItem("token"),
        },
    }).then((response) => {
        if (response.status === 401 || response.status === 403) {
            const err = new Error();
            err.status = response.status;
            throw err;
        }
        if (response.status !== 200) throw new Error(response.body.message);
        return response.json();
    }).then((res) => {
        if (resource === "copRegs") {
            return {
                data: res.copRegList,
                total: res.totalCount,
            };
        }
        return {
            data: [],
            total: 0,
        };
    });
};

const getOneData = async (type, resource, params) => {
    let urlPath = "";
    const query = {
    };
    if (type === GET_ONE) {
        urlPath = `/${resource}/${params.id}`;
    }
    if (type === "DOWNLOAD") {
        urlPath = params.url;
        query.width = params.width;
        query.height = params.height;
    }

    const url = urlPath;

    return fetch(url, {
        headers: {
            token: localStorage.getItem("token"),
        }
    }).then((response) => {
        if (response.status === 401 || response.status === 403) {
            const err = new Error();
            err.status = response.status;
            throw err;
        } else if (type === "DOWNLOAD") {
            return response;
        } else if (type === GET_ONE) {
            return response.json().then((res) => {
                if (resource === "copRegs") {
                    return { data: res.copReg };
                }
                return { data: null };
            });
        }
        return { data: null };
    });
};

export default (type, resource, params) => {
    log(type, resource, params);

    switch (type) {
        case GET_LIST:
            return getCollectionData(type, resource, params);
        case GET_ONE:
        case "DOWNLOAD":
            return getOneData(type, resource, params);
        default:
            return 0;
    }
};
