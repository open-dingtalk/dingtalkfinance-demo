/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.dingtalk.controller;

import com.alibaba.fastjson.JSON;
import com.dingtalk.common.AppConfig;
import com.dingtalk.model.CreateInstanceRequest;
import com.dingtalk.model.DecodeRequest;
import com.dingtalk.model.NotifyRefundRequest;
import com.dingtalk.model.NotifyRequest;
import com.dingtalk.model.NotifyVerifyRequest;
import com.dingtalk.model.SaveCorpCodeRequest;
import com.dingtalk.model.ServiceResult;
import com.dingtalk.model.UpdateInstanceRequest;
import com.dingtalk.service.DingTalkFinanceService;
import com.dingtalk.util.AccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
     * 示例入参：
      {
      "requestId": 123456,
      "codeIdentity": "DT_VISITOR",
      "status": "OPEN",
      "corpId": "ding3954e38839604bd524f2f5cc6abecb85",
      "userCorpRelationType": "INTERNAL_STAFF",
      "userIdentity": "2813420619986718",
      "gmtExpired": "2021-11-25 00:00:00",
      "availableTimes":[{
         "gmtStart":"2021-10-25 15:00:00",
         "gmtEnd":"2021-11-25 00:00:00"
      }],
      "extInfo": {
           "corpAddress": "杭州未来组织park",
            "applicantName": "张三",
            "applyTime": "2021-10-25 12:12:12",
            "visitorName": "李四",
            "visitorMobile": "86-12345678901",
            "visitorCorpInfo": "钉钉网络",
            "visitorExtInfo": "访客ID：20210907001",
            "remarks": "欢迎光临",
            "corpWebsite": "www.dingtalk.com"
           }
      }
     *
     * @return 钉工牌实例-codeId
     */
    @PostMapping("/createinstance")
    public ServiceResult createBadgeCodeUserInstance(@RequestBody CreateInstanceRequest createInstanceRequest) {
        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.createBadgeCodeUserInstance(createInstanceRequest.getRequestId(),
                    createInstanceRequest.getCodeIdentity(), createInstanceRequest.getCodeValue(), createInstanceRequest.getStatus(),
                    createInstanceRequest.getCorpId(), createInstanceRequest.getUserCorpRelationType(), createInstanceRequest.getUserIdentity(),
                    createInstanceRequest.getGmtExpired(), createInstanceRequest.getAvailableTimes(),
                    createInstanceRequest.getExtInfo()));
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.getFailureResult("", e.getMessage());
        }
    }

    /**
     * 更新钉工牌用户实例
     * 示例入参：
     {
     "requestId": 123456,
     "codeId": "7f9155fd8b5366b8a0e1a2d65b9e3cd2267f33df7861a2c10c92f85bf300c560_123456",
     "codeIdentity": "DT_VISITOR",
     "status": "OPEN",
     "corpId": "ding3954e38839604bd524f2f5cc6abecb85",
     "userCorpRelationType": "INTERNAL_STAFF",
     "userIdentity": "2813420619986718",
     "gmtExpired": "2021-11-25 00:00:00",
     "availableTimes":[{
         "gmtStart":"2021-10-25 15:00:00",
         "gmtEnd":"2021-11-25 00:00:00"
     }],
     "extInfo": {
         "corpAddress": "杭州未来组织park",
         "applicantName": "瀚博",
         "applyTime": "2021-10-25 12:12:12",
         "visitorName": "郝汉森",
         "visitorMobile": "86-12345678901",
         "visitorCorpInfo": "海云钉网络",
         "visitorExtInfo": "访客ID：20210907001",
         "remarks": "欢迎光临",
         "corpWebsite": "www.dingtalk.com"
        }
     }
     * @return 钉工牌实例-codeId
     */
    @PostMapping("/updateinstance")
    public ServiceResult updateBadgeCodeUserInstance(@RequestBody UpdateInstanceRequest updateInstanceRequest) {

        try {
            return ServiceResult.getSuccessResult(dingTalkFinanceService.updateBadgeCodeUserInstance(updateInstanceRequest.getCodeId(),
                    updateInstanceRequest.getCodeIdentity(), updateInstanceRequest.getCodeValue(), updateInstanceRequest.getStatus(),
                    updateInstanceRequest.getCorpId(), updateInstanceRequest.getUserCorpRelationType(), updateInstanceRequest.getUserIdentity(),
                    updateInstanceRequest.getGmtExpired(), updateInstanceRequest.getAvailableTimes(),
                    updateInstanceRequest.getExtInfo()));
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResult.getFailureResult("", e.getMessage());
        }
    }

    /**
     * 解码钉工牌码
     * 示例入参：
     {
         "payCode":"xxxx", // 码值可以通过手机扫描获取
         "requestId":"250134742608720142-V0ami1d7o7vap4uwlx"  // 请求ID随机
     }
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
     {
     "amount":"0.01",
     "chargeAmount":"0",
     "corpId":"ding3954e38839604bd524f2f5cc6abecb85",
     "gmtTradeCreate":"2021-10-26 12:11:24",
     "gmtTradeFinish":"2021-10-26 12:11:24",
     "isvOrgId":261399101,
     "merchantName":"杭州钉钉网络科技有限公司",
     "payChannelDetailList":[
     {
         "amount":"0.01",
         "fundToolDetailList":[
             {
             "amount":"0.01",
             "extInfo":"",
             "fundToolName":"数字食堂余额",
             "gmtCreate":"2021-10-26 12:11:24",
             "gmtFinish":"2021-10-26 12:11:24",
             "promotionFundTool":false
             }
         ],
         "payChannelName":"数字食堂余额",
         "payChannelOrderNo":"37164671863227332",
         "payChannelType":"BALANCE",
         "promotionAmount":"0.00"
     }
     ],
     "payCode":"250134742608720142-V0ami1d7o7vap4uwlx",
     "promotionAmount":"0",
     "remark":"钉钉支付码",
     "title":"陈军 下单 0.01 元（数字食堂）",
     "tradeNo":"2021102612112383609613ecf6b2e232",
     "tradeStatus":"SUCCESS",
     "userId":"2813420619986718"
     }
     * @return 同步结果
     */
    @PostMapping("/notify")
    public ServiceResult notifyBadgeCodePayResult(@RequestBody NotifyRequest notifyRequest) {
        System.out.println("同步支付 1：" + JSON.toJSONString(notifyRequest));
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
     * 示例入参：
     {
     "corpId": "ding3954e38839604bd524f2f5cc6abecb85",
     "userId": "2813420619986718",
     "tradeNo": "2021102612112383609613ecf6b2e232",  // 通知退款时的交易单号应该和同步支付结果时的单号一致
     "refundOrderNo": "2021102612112383609613ecf6b2e223",
     "remark": "钉钉退款",
     "refundAmount": "0.01",
     "refundPromotionAmount": "0.00",
     "gmtRefund": "2021-10-26 12:11:24",
     "payCode": "250134742608720142-V0ami1d7o7vap4uwlx",
     "payChannelDetailList": [
     {
         "amount": "0.01",
         "fundToolDetailList": [
             {
             "amount": "0.01",
             "extInfo": "",
             "fundToolName": "数字食堂余额",
             "gmtCreate": "2021-10-26 12:11:24",
             "gmtFinish": "2021-10-26 12:11:24",
             "promotionFundTool": false
             }
         ],
         "payChannelName": "数字食堂余额",
         "payChannelOrderNo": "37164671863227332",
         "payChannelRefundOrderNo": "37164671864327332",
         "payChannelType": "BALANCE",
         "promotionAmount": "0.00"
     }
     ]
     }
     * @return 同步结果
     */
    @PostMapping("/refund")
    public ServiceResult notifyBadgeCodeRefundResult(@RequestBody NotifyRefundRequest notifyRefundRequest) {
        System.out.println("同步退款：" + JSON.toJSONString(notifyRefundRequest));
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
     {
         "corpId":"ding3954e38839604bd524f2f5cc6abecb85",
         "payCode":"250134742608720142-V0ami1d7o7vap4uwlx",
         "userCorpRelationType":"INTERNAL_STAFF",
         "userIdentity":"2813420619986718",
         "verifyLocation":"未来park",
         "verifyResult":true,
         "verifyTime":"2021-10-26 10:30:06"
     }
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
     {
         "codeIdentity": "DT_VISITOR",
         "status": "OPEN",
         "corpId": "ding3954e388396xxxxx",
         "extInfo": {
            "xx_key": "xx_value"
        }
     }
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