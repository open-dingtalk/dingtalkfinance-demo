/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.aliyun.dingtalk.common;

import com.aliyun.dingtalkfinance_1_0.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.stereotype.Component;

/**
 * ISV用户账户生成管理器
 *
 * @author shiyan
 * @version $Id: IsvAccountInitManager.java, v 0.1 2021-10-20 下午3:53 shiyan Exp $$
 */
@Component
public class IsvAccountInitManager {

    /**
     * 使用 Token 初始化账号Client
     *
     * @return Client
     * @throws Exception
     */
    public Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        // 预发环境测试
        config.endpoint = "pre-api.dingtalk.com";

        return new Client(config);
    }
}