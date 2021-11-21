## 背景介绍

RepChain是一款区块链基础平台，适用于构建联盟链及其应用。

我们的目标是构建一个基于RepChain的数喆版权登记（ADAS Copyright Register ,简称ACR）应用。

对数喆版权登记的特点与功能概述如下：

1. 若干组织机构基于RepChain组建需授权准入的区块链系统，即联盟链；
2. 联盟链依靠其防篡改特性，对用户的版权登记操作进行可信见证；
3. 版权登记操作包括：请求登记、确认登记或拒绝登记；
4. 应用中有2类用户角色：机构用户和普通用户
5. 图片版权登记请求功能：普通用户可以选择并上传其本地图片以请求登记版权;
6. 图片版权登记审核功能：机构用户可以对普通用户的版权登记申请进行审核，并进行确认登记或拒绝登记的操作;
7. 图片版权登记情况浏览：所有用户可以浏览查看系统中所有的图片版权登记情况，如登记状态、图片名称、图片描述等；
8. 图片版权登记状态分为3种：已请求、已登记和已拒绝



## 项目介绍

ACR( ADAS Copyright Register )，即基于区块链的版权登记应用。

本项目的目标，是构建基于RepChain区块链网络系统的图片版权登记保护应用。即实现不受特定中心化服务管理机构控制，由联盟共同建设、共同维护，能提供在线图片版权登记保护服务的应用。本应用，主要由应用客户端, 应用服务端和RepChain区块链网络服务端三部分构成，其核心功能是利用RepChain区块链作为可信见证方，为用户提供图片版权操作服务，包括图片版权登记申请、图片版权登记审核、图片版权登记信息浏览等。




