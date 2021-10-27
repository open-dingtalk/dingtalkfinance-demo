/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.aliyun.dingtalk.controller;

import com.aliyun.dingtalk.common.AppConfig;
import com.aliyun.dingtalk.model.CreateInstanceRequest;
import com.aliyun.dingtalk.model.DecodeRequest;
import com.aliyun.dingtalk.model.NotifyRefundRequest;
import com.aliyun.dingtalk.model.NotifyRequest;
import com.aliyun.dingtalk.model.NotifyVerifyRequest;
import com.aliyun.dingtalk.model.SaveCorpCodeRequest;
import com.aliyun.dingtalk.model.ServiceResult;
import com.aliyun.dingtalk.model.UpdateInstanceRequest;
import com.aliyun.dingtalk.service.DingTalkFinanceService;
import com.aliyun.dingtalk.util.AccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 钉工牌三方企业内部应用Demo，完整钉工牌接入流程；
 * - 获取AccessToken，调用以下接口；
 * 1、 临时会展/访客场景
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
 * @version $Id: DingTalkFinanceServiceController.java, v 0.1 2021-10-21 下午7:12 shiyan Exp $$
 */
@RestController
public class DingTalkFinanceServiceController {
    /**
     * 企业内部应用，获取access-token调用相关接口
     * 如果是纯ISV，第三方应用，则需要获取suiteAccessToken调用相关接口
     */
    @Autowired
    AccessTokenUtil accessTokenUtil;

    /**
     * 应用配置
     */
    @Autowired
    AppConfig appConfig;

    /**
     * 钉工牌服务
     */
    @Autowired
    DingTalkFinanceService dingTalkFinanceService;

    /**
     * 欢迎页面, 检查后端服务是否启动
     *
     * @return
     */
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 获取access-token信息
     *
     * @return token信息
     */
    @GetMapping("/getaccess")
    public ServiceResult getAccessToken() {

        return ServiceResult.getSuccessResult(accessTokenUtil.getAccessToken(appConfig.getAppKey(), appConfig.getAppSecret()));
    }

    /**
     * 创建钉工牌用户实例
     *
     * @return 钉工牌实例-codeId
     */
    @PostMapping("/createinstance")
    public ServiceResult createBadgeCodeUserInstance(@RequestBody CreateInstanceRequest createInstanceRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.createBadgeCodeUserInstance(createInstanceRequest.getRequestId(),
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
    @PostMapping("/updateinstance")
    public ServiceResult updateBadgeCodeUserInstance(@RequestBody UpdateInstanceRequest updateInstanceRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.updateBadgeCodeUserInstance(updateInstanceRequest.getCodeId(),
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
    @PostMapping("/decode")
    public ServiceResult decodeBadgeCode(@RequestBody DecodeRequest decodeRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.decodeBadgeCode(decodeRequest.getPayCode(), decodeRequest.getRequestId()));
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
    @PostMapping("/notify")
    public ServiceResult notifyBadgeCodePayResult(@RequestBody NotifyRequest notifyRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.notifyBadgeCodePayResult(notifyRequest.getPayCode(), notifyRequest.getCorpId(),
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
    @PostMapping("/refund")
    public ServiceResult notifyBadgeCodeRefundResult(@RequestBody NotifyRefundRequest notifyRefundRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.notifyBadgeCodeRefundResult(notifyRefundRequest.getCorpId(), notifyRefundRequest.getUserId(),
                    notifyRefundRequest.getTradeNo(), notifyRefundRequest.getRefundOrderNo(), notifyRefundRequest.getRemark(), notifyRefundRequest.getRefundAmount(),
                    notifyRefundRequest.getRefundPromotionAmount(), notifyRefundRequest.getGmtRefund(), notifyRefundRequest.getPayCode(), notifyRefundRequest.getPayChannelDetailList()));
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
    @PostMapping("/verify")
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
    @PostMapping("/save")
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