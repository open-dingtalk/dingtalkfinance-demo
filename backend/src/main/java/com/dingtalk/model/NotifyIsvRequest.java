/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.dingtalk.model;

import com.aliyun.dingtalkfinance_1_0.models.NotifyPayCodePayResultRequest;
import lombok.Data;

import java.util.List;

/**
 * @author shiyan
 * @version $Id: NotifyRequest.java, v 0.1 2021-10-25 上午11:12 shiyan Exp $$
 */
@Data
public class NotifyIsvRequest {
    /**
     * 付款码
     */
    private String payCode;
    /**
     * 企业id
     */
    private String corpId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 交易开始时间
     */
    private String gmtTradeCreate;
    /**
     * 交易完成时间
     */
    private String gmtTradeFinish;
    /**
     * 总交易号
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
     * 总金额
     */
    private String amount;
    /**
     * 总优惠金额
     */
    private String promotionAmount;
    /**
     * 收费金额
     */
    private String chargeAmount;
    /**
     * 资金明细
     */
    private List<NotifyPayCodePayResultRequest.NotifyPayCodePayResultRequestPayChannelDetailList> payChannelDetailList;
    private String tradeErrorCode;
    private String tradeErrorMsg;
    private String extInfo;
    /**
     * 商户名称
     */
    private String merchantName;
}