### 技术组件
本项目所使用的主要开源技术组件包括:
- [RepChain](https://gitee.com/BTAJL/repchain)：轻量易用的区块链基础组件，可方便地建立许可链/联盟链网络环境;
- BAR([Base App of RepChain](https://gitee.com/linkel/bar)): RepChain基础应用，提供了区块链应用所需的基础功能，包括用户密钥对管理、公钥证书管理、交易数据同步与检索、区块数据同步与检索以及交易数据构建、交易提交等。本应用基于BAR的源码进行开发；
- RCJS([RepChain Client for JavaScript](https://gitee.com/BTAJL/RCJS))，能与RepChain交互的开发包，兼容Browser和Node环境；
- [repchain-synchronizer](https://gitee.com/linkel/repchain-synchronizer)，RepChain区块链数据同步工具；
- [react-admin](https://github.com/marmelab/react-admin)：基于ES6、React以及Material Design实现的前端框架；
- [Express](https://expressjs.com/)： NodeJS Web应用框架;
- [Prisma1](https://v1.prisma.io/docs/1.34/)：基于[GraphQL](https://graphql.org/)的数据库访问管理中间件，可替代传统ORMs，并使得底层数据库对开发者透明;
- [Docker](https://www.docker.com/)：本项目使用了Docker来管理运行[Prisma1 Server](https://hub.docker.com/r/prismagraphql/prisma)以及应用服务端本地数据库(默认为[MySQL](https://hub.docker.com/_/mysql))

## 应用架构

下图展示了数喆版权登记的系统架构

![架构图](https://gitee.com/linkel/CRBBV1.0/raw/master/docs/%E6%9E%B6%E6%9E%84%E5%9B%BE.png)

主要构成有：

- RepChain区块链网络
- 应用服务端
- 应用客户端



### RepChain区块链数据同步工具repchain-synchronizer

组建RepChain联盟链的节点本身，专注于执行交易及共识等任务，目前未提供繁重的数据查询服务。而我们需要从底层的区块链数据中构建上层的应用业务数据，这需要借助区块链数据同步工具repchain-synchronizer。



### 应用服务端

![Server](https://gitee.com/linkel/CRBBV1.0/raw/master/docs/%E6%9E%B6%E6%9E%84-Server.png)

应用服务端，为版权登记业务提供必要的支撑服务。包括：

- 利用repchain-synchronizer从区块链数据中同步版权登记信息，并向应用客户端提供版权登记信息检索浏览服务；
- 接收及存储应用客户端申请登记版权时提交的图片，并向应用客户端提供图片检索浏览服务；
- 基于repchain-synchronizer从区块链数据中同步得到的数据，向应用客户端提供账户注册与登录身份验证服务；
- 基于参与构建RepChain联盟链的某个组织机构的数字身份与密钥材料，构建及提交链码部署交易到RepChain中，以部署版权登记链码



### 应用客户端

![Client](https://gitee.com/linkel/CRBBV1.0/raw/master/docs/%E6%9E%B6%E6%9E%84-Client.png)

为用户提供应用的业务操作接口，包括：

- 非对称密钥/数字证书管理：
  - 生成普通用户的非对称密钥，并对私钥进行加密保护存储；
  - 生成普通用户的自签名数字证书；
  - 导入用户的非对称密钥/数字证书信息；
  - 导出用户的非对称密钥/数字证书信息。
- 信息检索浏览：
  - 所有用户，可检索浏览版权登记信息；
  - 所有用户，可检索浏览系统中的所有区块链区块信息；
- 版权登记操作：
  - 普通用户，可申请登记图片版权；
  - 机构用户，可审核版权登记请求，并确认登记或拒绝登记；
  - 根据不同的版权登记操作，构建调用版权登记链码的签名交易，并直接与RepChain交互，将交易提交给RepChain联盟链。



### 环境准备

若需要自行搭建环境并安装运行本应用，可参考以下内容：

1. 安装Git(V2+)
2. 安装JDK(V1.8 or V13)
3. 安装node，推荐使用npm安装管理node
4. 安装yarn
    ```bash
    npm i -g yarn
    ```
5. 安装node-gyp
```bash
yarn global add node-gyp
```
6. 安装编译工具make和g++
7. 安装docker以及docker-compose

### 运行
#### 安装依赖包并部署prisma
使用git工具将本项目clone到本地之后，进入项目根目录, 运行以下命令:
- 安装依赖(同时安装前端以及后端各自的依赖包)
  ```bash
  yarn install-all
  ```
- 为后端创建运行prisma以及数据库的docker容器，并部署prisma
  ```bash
  yarn deploy
  ```
  > Note: 若当前用户没有运行docker命令的权限，则需要赋予其相应的权限，如在上述命令前使用sudo或将当前用户加入docker用户组

#### 后端配置
进入目录: server/config, 可通过json格式的配置文件进行配置：
- default.json: 正式运行环境下的配置内容
- test.json: 开发测试环境下的配置内容

主要配置项包括：
- File.fileUploadDir： 文件上传时的暂存目录
- File.fileStorageDir: 文件被正式存储的目录
- File.uploadLimits.fields: 上传文件时multipart/form-data的最大项目数
- File.uploadLimits.fileSize: 上传文件时每个文件的最大限制(字节数)
- File.uploadLimits.files: 单次上传文件时允许的最多文件数

- Log.api: 后端对Restful api调用情况的日志记录管理
- Log.serverInner: 后端对功能逻辑运行情况的日志记录管理

- ServerCrypto.prvKeyFilePath: 后端使用的私钥文件的存储路径，该私钥将被用于区块链交易的构建
- ServerCrypto.prvKeyPassword: 后端所使用私钥的解密密码(若该私钥已被加密，需要提供该密码以解密使用)
- ServerCrypto.pubKeyFilePath: 后端所使用的公钥证书文件的存储路径，该公钥将被用于区块链交易的构建
- ServerCrypto.alg: 后端构建签名交易所使用的算法
- ServerCrypto.creditCode: 后端所使用的RepChain账户唯一标识
- ServerCrypto.certName: 后端所使用的RepChain账户证书名称
- ServerCrypto.jwtSignAlg: 后端生成Json Web Token时使用的签名算法
- ServerCrypto.jwtSecrect: 后端上传Json Web Token时使用的密钥

- RepChain.default.url_subscribe: RepChain节点的Websocket API地址，用于同步RepChain区块链数据时的实时推送同步
- RepChain.default.url_api: RepChain节点的Restful API地址，用于同步RepChain区块链数据时的主动获取同步
- RepChain.server: RepChain代理节点的地址，用于提交区块链交易
- RepChain.port: RepChain代理节点的服务端口，用于提交区块链交易
- RepChain.chaincodeName: 图片版权登记智能合约名称
- RepChain.chaincodeVersion: 图片版权登记智能合约版本号



#### 前端配置
在项目根目录下进入目录: src, 可编辑配置文件settings.js。
主要配置项目包括：
- RepChain.default.url_api: RepChain代理节点的Restful API地址，用于前端提交区块链交易
- Repchain.chaincodeName: 前端所构建交易调用的链码名称
- Repchain.chaincodeVersion: 前端所构建交易调用链码的版本号

- Prisma.endpoint: prisma server http服务地址
- Prisma.url_subscribe: prisma server websocket服务地址

- Server.wsBaseUrl: 后端websocket服务地址

> 
