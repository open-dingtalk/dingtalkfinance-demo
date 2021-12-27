package com.dingtalk.controller;

import com.dingtalk.common.AppConfig;
import com.dingtalk.model.ServiceResult;
import com.dingtalk.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 企业内部用户（免）登录
 */
@RestController
public class LoginController {

    @Resource
    private UserManager userManager;

    @Autowired
    private AppConfig appConfig;

    /**
     * 根据免登授权码, 获取登录用户身份
     *
     * @param authCode 免登授权码
     * @return
     */
    @GetMapping(value = "/login")
    public ServiceResult login(@RequestParam(value = "authCode") String authCode) {
        try {
            // 1. 获取用户id
            String userId = userManager.getUserId(authCode);
            // 2. 获取用户名称
            String userName = userManager.getUserName(userId);
            // 3. 返回用户身份
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("userId", userId);
            resultMap.put("userName", userName);

            return ServiceResult.getSuccessResult(resultMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ServiceResult.getFailureResult("-1", "login exception");
        }
    }

    /**
     * 获取corpId
     * @return
     */
    @RequestMapping(value = "/getCorpId", method = RequestMethod.GET)
    public String getCorpId() {
        return appConfig.getCorpId();
    }
}
