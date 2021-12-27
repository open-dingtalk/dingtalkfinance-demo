/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.dingtalk.model;

import com.aliyun.dingtalkbadge_1_0.models.NotifyBadgeCodeRefundResultRequest;
import lombok.Data;

import java.util.List;

/**
 * @author shiyan
 * @version $Id: NotifyRefundRequest.java, v 0.1 2021-10-25 上午11:32 shiyan Exp $$
 */
@Data
public class NotifyRefundRequest {
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
     * 退款交易单号
     */
    private String refundOrderNo;
    /**
     * 备注
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
    private List<NotifyBadgeCodeRefundResultRequest.NotifyBadgeCodeRefundResultRequestPayChannelDetailList> payChannelDetailList;

}