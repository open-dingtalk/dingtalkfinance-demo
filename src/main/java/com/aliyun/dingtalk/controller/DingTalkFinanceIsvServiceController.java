/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.aliyun.dingtalk.controller;

import com.aliyun.dingtalk.common.ISVAppConfig;
import com.aliyun.dingtalk.model.CreateInstanceRequest;
import com.aliyun.dingtalk.model.DecodeRequest;
import com.aliyun.dingtalk.model.NotifyIsvRequest;
import com.aliyun.dingtalk.model.NotifyRefundIsvRequest;
import com.aliyun.dingtalk.model.NotifyVerifyRequest;
import com.aliyun.dingtalk.model.SaveCorpCodeRequest;
import com.aliyun.dingtalk.model.ServiceResult;
import com.aliyun.dingtalk.model.UpdateInstanceRequest;
import com.aliyun.dingtalk.service.DingTalkFinanceISVService;
import com.aliyun.dingtalk.util.ISVSuiteAccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 钉工牌ISV应用Demo，完整钉工牌接入流程；
 * - 获取suiteAccessToken，调用以下接口
 * 1、 临时会展场景
 * - step1：调用saveCorpPayCode接口为企业创建码
 * - step2：调用createBadgeCodeUserInstance接口为参会人员创建付款码；
 * - step3：调用updateBadgeCodeUserInstance为需要的员工更新码实例；
 * - step4：调用decodeBadgeCode提交码值进行解析
 * - step5：调用notifyVerifyResult同步钉工牌码验证结果
 * - step6：调用notifyBadgeCodePayResult同步上传支付结果
 * - step7：调用notifyBadgeCodeRefundResult同步上传退款结果
 * <p>
 * 2、钉工牌支付场景
 * - step1：调用decodeBadgeCode提交码值进行解析
 * - step2：调用notifyBadgeCodePayResult同步上传支付结果
 * - step3：调用notifyBadgeCodeRefundResult同步上传退款结果
 *
 * @author shiyan
 * @version $Id: DingTalkFinanceIsvServiceController.java, v 0.1 2021-10-21 下午7:12 shiyan Exp $$
 */
@RestController
public class DingTalkFinanceIsvServiceController {
    /**
     * ISV应用，获取suit-access-token调用相关接口
     * 如果是纯ISV，第三方应用，则需要获取suiteAccessToken调用相关接口
     */
    @Autowired
    ISVSuiteAccessTokenUtil accessTokenUtil;

    /**
     * 应用配置
     */
    @Autowired
    ISVAppConfig appConfig;

    /**
     * 钉工牌服务
     */
    @Autowired
    DingTalkFinanceISVService dingTalkFinanceService;

