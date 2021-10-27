/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.aliyun.dingtalk.service.impl;

import com.aliyun.dingtalk.common.AccountInitManager;
import com.aliyun.dingtalk.common.AppConfig;
import com.aliyun.dingtalk.service.DingTalkFinanceService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import com.aliyun.dingtalkbadge_1_0.Client;
import com.aliyun.dingtalkbadge_1_0.models.CreateBadgeCodeUserInstanceHeaders;
import com.aliyun.dingtalkbadge_1_0.models.CreateBadgeCodeUserInstanceRequest;
import com.aliyun.dingtalkbadge_1_0.models.CreateBadgeCodeUserInstanceResponse;
import com.aliyun.dingtalkbadge_1_0.models.DecodeBadgeCodeHeaders;
import com.aliyun.dingtalkbadge_1_0.models.DecodeBadgeCodeRequest;
import com.aliyun.dingtalkbadge_1_0.models.DecodeBadgeCodeResponse;
import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodePayResultHeaders;
import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodePayResultRequest;
import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodePayResultResponse;
import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodeRefundResultHeaders;
import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodeRefundResultRequest;
import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodeRefundResultResponse;
import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodeVerifyResultHeaders;
import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodeVerifyResultRequest;
import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodeVerifyResultResponse;
import com.aliyun.dingtalkbadge_1_0.models.SaveBadgeCodeCorpInstanceHeaders;
import com.aliyun.dingtalkbadge_1_0.models.SaveBadgeCodeCorpInstanceRequest;
import com.aliyun.dingtalkbadge_1_0.models.SaveBadgeCodeCorpInstanceResponse;
import com.aliyun.dingtalkbadge_1_0.models.SaveBadgeCodeCorpInstanceResponseBody;
import com.aliyun.dingtalkbadge_1_0.models.UpdateBadgeCodeUserInstanceHeaders;
import com.aliyun.dingtalkbadge_1_0.models.UpdateBadgeCodeUserInstanceRequest;
import com.aliyun.dingtalkbadge_1_0.models.UpdateBadgeCodeUserInstanceResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 钉工牌接口服务管理（三方企业使用）
 *
 * @author shiyan
 * @version $Id: DingTalkFinanceServiceImpl.java, v 0.1 2021-10-20 下午3:40 shiyan Exp $$
 */
@Service
public class DingTalkFinanceServiceImpl implements DingTalkFinanceService {
    /**
     * 账户生成管理器
     */
    @Autowired
    AccountInitManager accountInitManager;

    /**
     * 应用配置类
     */
    @Autowired
    AppConfig appConfig;

    /**
     * 获取access_token工具类 （三方企业使用
     */
    @Autowired
    AccessTokenUtil accessTokenUtil;

