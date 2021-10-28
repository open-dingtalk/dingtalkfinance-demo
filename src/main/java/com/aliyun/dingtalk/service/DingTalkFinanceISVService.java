/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.aliyun.dingtalk.service;

import com.aliyun.dingtalk.model.AvailableTime;
import com.aliyun.dingtalkfinance_1_0.models.DecodePayCodeResponse;
import com.aliyun.dingtalkfinance_1_0.models.NotifyPayCodePayResultRequest;
import com.aliyun.dingtalkfinance_1_0.models.NotifyPayCodeRefundResultRequest;
import com.aliyun.dingtalkfinance_1_0.models.SaveCorpPayCodeResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author shiyan
 * @version $Id: DingTalkFinanceISVService.java, v 0.1 2021-10-20 下午8:21 shiyan Exp $$
 */
public interface DingTalkFinanceISVService {
    /**
     * 获取token
     *
     * @return suite-access-token
     * @throws Exception
     */
    String getAccessToken() throws Exception;

    /**
     * 创建钉工牌码用户实例
     * <p>
     * 调用本接口,为用户创建钉工牌码实例,主要用于访客、会展等临时证场景。
     * https://developers.dingtalk.com/document/app/create-a-user-code-instance
     * </p>
     *
     * @param requestId            业务幂等ID（必填）
     * @param codeIdentity         码标识，由钉钉颁发，DT_VISITOR: 访客码 DT_CONFERENCE: 会展码，请阅读链接文档（必填）
     * @param codeValue            码值 固定码不为空，动态码可空（可空）
     * @param status               OPEN：开启 CLOSED：关闭 INVALID：失效，传入关闭状态需要用户手动开启后才会渲染二维码。（必填）
     * @param corpId               企业corpId （必填）
     * @param userCorpRelationType INTERNAL_STAFF：企业内部员工 EXTERNAL_CONTACT：外部联系人 NO_RELATION：普通用户与组织无关 （必填）
     * @param userIdentity         企业内部员工传入staffId，外部联系人传入外部联系人ID，无关系用户传入手机号 （必填）
     * @param gmtExpired           临时码过期时间，格式：yyyy-MM-dd HH:mm:ss 注意：时间要精确到秒
     * @param availableTimes       可用时间段
     * @return 码值
     * @throws Exception
     */
    String createCodeUserInstance(String requestId, String codeIdentity, String codeValue, String status,
                                  String corpId, String userCorpRelationType, String userIdentity, String gmtExpired,
                                  List<AvailableTime> availableTimes, Map<String, ?> extInfo) throws Exception;

    /**
     * 更新钉工牌用户实例
     * <p>
     * 更新用户钉工牌码的相关信息
     * https://developers.dingtalk.com/document/app/update-user-code-instance
     * </p>
     *
     * @param codeId               用户码ID 创建接口返回值
     * @param codeIdentity         码标识，由钉钉颁发，需联系钉钉技术同学说明使用场景，请阅读链接文档（必填）
     * @param codeValue            码值 固定码不为空，动态码可空（可空）
     * @param status               OPEN：开启 CLOSED：关闭 INVALID：失效，传入关闭状态需要用户手动开启后才会渲染二维码。
     * @param corpId               企业corpId
     * @param userCorpRelationType INTERNAL_STAFF：企业内部员工 EXTERNAL_CONTACT：外部联系人 NO_RELATION：普通用户与组织无关
     * @param userIdentity         企业内部员工传入staffId，外部联系人传入外部联系人ID，无关系用户传入手机号
     * @param gmtExpired           临时码过期时间，格式：yyyy-MM-dd HH:mm:ss 注意：时间要精确到秒
     * @param availableTimes       可用时间段, 格式：yyyy-MM-dd HH:mm:ss 注意：时间要精确到秒
     * @return 码ID
     * @throws Exception
     */
    String updateCodeUserInstance(String codeId, String codeIdentity, String codeValue, String status, String corpId,
                                  String userCorpRelationType, String userIdentity, String gmtExpired,
                                  List<AvailableTime> availableTimes, Map<String, ?> extInfo) throws Exception;

    /**
     * 解码钉工牌码
     * <p>
     * 调用本接口解析钉工牌电子码，获取关联的企业、用户userid等信息。
     * https://developers.dingtalk.com/document/app/decoding-dingtalk-payment-code
     * </p>
     *
     * @param payCode   码值 （硬件扫描获取码值）
     * @param requestId 请求ID （两次解码请求需要传相同的请求ID，否则会解码失败）
     * @throws Exception
     */
    DecodePayCodeResponse decodeCode(String payCode, String requestId) throws Exception;