    /**
     * 欢迎页面, 检查后端服务是否启动
     *
     * @return
     */
    @GetMapping("/isv/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 获取access-token信息
     *
     * @return token信息
     */
    @GetMapping("/isv/getaccess")
    public ServiceResult getAccessToken() {

        return ServiceResult.getSuccessResult(accessTokenUtil.getSuiteAccessToken(appConfig.getSuiteKey(), appConfig.getSuiteSecret(), appConfig.getSuiteTicket()));
    }

    /**
     * 创建钉工牌用户实例
     *
     * @return 钉工牌实例-codeId
     */
    @PostMapping("/isv/createinstance")
    public ServiceResult createBadgeCodeUserInstance(@RequestBody CreateInstanceRequest createInstanceRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.createCodeUserInstance(createInstanceRequest.getRequestId(),
                    createInstanceRequest.getCodeIdentity(), createInstanceRequest.getCodeValue(), createInstanceRequest.getStatus(),
                    createInstanceRequest.getCorpId(), createInstanceRequest.getUserCorpRelationType(), createInstanceRequest.getUserIdentity(),
                    createInstanceRequest.getGmtExpired(), createInstanceRequest.getGmtStart(), createInstanceRequest.getGmtEnd(),
                    createInstanceRequest.getExtInfo()));
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.getFailureResult("", e.getMessage());
        }
    }

    /**
     * 更新钉工牌用户实例
     *
     * @return 钉工牌实例-codeId
     */
    @PostMapping("/isv/updateinstance")
    public ServiceResult updateBadgeCodeUserInstance(@RequestBody UpdateInstanceRequest updateInstanceRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.updateCodeUserInstance(updateInstanceRequest.getCodeId(),
                    updateInstanceRequest.getCodeIdentity(), updateInstanceRequest.getCodeValue(), updateInstanceRequest.getStatus(),
                    updateInstanceRequest.getCorpId(), updateInstanceRequest.getUserCorpRelationType(), updateInstanceRequest.getUserIdentity(),
                    updateInstanceRequest.getGmtExpired(), updateInstanceRequest.getGmtStart(), updateInstanceRequest.getGmtEnd(),
                    updateInstanceRequest.getExtInfo()));
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.getFailureResult("", e.getMessage());
        }
    }

    /**
     * 解码钉工牌码
     *
     * @return 码值
     */
    @PostMapping("/isv/decode")
    public ServiceResult decodeBadgeCode(@RequestBody DecodeRequest decodeRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.decodeCode(decodeRequest.getPayCode(), decodeRequest.getRequestId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.getFailureResult("", e.getMessage());
        }
    }

    /**
     * 同步钉工牌码支付结果
     *
     * @return 同步结果
     */
    @PostMapping("/isv/notify")
    public ServiceResult notifyBadgeCodePayResult(@RequestBody NotifyIsvRequest notifyRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.notifyCodePayResult(notifyRequest.getPayCode(), notifyRequest.getCorpId(),
                    notifyRequest.getUserId(), notifyRequest.getGmtTradeCreate(), notifyRequest.getGmtTradeFinish(), notifyRequest.getTradeNo(),
                    notifyRequest.getTradeStatus(), notifyRequest.getTitle(), notifyRequest.getRemark(), notifyRequest.getAmount(),
                    notifyRequest.getPromotionAmount(), notifyRequest.getChargeAmount(), notifyRequest.getPayChannelDetailList(), notifyRequest.getTradeErrorCode(),
                    notifyRequest.getTradeErrorMsg(), notifyRequest.getExtInfo(), notifyRequest.getMerchantName()));
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.getFailureResult("", e.getMessage());
        }
    }

    /**
     * 通知钉工牌码退款结果
     *
     * @return 同步结果
     */
    @PostMapping("/isv/refund")
    public ServiceResult notifyBadgeCodeRefundResult(@RequestBody NotifyRefundIsvRequest notifyRefundRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.notifyCodeRefundResult(notifyRefundRequest.getPayCode(), notifyRefundRequest.getCorpId(),
                    notifyRefundRequest.getUserId(), notifyRefundRequest.getRefundOrderNo(), notifyRefundRequest.getRemark(), notifyRefundRequest.getTradeNo(),
                    notifyRefundRequest.getRefundPromotionAmount(), notifyRefundRequest.getGmtRefund(), notifyRefundRequest.getRemark(),
                    notifyRefundRequest.getPayChannelDetailList()));
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.getFailureResult("", e.getMessage());
        }
    }

    /**
     * 同步钉工牌码验证结果
     *
     * @return 同步结果
     */
    @PostMapping("/isv/verify")
    public ServiceResult notifyVerifyResult(@RequestBody NotifyVerifyRequest notifyVerifyRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.notifyVerifyResult(notifyVerifyRequest.getPayCode(), notifyVerifyRequest.getCorpId(),
                    notifyVerifyRequest.getUserCorpRelationType(), notifyVerifyRequest.getUserIdentity(), notifyVerifyRequest.getVerifyTime(),
                    notifyVerifyRequest.isVerifyResult(), notifyVerifyRequest.getVerifyLocation()));
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.getFailureResult("", e.getMessage());
        }
    }

    /**
     * 企业创建码
     *
     * @return 结果
     */
    @PostMapping("/isv/save")
    public ServiceResult saveCorpPayCode(@RequestBody SaveCorpCodeRequest saveCorpCodeRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.saveCorpPayCode(saveCorpCodeRequest.getCorpId(), saveCorpCodeRequest.getCodeIdentity(),
                    saveCorpCodeRequest.getStatus(), saveCorpCodeRequest.getExtInfo()));
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.getFailureResult("", e.getMessage());
        }
    }

}