/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.dingtalk.model;

import lombok.Data;

/**
 * @author shiyan
 * @version $Id: NotifyVerifyRequest.java, v 0.1 2021-10-25 上午11:57 shiyan Exp $$
 */
@Data
public class NotifyVerifyRequest {
    /**
     * 码值
     */
    private String payCode;
    /**
     * 企业ID
     */
    private String corpId;
    /**
     * 访客和企业的关系类型,取值：INTERNAL_STAFF，EXTERNAL_CONTACT，NO_RELATION
     */
    private String userCorpRelationType;
    /**
     * 用户身份标识，如果企业内部员工传入staffId，外部联系人传入外部联系人ID，无关系用户传入手机号
     */
    private String userIdentity;
    /**
     * 验证时间
     */
    private String verifyTime;
    /**
     * 验证结果
     */
    private boolean verifyResult;
    /**
     * 验证地点
     */
    private String verifyLocation;
}