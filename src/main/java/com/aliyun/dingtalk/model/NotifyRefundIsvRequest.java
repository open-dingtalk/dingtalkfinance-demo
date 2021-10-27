/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.aliyun.dingtalk.model;

import com.aliyun.dingtalkfinance_1_0.models.NotifyPayCodeRefundResultRequest;
import lombok.Data;

import java.util.List;

/**
 * @author shiyan
 * @version $Id: NotifyRefundRequest.java, v 0.1 2021-10-25 上午11:32 shiyan Exp $$
 */
@Data
public class NotifyRefundIsvRequest {
    /**
     * 企业ID
     */
    private String corpId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 交易单号
     */
    private String tradeNo;
    /**
     * 退款单号
     */
    private String refundOrderNo;
    /**
     * 交易备注
     */
    private String remark;
    /**
     * 退款金额
     */
    private String refundAmount;
    /**
     * 退款优惠金额
     */
    private String refundPromotionAmount;
    /**
     * 退款时间
     */
    private String gmtRefund;
    /**
     * 码值
     */
    private String payCode;
    /**
     * 资金明细
     */
    private List<NotifyPayCodeRefundResultRequest.NotifyPayCodeRefundResultRequestPayChannelDetailList> payChannelDetailList;

}