    /**
     * 同步钉工牌码支付结果
     * <p>
     * <p/>
     * 用户使用钉工牌码扫码支付完成后，可调用本接口同步支付结果，并通知用户完成消费，同时为用户记录账单。
     * https://developers.dingtalk.com/document/app/notify-dingtalk-payment-code-payment-result
     * </p>
     *
     * @param payCode              码值
     * @param corpId               组织ID
     * @param userId               用户ID
     * @param gmtTradeCreate       交易创建时间 时间格式："yyyy-MM-dd HH:mm:ss"
     * @param gmtTradeFinish       交易完成时间 时间格式："yyyy-MM-dd HH:mm:ss"
     * @param tradeNo              交易号
     * @param tradeStatus          交易状态
     * @param title                订单名称
     * @param remark               备注
     * @param amount               金额
     * @param promotionAmount      优惠金额
     * @param chargeAmount         收费金额。支付宝收取费用
     * @param payChannelDetailList 支付渠道明细信息。tradeStatus为SUCCESS（支付成功），支付渠道信息则必传。如果tradeStatus为FAIL（支付失败），支付信息可为空，同时建议传递tradeErrorCode, tradeErrorMsg，用于告知用户扣款失败原因。
     * @param tradeErrorCode       失败错误码
     * @param tradeErrorMsg        失败原因信息
     * @param extInfo              扩展属性
     * @param merchantName         商户名称
     * @return
     * @throws Exception
     */
    String notifyCodePayResult(String payCode, String corpId, String userId, String gmtTradeCreate, String gmtTradeFinish,
                               String tradeNo, String tradeStatus, String title, String remark, String amount,
                               String promotionAmount, String chargeAmount, List<NotifyPayCodePayResultRequest.NotifyPayCodePayResultRequestPayChannelDetailList> payChannelDetailList,
                               String tradeErrorCode, String tradeErrorMsg, String extInfo, String merchantName) throws Exception;

    /**
     * 通知钉工牌码退款结果
     * <p/>
     * 用户使用钉工牌码支付后，如果发生退款，退款完成后，调用本接口同步退款结果，生成对应账单。
     * https://developers.dingtalk.com/document/app/dingtalk-payment-code-refund-information-synchronization-operation
     * </p>
     *
     * @param corpId                                  企业ID
     * @param userId                                  用户ID
     * @param tradeNo                                 交易单号，和通知支付结果时候的单号保持一致
     * @param refundOrderNo                           退款单号
     * @param remark                                  备注
     * @param refundAmount                            退款金额
     * @param refundPromotionAmount                   退款的优惠金额。
     * @param gmtRefund                               退款时间。
     * @param payCode                                 支付时使用的付款码。
     * @param payChannelDetailList0FundToolDetailList 支付渠道明细信息。如果tradeStatus为SUCCESS（支付成功），支付渠道信息则必传。如果tradeStatus为FAIL（支付失败），支付信息可为空，同时建议传递tradeErrorCode, tradeErrorMsg，用于告知用户扣款失败原因。
     * @return 退款结果
     * @throws Exception
     */
    String notifyCodeRefundResult(String corpId, String userId, String tradeNo, String refundOrderNo, String remark, String refundAmount,
                                  String refundPromotionAmount, String gmtRefund, String payCode,
                                  List<NotifyPayCodeRefundResultRequest.NotifyPayCodeRefundResultRequestPayChannelDetailList> payChannelDetailList0FundToolDetailList) throws Exception;

    /**
     * 同步钉工牌码验证结果
     * <p>
     * 用户扫码验证后，调用本接口同步验证结果。
     * https://developers.dingtalk.com/document/app/sync-pin-badge-code-verification-result
     * </p>
     *
     * @param payCode              码值。
     * @param corpId               企业corpId。
     * @param userCorpRelationType 用户和企业的关系类型，用于区分内部员工，外部联系人，无关系普通用户。
     * @param userIdentity         用户身份标识。取值和userCorpRelationType值有关
     * @param verifyTime           验证时间。
     * @param verifyResult         验证结果。
     * @param verifyLocation       验证地点。
     * @return 验证结果
     * @throws Exception
     */
    String notifyVerifyResult(String payCode, String corpId, String userCorpRelationType, String userIdentity, String verifyTime,
                              boolean verifyResult, String verifyLocation) throws Exception;

    /**
     * 配置企业钉工牌
     * <p>
     * 调用本接口配置企业钉工牌。
     * https://developers.dingtalk.com/document/app/set-up-enterprise-payment-code-configuration-interface
     * </p>
     *
     * @param corpId       开通的企业corpId。
     * @param codeIdentity 码标识，由钉钉颁发
     * @param status       状态，取值。OPEN：开启 CLOSED：关闭
     * @param extInfo      扩展属性（可空）
     * @return 配置结果
     * @throws Exception
     */
    SaveCorpPayCodeResponseBody saveCorpPayCode(String corpId, String codeIdentity, String status, Map<String, String> extInfo) throws Exception;
}