    /**
     * 获取token
     *
     * @return access-token
     * @throws Exception
     */
    @Override
    public String getAccessToken() {
        return accessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());
    }

    /**
     * 创建钉工牌码用户实例
     * <p>
     * 调用本接口,为用户创建钉工牌码实例,主要用于访客、会展等临时证场景。
     * https://developers.dingtalk.com/document/app/create-a-badge-user-instance
     * </p>
     *
     * @param requestId            业务幂等ID（必填）
     * @param codeIdentity         码标识，由钉钉颁发，需联系钉钉技术同学说明使用场景，请阅读链接文档（必填）
     * @param codeValue            码值 固定码不为空，动态码可空（可空）
     * @param status               OPEN：开启 CLOSED：关闭 INVALID：失效，传入关闭状态需要用户手动开启后才会渲染二维码。（必填）
     * @param corpId               企业corpId （必填）
     * @param userCorpRelationType INTERNAL_STAFF：企业内部员工 EXTERNAL_CONTACT：外部联系人 NO_RELATION：普通用户与组织无关 （必填）
     * @param userIdentity         企业内部员工传入staffId，外部联系人传入外部联系人ID，无关系用户传入手机号 （必填）
     * @param gmtExpired           临时码过期时间，格式：yyyy-MM-dd HH:mm:ss 注意：时间要精确到秒
     * @param gmtStart             有效时间，格式：yyyy-MM-dd HH:mm:ss 注意：时间要精确到秒
     * @param gmtEnd               结束时间，格式：yyyy-MM-dd HH:mm:ss 注意：时间要精确到秒
     * @return 码值
     * @throws Exception
     */
    @Override
    public String createBadgeCodeUserInstance(String requestId, String codeIdentity, String codeValue, String status,
                                              String corpId, String userCorpRelationType, String userIdentity, String gmtExpired,
                                              String gmtStart, String gmtEnd, Map<String, ?> extInfo) throws Exception {
        // 1、创建账户client
        Client client = accountInitManager.createClient();
        // 2、获取accessToken
        String accessToken = accessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());


        // 3、构建请求参数
        CreateBadgeCodeUserInstanceHeaders createBadgeCodeUserInstanceHeaders = new CreateBadgeCodeUserInstanceHeaders();
        createBadgeCodeUserInstanceHeaders.xAcsDingtalkAccessToken = accessToken;
        // 有效时间列表，对于连续时间段，只需传入一个对象即可，过期时间必须晚于最晚结束时间。
        CreateBadgeCodeUserInstanceRequest.CreateBadgeCodeUserInstanceRequestAvailableTimes availableTimes0 = new CreateBadgeCodeUserInstanceRequest.CreateBadgeCodeUserInstanceRequestAvailableTimes()
                .setGmtStart(gmtStart)
                .setGmtEnd(gmtEnd);
        CreateBadgeCodeUserInstanceRequest createBadgeCodeUserInstanceRequest = new CreateBadgeCodeUserInstanceRequest()
                .setRequestId(requestId)
                .setCodeIdentity(codeIdentity)
                .setCodeValue(codeValue)
                .setStatus(status)
                .setCorpId(corpId)
                .setUserCorpRelationType(userCorpRelationType)
                .setUserIdentity(userIdentity)
                .setGmtExpired(gmtExpired)
                .setExtInfo(extInfo)
                .setAvailableTimes(Collections.singletonList(
                        availableTimes0
                ));

        CreateBadgeCodeUserInstanceResponse response = new CreateBadgeCodeUserInstanceResponse();
        try {
            response = client.createBadgeCodeUserInstanceWithOptions(createBadgeCodeUserInstanceRequest, createBadgeCodeUserInstanceHeaders, new RuntimeOptions());
            return response.body.codeId;
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);
            }
        }
        return null;
    }

    /**
     * 更新钉工牌用户实例
     * <p>
     * 更新用户钉工牌码的相关信息
     * https://developers.dingtalk.com/document/app/update-dingtalk-user-instance
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
     * @param gmtStart             有效时间，格式：yyyy-MM-dd HH:mm:ss 注意：时间要精确到秒
     * @param gmtEnd               结束时间，格式：yyyy-MM-dd HH:mm:ss 注意：时间要精确到秒
     * @return 码ID
     * @throws Exception
     */
    @Override
    public String updateBadgeCodeUserInstance(String codeId, String codeIdentity, String codeValue, String status, String corpId,
                                              String userCorpRelationType, String userIdentity, String gmtExpired, String gmtStart,
                                              String gmtEnd, Map<String, ?> extInfo) throws Exception {
        // 1、获取accessToken
        String accessToken = accessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());
        // 2、创建账户client
        Client client = accountInitManager.createClient();

        // 3、构建请求参数
        UpdateBadgeCodeUserInstanceHeaders updateBadgeCodeUserInstanceHeaders = new UpdateBadgeCodeUserInstanceHeaders();
        updateBadgeCodeUserInstanceHeaders.xAcsDingtalkAccessToken = accessToken;
        // 有效时间段需精确到时分秒，
        UpdateBadgeCodeUserInstanceRequest.UpdateBadgeCodeUserInstanceRequestAvailableTimes availableTimes0 = new UpdateBadgeCodeUserInstanceRequest.UpdateBadgeCodeUserInstanceRequestAvailableTimes()
                .setGmtStart(gmtStart)
                .setGmtEnd(gmtEnd);

        UpdateBadgeCodeUserInstanceRequest updateBadgeCodeUserInstanceRequest = new UpdateBadgeCodeUserInstanceRequest()
                .setCodeId(codeId)
                .setCodeIdentity(codeIdentity)
                .setCodeValue(codeValue)
                .setStatus(status)
                .setCorpId(corpId)
                .setUserCorpRelationType(userCorpRelationType)
                .setUserIdentity(userIdentity)
                .setGmtExpired(gmtExpired)
                .setExtInfo(extInfo)
                .setAvailableTimes(java.util.Arrays.asList(
                        availableTimes0
                ));
        UpdateBadgeCodeUserInstanceResponse response = new UpdateBadgeCodeUserInstanceResponse();
        try {
            response = client.updateBadgeCodeUserInstanceWithOptions(updateBadgeCodeUserInstanceRequest, updateBadgeCodeUserInstanceHeaders, new RuntimeOptions());
            return response.body.codeId;
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }
        }
        return null;
    }

    /**
     * 解码钉工牌码
     * <p>
     * 调用本接口解析钉工牌电子码，获取关联的企业、用户userid等信息。
     * https://developers.dingtalk.com/document/app/stack-dingtalk-badge
     * </p>
     *
     * @param payCode   码值 （硬件扫描获取码值）
     * @param requestId 请求ID （两次解码请求需要传相同的请求ID，否则会解码失败）
     * @throws Exception
     */
    @Override
    public DecodeBadgeCodeResponse decodeBadgeCode(String payCode, String requestId) throws Exception {
        // 1、获取accessToken
        String accessToken = accessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());
        // 2、创建账户client
        Client client = accountInitManager.createClient();

        // 3、构建请求参数
        DecodeBadgeCodeHeaders decodeBadgeCodeHeaders = new DecodeBadgeCodeHeaders();
        decodeBadgeCodeHeaders.xAcsDingtalkAccessToken = accessToken;
        DecodeBadgeCodeRequest decodeBadgeCodeRequest = new DecodeBadgeCodeRequest()
                .setPayCode(payCode)
                .setRequestId(requestId);
        DecodeBadgeCodeResponse response = new DecodeBadgeCodeResponse();
        try {
            return client.decodeBadgeCodeWithOptions(decodeBadgeCodeRequest, decodeBadgeCodeHeaders, new RuntimeOptions());
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }
        }

        return null;
    }

    /**
     * 同步钉工牌码支付结果
     * <p>
     * <p/>
     * 用户使用钉工牌码扫码支付完成后，可调用本接口同步支付结果，并通知用户完成消费，同时为用户记录账单。
     * https://developers.dingtalk.com/document/app/sync-dingtalk-badge-code-payment-result
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
    @Override
    public String notifyBadgeCodePayResult(String payCode, String corpId, String userId, String gmtTradeCreate, String gmtTradeFinish,
                                           String tradeNo, String tradeStatus, String title, String remark, String amount,
                                           String promotionAmount, String chargeAmount, List<NotifyBadgeCodePayResultRequest.NotifyBadgeCodePayResultRequestPayChannelDetailList> payChannelDetailList,
                                           String tradeErrorCode, String tradeErrorMsg, String extInfo, String merchantName) throws Exception {
        // 1、获取accessToken
        String accessToken = accessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());
        // 2、创建账户client
        Client client = accountInitManager.createClient();

        // 3、构建请求参数
        NotifyBadgeCodePayResultHeaders notifyBadgeCodePayResultHeaders = new NotifyBadgeCodePayResultHeaders();
        notifyBadgeCodePayResultHeaders.xAcsDingtalkAccessToken = accessToken;
        NotifyBadgeCodePayResultRequest.NotifyBadgeCodePayResultRequestPayChannelDetailListFundToolDetailList payChannelDetailList0FundToolDetailList0 = new NotifyBadgeCodePayResultRequest.NotifyBadgeCodePayResultRequestPayChannelDetailListFundToolDetailList()
                .setFundToolName(payChannelDetailList.get(0).fundToolDetailList.get(0).fundToolName)
                .setAmount(payChannelDetailList.get(0).fundToolDetailList.get(0).getAmount())
                .setGmtCreate(payChannelDetailList.get(0).fundToolDetailList.get(0).gmtCreate)
                .setGmtFinish(payChannelDetailList.get(0).fundToolDetailList.get(0).gmtFinish)
                .setPromotionFundTool(payChannelDetailList.get(0).fundToolDetailList.get(0).promotionFundTool)
                .setExtInfo(payChannelDetailList.get(0).fundToolDetailList.get(0).extInfo);
        NotifyBadgeCodePayResultRequest.NotifyBadgeCodePayResultRequestPayChannelDetailList payChannelDetailList0 = new NotifyBadgeCodePayResultRequest.NotifyBadgeCodePayResultRequestPayChannelDetailList()
                .setPayChannelName(payChannelDetailList.get(0).payChannelName)
                .setGmtCreate(payChannelDetailList.get(0).gmtCreate)
                .setGmtFinish(payChannelDetailList.get(0).gmtFinish)
                .setPayChannelType(payChannelDetailList.get(0).payChannelType)
                .setAmount(payChannelDetailList.get(0).amount)
                .setPayChannelOrderNo(payChannelDetailList.get(0).payChannelOrderNo)
                .setPromotionAmount(payChannelDetailList.get(0).promotionAmount)
                .setFundToolDetailList(java.util.Arrays.asList(
                        payChannelDetailList0FundToolDetailList0
                ));
        NotifyBadgeCodePayResultRequest notifyBadgeCodePayResultRequest = new NotifyBadgeCodePayResultRequest()
                .setPayCode(payCode)
                .setCorpId(corpId)
                .setUserId(userId)
                .setGmtTradeCreate(gmtTradeCreate)
                .setGmtTradeFinish(gmtTradeFinish)
                .setTradeNo(tradeNo)
                .setTradeStatus(tradeStatus)
                .setTitle(title)
                .setRemark(remark)
                .setAmount(amount)
                .setPromotionAmount(promotionAmount)
                .setChargeAmount(chargeAmount)
                .setPayChannelDetailList(java.util.Arrays.asList(
                        payChannelDetailList0
                ))
                .setTradeErrorCode(tradeErrorCode)
                .setTradeErrorMsg(tradeErrorMsg)
                .setExtInfo(extInfo)
                .setMerchantName(merchantName);
        NotifyBadgeCodePayResultResponse response = new NotifyBadgeCodePayResultResponse();
        try {
            response = client.notifyBadgeCodePayResultWithOptions(notifyBadgeCodePayResultRequest, notifyBadgeCodePayResultHeaders, new RuntimeOptions());
            return response.body.getResult();

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }

        }
        return null;
    }

    /**
     * 通知钉工牌码退款结果
     * <p/>
     * 用户使用钉工牌码支付后，如果发生退款，退款完成后，调用本接口同步退款结果，生成对应账单。
     * https://developers.dingtalk.com/document/app/notification-dingtalk-badge-code-refund-result
     * </p>
     *
     * @param corpId                                  企业ID
     * @param userId                                  用户ID
     * @param tradeNo                                 交易单号
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
    @Override
    public String notifyBadgeCodeRefundResult(String corpId, String userId, String tradeNo, String refundOrderNo, String remark, String refundAmount,
                                              String refundPromotionAmount, String gmtRefund, String payCode,
                                              List<NotifyBadgeCodeRefundResultRequest.NotifyBadgeCodeRefundResultRequestPayChannelDetailList> payChannelDetailList0FundToolDetailList) throws Exception {
        // 1、获取accessToken
        String accessToken = accessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());
        // 2、创建账户client
        Client client = accountInitManager.createClient();

        // 3、构建请求参数
        NotifyBadgeCodeRefundResultHeaders notifyBadgeCodeRefundResultHeaders = new NotifyBadgeCodeRefundResultHeaders();
        notifyBadgeCodeRefundResultHeaders.xAcsDingtalkAccessToken = accessToken;
        NotifyBadgeCodeRefundResultRequest.NotifyBadgeCodeRefundResultRequestPayChannelDetailListFundToolDetailList payChannelDetailList0FundToolDetailList0 = new NotifyBadgeCodeRefundResultRequest.NotifyBadgeCodeRefundResultRequestPayChannelDetailListFundToolDetailList()
                .setFundToolName(payChannelDetailList0FundToolDetailList.get(0).fundToolDetailList.get(0).fundToolName)
                .setAmount(payChannelDetailList0FundToolDetailList.get(0).fundToolDetailList.get(0).amount)
                .setGmtCreate(payChannelDetailList0FundToolDetailList.get(0).fundToolDetailList.get(0).gmtCreate)
                .setGmtFinish(payChannelDetailList0FundToolDetailList.get(0).fundToolDetailList.get(0).gmtFinish)
                .setPromotionFundTool(payChannelDetailList0FundToolDetailList.get(0).fundToolDetailList.get(0).promotionFundTool)
                .setExtInfo(payChannelDetailList0FundToolDetailList.get(0).fundToolDetailList.get(0).extInfo);
        NotifyBadgeCodeRefundResultRequest.NotifyBadgeCodeRefundResultRequestPayChannelDetailList payChannelDetailList0 = new NotifyBadgeCodeRefundResultRequest.NotifyBadgeCodeRefundResultRequestPayChannelDetailList()
                .setPayChannelName(payChannelDetailList0FundToolDetailList.get(0).payChannelName)
                .setPayChannelType(payChannelDetailList0FundToolDetailList.get(0).payChannelType)
                .setAmount(payChannelDetailList0FundToolDetailList.get(0).amount)
                .setPayChannelOrderNo(payChannelDetailList0FundToolDetailList.get(0).payChannelOrderNo)
                .setPayChannelRefundOrderNo(payChannelDetailList0FundToolDetailList.get(0).payChannelRefundOrderNo)
                .setPromotionAmount(payChannelDetailList0FundToolDetailList.get(0).promotionAmount)
                .setFundToolDetailList(java.util.Arrays.asList(
                        payChannelDetailList0FundToolDetailList0
                ));
        NotifyBadgeCodeRefundResultRequest notifyBadgeCodeRefundResultRequest = new NotifyBadgeCodeRefundResultRequest()
                .setCorpId(corpId)
                .setUserId(userId)
                .setTradeNo(tradeNo)
                .setRefundOrderNo(refundOrderNo)
                .setRemark(remark)
                .setRefundAmount(refundAmount)
                .setRefundPromotionAmount(refundPromotionAmount)
                .setGmtRefund(gmtRefund)
                .setPayChannelDetailList(java.util.Arrays.asList(
                        payChannelDetailList0
                ))
                .setPayCode(payCode);
        NotifyBadgeCodeRefundResultResponse response = new NotifyBadgeCodeRefundResultResponse();
        try {
            response = client.notifyBadgeCodeRefundResultWithOptions(notifyBadgeCodeRefundResultRequest, notifyBadgeCodeRefundResultHeaders, new RuntimeOptions());
            return response.body.getResult();

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }

        }
        return null;
    }

    /**
     * 同步钉工牌码验证结果
     * <p>
     * 用户扫码验证后，调用本接口同步验证结果。
     * https://developers.dingtalk.com/document/app/notification-dingtalk-badge-verification-result
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
    @Override
    public String notifyVerifyResult(String payCode, String corpId, String userCorpRelationType, String userIdentity, String verifyTime,
                                     boolean verifyResult, String verifyLocation) throws Exception {
        // 1、获取accessToken
        String accessToken = accessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());
        // 2、创建账户client
        Client client = accountInitManager.createClient();

        // 3、构建请求参数
        NotifyBadgeCodeVerifyResultHeaders notifyBadgeCodeVerifyResultHeaders = new NotifyBadgeCodeVerifyResultHeaders();
        notifyBadgeCodeVerifyResultHeaders.xAcsDingtalkAccessToken = accessToken;
        NotifyBadgeCodeVerifyResultRequest notifyBadgeCodeVerifyResultRequest = new NotifyBadgeCodeVerifyResultRequest()
                .setPayCode(payCode)
                .setCorpId(corpId)
                .setUserCorpRelationType(userCorpRelationType)
                .setUserIdentity(userIdentity)
                .setVerifyTime(verifyTime)
                .setVerifyResult(verifyResult)
                .setVerifyLocation(verifyLocation);
        NotifyBadgeCodeVerifyResultResponse response = new NotifyBadgeCodeVerifyResultResponse();
        try {
            response = client.notifyBadgeCodeVerifyResultWithOptions(notifyBadgeCodeVerifyResultRequest, notifyBadgeCodeVerifyResultHeaders, new RuntimeOptions());
            return response.body.getResult();

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }

        }

        return null;
    }

    /**
     * 配置企业钉工牌
     * <p>
     * 调用本接口配置企业钉工牌。
     * https://developers.dingtalk.com/document/app/save-dingtalk-enterprise-instance
     * </p>
     *
     * @param corpId       开通的企业corpId。
     * @param codeIdentity 码标识，由钉钉颁发
     * @param status       状态，取值。OPEN：开启 CLOSED：关闭
     * @param extInfo      扩展属性（可空）
     * @return 配置结果
     * @throws Exception
     */
    @Override
    public SaveBadgeCodeCorpInstanceResponseBody saveCorpPayCode(String corpId, String codeIdentity, String status, Map<String, String> extInfo) throws Exception {
        // 1、获取accessToken
        String accessToken = accessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret());
        // 2、创建账户client
        Client client = accountInitManager.createClient();

        // 3、构建请求参数
        SaveBadgeCodeCorpInstanceHeaders saveBadgeCodeCorpInstanceHeaders = new SaveBadgeCodeCorpInstanceHeaders();
        saveBadgeCodeCorpInstanceHeaders.xAcsDingtalkAccessToken = accessToken;
        SaveBadgeCodeCorpInstanceRequest saveBadgeCodeCorpInstanceRequest = new SaveBadgeCodeCorpInstanceRequest()
                .setCodeIdentity(codeIdentity)
                .setCorpId(corpId)
                .setStatus(status)
                .setExtInfo(extInfo);
        SaveBadgeCodeCorpInstanceResponse response = new SaveBadgeCodeCorpInstanceResponse();
        try {
            response = client.saveBadgeCodeCorpInstanceWithOptions(saveBadgeCodeCorpInstanceRequest, saveBadgeCodeCorpInstanceHeaders, new RuntimeOptions());
            return response.body;

        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println("code: " + err.code + ", msg: " + err.message);

            }

        }

        return null;

    }


}