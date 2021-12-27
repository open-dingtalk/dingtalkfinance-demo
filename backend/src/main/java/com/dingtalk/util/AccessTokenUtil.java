/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.dingtalk.util;

import com.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.stereotype.Component;

/**
 * 获取access_token工具类 （三方企业使用）
 *
 * @author shiyan
 * @version $Id: AccessTokenUtil.java, v 0.1 2021-10-20 下午4:24 shiyan Exp $$
 */
@Component
public class AccessTokenUtil {
    /**
     * 在使用accessToken时，请注意：
     * accessToken的有效期为7200秒（2小时），有效期内重复获取会返回相同结果并自动续期，过期后获取会返回新的accessToken。
     * 开发者需要缓存accessToken，用于后续接口的调用。因为每个应用的accessToken是彼此独立的，所以进行缓存时需要区分应用来进行存储。
     * 不能频繁调用接口，否则会受到频率拦截。
     *
     * @param appKey
     * @param appSecret
     * @return
     */
    public String getAccessToken(String appKey, String appSecret) {
        Config config = new Config();
        config.protocol = UrlConstant.PROTOCOL;
        config.regionId = UrlConstant.GET_REGION;
        Client client = null;
        try {
            client = new Client(config);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest()
                .setAppKey(appKey)
                .setAppSecret(appSecret);

        GetAccessTokenResponse response = new GetAccessTokenResponse();
        try {
            response = client.getAccessToken(getAccessTokenRequest);
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        }

        return response.body.accessToken;
    }
}