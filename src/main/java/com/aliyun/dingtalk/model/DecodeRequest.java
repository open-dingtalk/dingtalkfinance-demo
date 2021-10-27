/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.aliyun.dingtalk.model;

import lombok.Data;

/**
 * @author shiyan
 * @version $Id: DecodeRequest.java, v 0.1 2021-10-25 上午10:49 shiyan Exp $$
 */
@Data
public class DecodeRequest {
    /**
     * 码值（字符串），由硬件设备扫描获取
     * - 相机扫描钉工牌码即可看到码值字符串
     */
    private String payCode;

    /**
     * 幂等请求ID，用户随机生成
     */
    private String requestId;
}