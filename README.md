# 钉工牌功能-Demo
## 钉工牌简介
钉工牌是企业数字化的标志，是员工的数字化工作证。钉钉基于组织自身的通讯录，联合支付宝和阿里云的支付与安全能力，为企业组织提供一站式的工牌解决方案。

企业可开通钉工牌服务，员工出示钉工牌，不仅能实现物理工牌常有的门禁通行、食堂就餐支付等能力，还能有访客识别、企业协议价支付、员工收款等能力。钉钉聚焦实现用户端钉工牌的展码、解码、支付、安全等能力，在应用场景上与合作伙伴的系统开放共建。

## 接入准备
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

## 运行准备
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
### 接口说明
**demo依赖链路图**

![image333](https://img.alicdn.com/imgextra/i2/O1CN01gOpgre1IX9y93QDFw_!!6000000000902-2-tps-1780-1132.png)

**此处展示企业内部应用的开发方式**

![image2](https://img.alicdn.com/imgextra/i2/O1CN01QPHKDQ1cHr2sINE5Y_!!6000000003576-2-tps-870-1286.png)

### 修改前端页面

**打开项目，命令行中执行以下命令，编译打包生成build文件**

```shell
cd front-end
npm install
npm run build
```

**将打包好的静态资源文件放入后端**

![image-20210706173224172](https://img.alicdn.com/imgextra/i2/O1CN01QLp1Qw1TCVrPddfjZ_!!6000000002346-2-tps-322-521.png)

### 启动项目

- 启动SpringBoot
- 移动端钉钉点击工作台，找到创建的应用，进入应用

### 效果展示

操作列表

![](https://img.alicdn.com/imgextra/i4/O1CN01MVGnYF1kHrzDVBxxw_!!6000000004659-2-tps-449-422.png)

配置钉工牌

![22](https://img.alicdn.com/imgextra/i2/O1CN01z66hip21axy32D3GT_!!6000000007002-2-tps-449-76.png)

创建钉工牌

![33](https://img.alicdn.com/imgextra/i1/O1CN01UUQ2HX1M7kSI2n4LQ_!!6000000001388-2-tps-449-119.png)

钉工牌入口

![44](https://img.alicdn.com/imgextra/i3/O1CN01Bqsls41ZVe7q3pcAp_!!6000000003200-2-tps-449-504.png)

扫描钉工牌获取码值

![00](https://img.alicdn.com/imgextra/i1/O1CN01kYJgcb1bzXCmTY1v4_!!6000000003536-2-tps-448-376.png)

钉工牌解码

![55](https://img.alicdn.com/imgextra/i3/O1CN01rp3kQO2AAhnoxMfMA_!!6000000008163-2-tps-449-262.png)

解码获取付款码

![88](https://img.alicdn.com/imgextra/i2/O1CN01fnWvyN1qGOP7kPbk1_!!6000000005468-2-tps-450-133.png)

同步支付结果/同步退款结果

![66](https://img.alicdn.com/imgextra/i1/O1CN01Jn4WJN239bXSr1pmD_!!6000000007213-2-tps-447-188.png)

支付/退款信息

![99](https://img.alicdn.com/imgextra/i2/O1CN01NIpm1B1zZTRpyLLQk_!!6000000006728-2-tps-448-951.png)

验证钉工牌电子码身份

![77](https://img.alicdn.com/imgextra/i2/O1CN01IwzOof1JvGQY3AfqO_!!6000000001090-2-tps-447-73.png)

## 参考文档

1. 钉工牌简介：https://developers.dingtalk.com/document/app/introduction-to-dingtalk-badge
2. 如何调用钉工牌API：https://developers.dingtalk.com/document/app/application-for-dingtalk-badge-permission
