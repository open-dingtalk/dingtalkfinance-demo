# dingtalkfinance-demo
# 钉工牌简介
钉工牌是企业数字化的标志，是员工的数字化工作证。钉钉基于组织自身的通讯录，联合支付宝和阿里云的支付与安全能力，为企业组织提供一站式的工牌解决方案。

企业可开通钉工牌服务，员工出示钉工牌，不仅能实现物理工牌常有的门禁通行、食堂就餐支付等能力，还能有访客识别、企业协议价支付、员工收款等能力。钉钉聚焦实现用户端钉工牌的展码、解码、支付、安全等能力，在应用场景上与合作伙伴的系统开放共建。

# 接入准备
1. 成为钉钉开发者，详情请参考成为[钉钉开发者](https://developers.dingtalk.com/document/app/become-a-dingtalk-developer?spm=ding_open_doc.document.0.0.65353799BrvFES#topic-2024337)。

2. 根据需求创建对应的应用，详情请参考[应用类型介绍](https://developers.dingtalk.com/document/app/application-types?spm=ding_open_doc.document.0.0.65353799BrvFES#topic-2024338)。

3. 应用创建完成后，根据自身场景添加相应的接口调用权限。

     - 企业内部应用和第三方企业应用

      1. 登录[开发者后台](https://login.dingtalk.com/oauth2/challenge.htm?redirect_uri=https%3A%2F%2Fopen-dev.dingtalk.com%2Fdingtalk_sso_call_back%3Fcontinue%3Dhttps%253A%252F%252Fopen-dev.dingtalk.com%252F%253Fspm%253Dding_open_doc.document.0.0.65353799BrvFES&response_type=code&client_id=dingbakuoyxavyp5ruxw&scope=openid+corpid)，单击进入**应用详情页**。

      2. 单击权限管理，然后搜索**钉工牌**对应的接口权限，单击**申请权限**。

     - 纯ISV身份应用

      1. 登录[开发者后台](https://login.dingtalk.com/oauth2/challenge.htm?redirect_uri=https%3A%2F%2Fopen-dev.dingtalk.com%2Fdingtalk_sso_call_back%3Fcontinue%3Dhttps%253A%252F%252Fopen-dev.dingtalk.com%252F%253Fspm%253Dding_open_doc.document.0.0.65353799BrvFES&response_type=code&client_id=dingbakuoyxavyp5ruxw&scope=openid+corpid)，单击进入应用详情页。

      2. 单击权限管理，然后搜索**付款码**对应的接口权限，单击**申请权限**。

4. 获取接口调用凭证，如何获取不同应用的凭证请参考[访问凭证概述](https://developers.dingtalk.com/document/app/authorization-overview?spm=ding_open_doc.document.0.0.65353799BrvFES#topic-2040646)。
     - 企业内部应用和第三方企业应用，获取**AccessToken**进行接口调用,请[参考](https://developers.dingtalk.com/document/app/obtain-the-access_token-of-an-internal-app)。

     - 纯ISV身份应用，获取**SuiteAccessToken**进行接口调用,请[参考](https://developers.dingtalk.com/document/app/obtains-the-suite_acess_token-of-third-party-enterprise-applications)。

5. 根据不同的使用场景，调用**钉工牌**相关接口。
     - 门禁核验场景
     - 临时会展场景
     - 刷码消费场景

# 运行准备
### 下载本项目至本地

```shell
git clone https://github.com/open-dingtalk/dingtalkfinance-demo.git
```

### 获取相应参数

获取到以下参数，修改application.properties文件内容
  - 三方企业登陆开发者后台获取企业内部应用app_key、app_secret
  ![image1](https://img.alicdn.com/imgextra/i2/O1CN01Qj32jD1p7Oy7LeilA_!!6000000005313-2-tps-1349-485.png)
  - 纯ISV企业登陆开发者后台获取三方应用suite_key、suite_secret、suite_ticket

```yaml
dingtalk.app_key=xxx
dingtalk.app_secret=xxx

dingtalk.suite_key=xxx
dingtalk.suite_secret=xxx
dingtalk.suite_ticket=xxx
```
### 运行
springboot项目本地运行，通过postman测试使用各接口
![image2](https://img.alicdn.com/imgextra/i2/O1CN01QPHKDQ1cHr2sINE5Y_!!6000000003576-2-tps-870-1286.png)

# 参考文档
1. 钉工牌简介：https://developers.dingtalk.com/document/app/introduction-to-dingtalk-badge
2. 如何调用钉工牌API：https://developers.dingtalk.com/document/app/application-for-dingtalk-badge-permission
