/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.dingtalk.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author shiyan
 * @version $Id: UpdateInstanceRequest.java, v 0.1 2021-10-25 上午10:37 shiyan Exp $$
 */
@Data
public class UpdateInstanceRequest {
    /**
     * 码编号
     */
    private String codeId;
    /**
     * 码标识
     */
    private String codeIdentity;
    /**
     * 码值
     */
    private String codeValue;
    /**
     * 码状态
     */
    private String status;
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
     * 临时码过期时间
     */
    private String gmtExpired;
    /**
     * 可用时间
     * 包含：
     * gmtStart 开始时间
     * gmtEnd 结束时间
     */
    private List<AvailableTime> availableTimes;

    /**
     * 扩展参数
     * - 必传key，示例
     *  1、applicantName："xx"
     *  2、applyTime："2021-10-25 12:12:12"
     *  3、visitorName："张三"
     *  4、visitorMobile："86-12345678901"
     */
    private Map<String, ?> extInfo;

}