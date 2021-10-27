/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.aliyun.dingtalk.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author shiyan
 * @version $Id: ISVAppConfig.java, v 0.1 2021-10-26 下午5:51 shiyan Exp $$
 */
@Configuration
@Data
public class ISVAppConfig {
    @Value("${dingtalk.suite_key}")
    private String suiteKey;

    @Value("${dingtalk.suite_secret}")
    private String suiteSecret;

    @Value("${dingtalk.suite_ticket}")
    private String suiteTicket;
}