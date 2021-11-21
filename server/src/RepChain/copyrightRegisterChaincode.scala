/*
 * Copyright  2019 Blockchain Technology and Application Joint Lab, Linkel Technology Co., Ltd, Beijing, Fintech Research Center of ISCAS.
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

// image copyright register
package rep.sc.cr

// 外界调用链码方法时传入的参数是Json字符串，需要进行解析
// 并且我们在存储worldState的value时其值也使用Json字符串
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods.parse
import org.json4s.jackson.Serialization.{write, read}
import rep.protos.peer.ActionResult
import rep.sc.scalax.{ContractContext, IContract, ContractException}

final case class RequestParams( // 请求登记方法接受的参数结构
    hashArray: Array[String], // 图片的哈希值(支持一次对多张图片申请登记版权)
    hashAlg: String,          // 计算图片哈希值所用哈希算法
    desc: String,             // 对图片的描述
    name: String              // 图片的名称
);
final case class ReviewParams( // 确认登记方法和拒绝登记方法接受的参数结构
    hashArray: Array[String], // 图片的哈希值(支持一次确认/拒绝多张图片的版权请求)
    notes: String             // 确认登记或拒绝登记时的备注信息
);

class CopyrightRegister extends IContract { // 链码/合约必需继承IContract类

    val STATUS_REQUESTED = "REQUESTED";
    val STATUS_REGISTERED = "REGISTERED";
    val STATUS_REJECTED = "REJECTED";

    // 记录影响版权登记信息的相关交易ID的数据结构
    final case class Txs (requestTx: String, registerTx: String, rejectTx: String);
    // 记录影响版权登记信息的相关备注信息的数据结构
    final case class Notes (requestNotes: String, registerNotes: String, rejectNotes: String);

    final case class CopyrightRegistration( // 用于持久化版权登记信息worldState的数据结构，
        status: String, // 版权登记状态
        owner: String,  // 版权主张者的账户Id
        hash: String,   // 图片哈希值 
        hashAlg: String,// 计算图片哈系值时使用的哈希算法
        desc: String,   // 图片描述
        name: String,   // 图片名称
        txs: Txs,       // 相关交易
        notes: Notes    // 相关备注信息
    );

    // Json序列化与反序列化时使用的格式
    implicit val formats = DefaultFormats;

    // 链码的初始化方法
    override def init(ctx: ContractContext) {
        println(s"init copyright register contract with tx: ${ctx.t.id}")
    }

    /**
    * 链码的请求登记方法
    *
    * @param ctx 
    * @param data
    * @return
    */
    def request(ctx: ContractContext, data: RequestParams): ActionResult = {
        // 通过ContractContext可以获得当前所执行交易的信息
        for(i <- 0 until data.hashArray.length) {
            val key = data.hashArray(i);
            val pvAny = ctx.api.getVal(key);
            if(pvAny != null) {
                // 通过使用ContractException可以抛出链码执行异常
                throw ContractException(s"图像(hash: ${key})版权登记申请记录已存在，不能再次申请");
            }
            var imageName = data.name;
            if (data.hashArray.length > 1) {
                imageName += "-" + i
            }
            val value = CopyrightRegistration(
                STATUS_REQUESTED, 
                ctx.t.getSignature.getCertId.creditCode,
                key,
                data.hashAlg,
                data.desc,
                imageName,
                txs = Txs(ctx.t.id, "", ""),
                notes = Notes("copyright register request", "", "")
            );
            // 对value序列化为Json字符串后再持久化
            ctx.api.setVal(key, write(value));
            println("copyright registration request: "+ key + ": " + value);
        }
        
        null
    }

    /**
    * 链码的确认登记方法
    *
    * @param ctx 
    * @param data
    * @return
    */
    def register(ctx: ContractContext, data: ReviewParams): ActionResult = {
        val isAuthority = ctx.api.bNodeCreditCode(ctx.t.getSignature.getCertId.creditCode)
        if (!isAuthority) {
            throw ContractException(s"发起者不是机构用户，拒绝该操作!");
        }

        for(hash <- data.hashArray) {
            val key = hash;
            val pvAny = ctx.api.getVal(key);
            if(pvAny == null) {
                throw ContractException(s"图像(hash: ${key})版权未被预先申请登记，不能确认登记");
            } else if (read[CopyrightRegistration](pvAny.asInstanceOf[String]).status != STATUS_REQUESTED) {
                throw ContractException(s"图像(hash: ${key})版权登记已被确认或拒绝，不能再确认登记");
            }
            var pv = read[CopyrightRegistration](pvAny.asInstanceOf[String]);
            var value = pv.copy(
                status = STATUS_REGISTERED,
                txs = pv.txs.copy(registerTx = ctx.t.id),
                notes = pv.notes.copy(registerNotes = data.notes)
            );
            ctx.api.setVal(key, write(value));
            println("copyright registration register: "+ key + ": " + value);
        }
        
        null
    }

    /**
    * 链码的拒绝登记方法
    *
    * @param ctx 
    * @param data
    * @return
    */
    def reject(ctx: ContractContext, data: ReviewParams): ActionResult = {
        val isAuthority = ctx.api.bNodeCreditCode(ctx.t.getSignature.getCertId.creditCode)
        if (!isAuthority) {
            throw ContractException(s"发起者不是机构用户，拒绝该操作!");
        }

        for(hash <- data.hashArray) {
            val key = hash;
            val pvAny = ctx.api.getVal(key);
            if(pvAny == null) {
                throw ContractException(s"图像(hash: ${key})版权未被预先申请登记，不能拒绝登记");
            } else if (read[CopyrightRegistration](pvAny.asInstanceOf[String]).status != STATUS_REQUESTED) {
                throw ContractException(s"图像(hash: ${key})版权登记已被确认或拒绝，不能再拒绝登记");
            }
            var pv = read[CopyrightRegistration](pvAny.asInstanceOf[String]);
            var value = pv.copy(
                status = STATUS_REJECTED,
                txs = pv.txs.copy(rejectTx = ctx.t.id),
                notes = pv.notes.copy(rejectNotes = data.notes)
            );
            ctx.api.setVal(key, write(value));
            println("copyright registration reject: "+ key + ": " + value);
        }

        null
    }   

    // 对外界提供的链码方法名称分别为Request、Register和Reject
    // 外界调用上述链码方法时提供的参数必须为满足RequestParams或ReviewParams结构的Json字符串
    override def onAction(ctx: ContractContext, action: String, sdata: String): ActionResult = {
        println("-----------"+sdata);

        val json = parse(sdata);

        action match { 
            case "Request" =>
                request(ctx, json.extract[RequestParams])
            case "Register" =>
                register(ctx, json.extract[ReviewParams])
            case "Reject" =>
                reject(ctx, json.extract[ReviewParams])
        }
    }
}