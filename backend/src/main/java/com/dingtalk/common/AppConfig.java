/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.dingtalk.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author shiyan
 * @version $Id: AppConfig.java, v 0.1 2021-10-20 下午8:24 shiyan Exp $$
 */
@Configuration
@Data
public class AppConfig {
    @Value("${dingtalk.app_key}")
    private String appKey;

    @Value("${dingtalk.app_secret}")
    private String appSecret;

    @Value("${dingtalk.corp_id}")
    private String corpId;
}