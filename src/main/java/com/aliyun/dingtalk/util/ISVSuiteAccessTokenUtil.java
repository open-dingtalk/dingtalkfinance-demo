/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.aliyun.dingtalk.util;

import com.aliyun.dingtalk.constant.UrlConstant;
import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.dingtalkoauth2_1_0.models.GetSuiteAccessTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetSuiteAccessTokenResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.stereotype.Component;

/**
 * 获取suite_access_token工具类 （纯ISV企业使用）
 *
 * @author shiyan
 * @version $Id: ISVSuiteAccessTokenUtil.java, v 0.1 2021-10-20 下午4:24 shiyan Exp $$
 */
@Component
public class ISVSuiteAccessTokenUtil {

    /**
     * 调用本接口获取第三方企业应用的suiteAccessToken。
     * <p>
     * 该suiteAccessToken主要用于获取第三方企业应用的信息，在调用以下接口时会使用第三方企业应用的suiteAccessToken：
     *
     * @param suiteKey    已创建的第三方企业应用的SuiteKey。
     * @param suiteSecret 已创建的第三方企业应用的SuiteSecret。
     * @param suiteTicket 钉钉开放平台会向应用的回调URL推送的suite_ticket（约5个小时推送一次），详细内容请参考推送suite_ticket事件。
     * @return 第三方企业应用的凭证。
     */
    public String getSuiteAccessToken(String suiteKey, String suiteSecret, String suiteTicket) {
        Config config = new Config();
        config.protocol = UrlConstant.PROTOCOL;
        config.regionId = UrlConstant.GET_REGION;
        Client client = null;
        try {
            client = new Client(config);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GetSuiteAccessTokenRequest getSuiteAccessTokenRequest = new GetSuiteAccessTokenRequest()
                .setSuiteKey(suiteKey)
                .setSuiteSecret(suiteSecret)
                .setSuiteTicket(suiteTicket);
        GetSuiteAccessTokenResponse response = new GetSuiteAccessTokenResponse();
        try {
            response = client.getSuiteAccessToken(getSuiteAccessTokenRequest);
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