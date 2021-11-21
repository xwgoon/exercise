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
import chineseMessages from "ra-language-chinese";

export default {
    ...chineseMessages,
    pos: {
        search: "Search",
        configuration: "Configuration",
        language: "Language",
        theme: {
            name: "Theme",
            light: "Light",
            dark: "Dark",
        },
        dashboard: {
            copyright_title: "图片版权登记",
            transaction_title: "交易信息浏览",
            block_title: "区块信息浏览",
            pending_reviews: "Pending Reviews",
            new_customers: "New Customers",
            pending_orders: "Pending Orders",
            order: {
                items:
                    "by %{customer_name}, one item |||| by %{customer_name}, %{nb_items} items",
            },
            welcome: {
                title: "CRBB",
                subtitle:
                    "This is the admin of an imaginary poster shop. Fell free to explore and modify the data - it's local to your computer, and will reset each time you reload.",
                aor_button: "react-admin site",
                demo_button: "Source for this demo",
            },
        },
    },
    resources: {
        Block: {
            name: "区块",
            tabs: {
                tab1: "基本项",
                tab2: "背书信息",
                tab3: "所含交易",
            },
            fields: {
                id: "序号",
                pid: "组网标识",
                hash: "本块哈希值",
                prevHash: "前块哈希值",
                stateHash: "世界状态哈希值",
                height: "高度",
                version: "版本号",
                transCount: "块内交易数",
                timestamp: "生成时间",
            },
        },
        NetPeer: {
            notification: {
                approved_success: "服务已启动",
                approved_error: "错误: 服务启动失败",
                rejected_success: "服务已停止",
                rejected_error: "错误: 服务停止失败、",
            },
            name: "节点",
            tabs: {
                tab1: "基本项",
                tab2: "私钥文件",
                tab3: "配置",
                tab4: "信任证书列表",
            },
            fields: {
                id: "序号",
                sid: "标识",
                addr: "地址",
                pid: "组网",
                cid: "证书",
                nodename: "节点名称",
                seedip: "种子节点IP地址",
                rtGraph: "实时图URL",
                created: "生成时间",
            },
        },
        Network: {
            name: "组网",
            tabs: {
                tab1: "基本项",
                tab2: "创世块",
                tab3: "配置",
                tab4: "信任证书列表",
                tab5: "区块列表",
                tab6: "私钥文件",
            },
            fields: {
                id: "序号",
                netId: "组网标识",
                config: "配置",
                genesisBlock: "创世区块",
                seedip: "种子节点IP地址",
                rtGraph: "实时图URL",
                name: "名称",
                created: "生成时间",
                transCount: "交易总数",
                blockCount: "区块高度",
                syncHeight: "同步高度",
            },
        },
        Transaction: {
            notification: {
                send_success: "签名交易[%{txId}]已提交到RepChain",
            },
            name: "交易",
            tabs: {
                tab1: "基本项",
                tab2: "合约",
            },
            fields: {
                id: "序号",
                txid: "交易标识",
                orderInBlock: "块内交易顺序",
                block: {
                    id: "区块标识",
                    hash: "区块标识",
                },
                chaincodeID: {
                    chaincodeName: "合约名称",
                    version: "合约版本号",
                },
                type: "交易类型",
                transactionType: "交易类型",
                chaincodeName: "合约名称",
                timestamp: "生成时间",
                chaincodeVersion: "合约版本号",
                chaincodeDeployParams: {
                    legalProse: "合约规则法律描述",
                    codePackage: "合约代码",
                    codeLanguageType: "合约代码语言",
                },
                chaincodeInvokeParams: {
                    function: "合约方法",
                    args: "合约方法参数",
                    chaincodeFunction: "合约方法",
                    chaincodeFunctionArgs: "合约方法参数",
                },
                chaincodeSetStateParams: {
                    state: "合约状态",
                },
                signature: {
                    signature: "交易签名",
                    certID: {
                        creditCode: "签名者信用代码",
                        certName: "签名者证书名称",
                        timestamp: "签名时间",
                    },
                },
            },
            button: {
                sign_submit: "签名提交",
            },
        },
        accounts: {
            name: "账号",
            tabs: {
                tab1: "基本项",
                tab2: "拥有的密钥",
                tab3: "发起的交易",
            },
            fields: {
                id: "序号",
                sn: "标识",
                name: "账号名",
                creditCode: "信用代码",
                mobile: "手机号",
                created: "生成时间",
                modified: "修改时间",
                org: "单位/机构",
                status: "状态",
            },
        },
        copRegs: {
            notification: {
                upload_success: "文件上传完成",
                upload_error: "错误: 文件上传失败",
                remove_success: "文件删除完成",
                remove_error: "错误: 文件删除失败",
            },
            name: "图片版权登记",
            fields: {
                id: "序号",
                copRegKey: "版权登记编号",
                status: "状态",
                image: {
                    hash: "图片哈希值",
                    hashAlg: "哈希算法",
                    name: "图片名称",
                    desc: "描述",
                },
                requestNotes: "申请登记备注",
                registerNotes: "确认登记备注",
                registerButtonLabel: "确认登记",
                rejectNotes: "拒绝登记备注",
                rejectButtonLabel: "拒绝登记",
                imagesToUpload: "申请登记的图片",
                password: "私钥解密密码",
            },
            titles: {
                passwordInputDialog: "请输入私钥解密密码",
            },
            buttons: {
                ok: "确定",
                cancel: "取消",
            },
            status: {
                REQUESTED: "已请求",
                REGISTERED: "已登记",
                REJECTED: "已拒绝",
            },
        },
        certs: {
            tabs: {
                tab1: "基本项",
                tab2: "证书",
                tab3: "密钥对",
                tab4: "密码保护",
            },
            name: "证书",
            fields: {
                id: "序号",
                sn: "密钥标识",
                algorithm: "生成算法",
                desc: "备注",
                prv_key: "私钥",
                pub_key: "公钥",
                pub_cert: "证书",
                sn_cert: "证书标识",
                created: "生成时间",
                fimp: "导出文件",
                pwd_old: "旧密码",
                pwd1: "请输入密码",
                pwd2: "再一次输入密码",
                status: "状态",
            },
        },
        keypairs: {
            tabs: {
                tab1: "基本项",
                tab2: "证书",
                tab3: "密钥对",
                tab4: "密码保护",
            },
            name: "密钥对",
            fields: {
                id: "序号",
                name: "证书名称",
                ownerID: "所属账号",
                algorithm: "生成算法",
                desc: "备注",
                prv_key: "私钥",
                pub_key: "公钥",
                pub_cert: "证书",
                createdAt: "生成时间",
                fimp: "导出文件",
                kp: {
                    sn: "密钥标识",
                    alg: {
                        name: "生成算法",
                        param: "生成算法参数",
                    },
                    pwdOld: "旧密码",
                    pwd1: "请输入密码",
                    pwd2: "再一次输入密码",
                },
                cert: {
                    sn: "证书标识",
                    certPEM: "pem格式信息",
                    status: "证书状态",
                },
            },
        },
        users: {
            name: "用户 |||| 用户列表",
            fields: {
                name: "姓名",
                id: "序号",
                sn: "标识",
            },
        },
        customers: {
            name: "Customer |||| Customers",
            fields: {
                commands: "Orders",
                groups: "Segments",
                last_seen_gte: "Visited Since",
                name: "Name",
                total_spent: "Total spent",
            },
            tabs: {
                identity: "Identity",
                address: "Address",
                orders: "Orders",
                reviews: "Reviews",
                stats: "Stats",
            },
            page: {
                delete: "Delete Customer",
            },
        },
        commands: {
            name: "Order |||| Orders",
            fields: {
                basket: {
                    delivery: "Delivery",
                    reference: "Reference",
                    quantity: "Quantity",
                    sum: "Sum",
                    tax_rate: "Tax Rate",
                    total: "Total",
                    unit_price: "Unit Price",
                },
                customer_id: "Customer",
                date_gte: "Passed Since",
                date_lte: "Passed Before",
                total_gte: "Min amount",
                status: "Status",
                returned: "Returned",
            },
        },
        products: {
            name: "Poster |||| Posters",
            fields: {
                category_id: "Category",
                height_gte: "Min height",
                height_lte: "Max height",
                height: "Height",
                image: "Image",
                price: "Price",
                reference: "Reference",
                stock_lte: "Low Stock",
                stock: "Stock",
                thumbnail: "Thumbnail",
                width_gte: "Min width",
                width_lte: "Max width",
                width: "Width",
            },
            tabs: {
                image: "Image",
                details: "Details",
                description: "Description",
                reviews: "Reviews",
            },
        },
        categories: {
            name: "Category |||| Categories",
            fields: {
                products: "Products",
            },
        },
        reviews: {
            name: "Review |||| Reviews",
            fields: {
                customer_id: "Customer",
                command_id: "Order",
                product_id: "Product",
                date_gte: "Posted since",
                date_lte: "Posted before",
                date: "Date",
                comment: "Comment",
                rating: "Rating",
            },
            action: {
                accept: "Accept",
                reject: "Reject",
            },
            notification: {
                approved_success: "Review approved",
                approved_error: "Error: Review not approved",
                rejected_success: "Review rejected",
                rejected_error: "Error: Review not rejected",
            },
        },
        segments: {
            name: "Segments",
            fields: {
                customers: "Customers",
                name: "Name",
            },
            data: {
                compulsive: "Compulsive",
                collector: "Collector",
                ordered_once: "Ordered once",
                regular: "Regular",
                returns: "Returns",
                reviewer: "Reviewer",
            },
        },
    },
    auth: {
        sign_up: "注册",
        username: "用户名",
        password: "密码",
        creditCode: "信用代码",
        certName: "证书名",
        phone: "手机号",
        rememberme: "记住我",
        keypairUpload: "选择导入密钥对文件",
        AUTHORITY_USER: "机构用户",
        NORMAL_USER: "普通用户",
        keypair: "导出密钥对",
    },
};
