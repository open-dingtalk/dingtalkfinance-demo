/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.dingtalk.model;

import lombok.Data;

import java.util.Map;

/**
 * @author shiyan
 * @version $Id: SaveCorpCodeRequest.java, v 0.1 2021-10-26 下午2:53 shiyan Exp $$
 */
@Data
public class SaveCorpCodeRequest {
    /**
     * 码标识，由钉钉颁发
     */
    private String codeIdentity;
    /**
     * 码状态
     */
    private String status;
    /**
     * 企业ID
     */
    private String corpId;

    /**
     * 扩展属性字段(可空）
     */
    private Map<String, String> extInfo;

}