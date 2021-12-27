/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.dingtalk.model;

import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodePayResultRequest;
import lombok.Data;

import java.util.List;

/**
 * @author shiyan
 * @version $Id: NotifyRequest.java, v 0.1 2021-10-25 上午11:12 shiyan Exp $$
 */
@Data
public class NotifyRequest {
    /**
     * 码值，由扫码硬件设备获取
     */
    private String payCode;
    /**
     * 企业ID
     */
    private String corpId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 交易创建时间
     */
    private String gmtTradeCreate;
    /**
     * 交易完成时间
     */
    private String gmtTradeFinish;
    /**
     * 交易单号
     */
    private String tradeNo;
    /**
     * 交易状态
     */
    private String tradeStatus;
    /**
     * 订单名称
     */
    private String title;
    /**
     * 订单说明
     */
    private String remark;
    /**
     * 金额
     */
    private String amount;
    /**
     * 优惠金额
     */
    private String promotionAmount;
    /**
     * 收费金额
     */
    private String chargeAmount;
    /**
     * 资金明细
     */
    private List<NotifyBadgeCodePayResultRequest.NotifyBadgeCodePayResultRequestPayChannelDetailList> payChannelDetailList;
    /**
     * 错误码
     */
    private String tradeErrorCode;
    /**
     * 错误信息
     */
    private String tradeErrorMsg;
    /**
     * 扩展属性
     */
    private String extInfo;
    /**
     * 商户名称
     */
    private String merchantName;
}