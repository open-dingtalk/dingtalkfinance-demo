import * as dd from "dingtalk-jsapi"
import axios from "axios"
import React from "react"
import "./App.css"
import { message, Button } from "antd"
import { Link, withRouter } from "react-router-dom"
import Work from "./components/Work"
import "antd/dist/antd.min.css"

class App extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      //内网穿透工具介绍:
      // https://developers.dingtalk.com/document/resourcedownload/http-intranet-penetration?pnamespace=app
      // 替换成后端服务域名
      domain: "",
      corpId: "",
      userId: "",
      userName: "",
      payCode: "",
      showType: 0,
    }
  }

  render() {
    if (this.state.userId === "") {
      this.login()
    }
    return (
      <div className="App">
        {this.state.showType === 0 && (
          <div>
            <p>
              <Button type="primary" onClick={() => this.saveDingTalkFinance()}>
              企业配置钉工牌
            </Button>
            </p>
            <p><Button type="primary" onClick={() => this.createDingTalkFinance()}>
              创建个人钉工牌电子码
            </Button></p>
            <p><Button type="primary" onClick={() => this.updateDingTalkFinance()}>
              更新个人钉工牌电子码
            </Button></p>
            <p><Button
              type="primary"
              onClick={() => this.setState({ showType: 1 })}
            >
              钉工牌电子码解码
            </Button></p>
            <p><Button type="primary" onClick={() => this.authDingTalkCode()}>
              钉工牌电子码验证
            </Button></p>
            <p><Button
              type="primary"
              onClick={() => this.asyncDingTalkPayResult()}
            >
              钉工牌同步支付结果
            </Button></p>
            <p><Button
              type="primary"
              onClick={() => this.asyncDingTalkReturnResult()}
            >
              钉工牌同步退款结果
            </Button></p>
            
          </div>
        )}

        {this.state.showType === 1 && (
          <Work
            onClick={(data) => {
              this.setState({
                payCode: data.payCode,
                showType: 0,
              })
              this.getDingTalkCode(data.payCode)
            }}
          />
        )}
      </div>
    )
  }
  // 企业配置钉工牌
  saveDingTalkFinance() {
    let data = {
      codeIdentity: "DT_VISITOR",
      status: "OPEN",
      corpId: "ding3954e38839604bd524f2f5cc6abecb85",
      extInfo: {
        xx_key: "xx_value",
      },
    }
    axios
      .post(this.state.domain + "/save", JSON.stringify(data), {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        if (res.data.success) {
          message.success("企业配置钉工牌成功")
        }
      })
      .catch((error) => {
        alert("saveDingTalkFinance err " + JSON.stringify(error))
      })
  }
  //
  createDingTalkFinance(e) {
    let data = {
      requestId: 123456,
      codeIdentity: "DT_VISITOR",
      status: "OPEN",
      corpId: this.state.corpId, //  corpId
      userCorpRelationType: "INTERNAL_STAFF",
      userIdentity: this.state.userId, //  userId
      gmtExpired: "2021-11-25 00:00:00",
      availableTimes: [
        {
          gmtStart: "2021-10-25 15:00:00",
          gmtEnd: "2021-11-25 00:00:00",
        },
      ],
      extInfo: {
        corpAddress: "杭州未来组织park",
        applicantName: "张三",
        applyTime: "2021-10-25 12:12:12",
        visitorName: "李四",
        visitorMobile: "86-12345678901",
        visitorCorpInfo: "钉钉网络",
        visitorExtInfo: "访客ID：20210907001",
        remarks: "欢迎光临",
        corpWebsite: "www.dingtalk.com",
      },
    }
    axios
      .post(this.state.domain + "/createinstance", JSON.stringify(data), {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        if (res.data.success) {
          message.success("创建成功，请到手机钉钉--右上角+--钉工牌中查看")
          this.setState({
            codeId: res.data.data,
          })
        }
      })
      .catch((error) => {
        alert("createDingTalkFinance err " + JSON.stringify(error))
      })
  }
  // 更新个人钉工牌电子码
  updateDingTalkFinance(e) {
    if (!this.state.codeId) {
      message.error("请先创建钉工牌电子码")
    }

    let data = {
      requestId: 123456,
      codeId: this.state.codeId, //  code
      codeIdentity: "DT_VISITOR",
      status: "OPEN",
      corpId: this.state.corpId, //  corpId
      userCorpRelationType: "INTERNAL_STAFF",
      userIdentity: this.state.userId, //  userId
      gmtExpired: "2021-11-25 00:00:00",
      availableTimes: [
        {
          gmtStart: "2021-10-25 15:00:00",
          gmtEnd: "2021-11-25 00:00:00",
        },
      ],
      extInfo: {
        corpAddress: "杭州未来组织park",
        applicantName: "瀚博",
        applyTime: "2021-10-25 12:12:12",
        visitorName: "郝汉森",
        visitorMobile: "86-12345678901",
        visitorCorpInfo: "海云钉网络",
        visitorExtInfo: "访客ID：20210907001",
        remarks: "欢迎光临",
        corpWebsite: "www.dingtalk.com",
      },
    }
    axios
      .post(this.state.domain + "/updateinstance", JSON.stringify(data), {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        if (res.data.success) {
          message.success("更新成功，请到手机钉钉--右上角+--钉工牌中查看")
        }
      })
      .catch((error) => {
        alert("updateDingTalkFinance err " + JSON.stringify(error))
      })
  }
  // 钉工牌电子码解码
  getDingTalkCode(payCode) {
    const data = {
      payCode: payCode, // code
      requestId: "250134742608720142-V0ami1d7o7vap4uwlx",
    }
    axios
      .post(this.state.domain + "/decode", JSON.stringify(data), {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        if (res.data.success) {
          message.success("钉工牌电子码解码已成功，付款码：" + res.data.data.body.alipayCode)
        }
      })
      .catch((error) => {
        alert("getDingTalkCode err " + JSON.stringify(error))
      })
  }
  // 钉工牌电子码验证
  authDingTalkCode() {
    const data = {
      corpId: this.state.corpId, //  corpId
      payCode: this.state.payCode, //  code
      userCorpRelationType: "INTERNAL_STAFF",
      userIdentity: this.state.userId, //  userId
      verifyLocation: "未来park",
      verifyResult: true,
      verifyTime: "2021-10-26 10:30:06",
    }
    axios
      .post(this.state.domain + "/verify", JSON.stringify(data), {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        if (res.data.success) {
          message.success("钉工牌电子码验证通过")
        } else {
          message.error("暂无权限")
        }
      })
      .catch((error) => {
        alert("authDingTalkCode err " + JSON.stringify(error))
      })
  }
  // 钉工牌同步支付结果
  asyncDingTalkPayResult() {
    const data = {
      amount: "0.01",
      chargeAmount: "0",
      corpId: this.state.corpId, //  corpId
      gmtTradeCreate: "2021-10-26 12:11:24",
      gmtTradeFinish: "2021-10-26 12:11:24",
      merchantName: "杭州钉钉网络科技有限公司",
      payChannelDetailList: [
        {
          amount: "0.01",
          fundToolDetailList: [
            {
              amount: "0.01",
              extInfo: "",
              fundToolName: "数字食堂余额",
              gmtCreate: "2021-10-26 12:11:24",
              gmtFinish: "2021-10-26 12:11:24",
              promotionFundTool: false,
            },
          ],
          payChannelName: "数字食堂余额",
          payChannelOrderNo: "37164671863227332",
          payChannelType: "BALANCE",
          promotionAmount: "0.00",
        },
      ],
      payCode: this.state.payCode, //  code
      promotionAmount: "0",
      remark: "钉钉支付码",
      title: "陈军 下单 0.01 元（数字食堂）",
      tradeNo: "2021102612112383609613ecf6b2e232",
      tradeStatus: "SUCCESS",
      userId: this.state.userId, //  userId
    }
    axios
      .post(this.state.domain + "/notify", JSON.stringify(data), {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        if (res.data.success) {
          message.success("支付结果同步成功")
        }
      })
      .catch((error) => {
        alert("asyncDingTalkPayResult err " + JSON.stringify(error))
      })
  }
  // 钉工牌同步退款结果
  asyncDingTalkReturnResult() {
    const data = {
      corpId: this.state.corpId, //  corpId
      userId: this.state.userId, //  userId
      tradeNo: "2021102612112383609613ecf6b2e232",
      refundOrderNo: "2021102612112383609613ecf6b2e223",
      remark: "钉钉退款",
      refundAmount: "0.01",
      refundPromotionAmount: "0.00",
      gmtRefund: "2021-10-26 12:11:24",
      payCode: this.state.payCode, //  code
      payChannelDetailList: [
        {
          amount: "0.01",
          fundToolDetailList: [
            {
              amount: "0.01",
              extInfo: "",
              fundToolName: "数字食堂余额",
              gmtCreate: "2021-10-26 12:11:24",
              gmtFinish: "2021-10-26 12:11:24",
              promotionFundTool: false,
            },
          ],
          payChannelName: "数字食堂余额",
          payChannelOrderNo: "37164671863227332",
          payChannelRefundOrderNo: "37164671864327332",
          payChannelType: "BALANCE",
          promotionAmount: "0.00",
        },
      ],
    }
    axios
      .post(this.state.domain + "/refund", JSON.stringify(data), {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        if (res.data.success) {
          message.success("退款结果同步成功")
        }
      })
      .catch((error) => {
        alert("asyncDingTalkReturnResult err " + JSON.stringify(error))
      })
  }

  login() {
    axios
      .get(this.state.domain + "/getCorpId")
      .then((res) => {
        if (res.data) {
          this.loginAction(res.data)
          this.setState({ corpId: res.data })
        }
      })
      .catch((error) => {
        alert("corpId err, " + JSON.stringify(error))
      })
  }
  loginAction(corpId) {
    let _this = this
    dd.runtime.permission.requestAuthCode({
      corpId: corpId, //企业 corpId
      onSuccess: function (res) {
        // 调用成功时回调
        _this.state.authCode = res.code
        axios
          .get(_this.state.domain + "/login?authCode=" + _this.state.authCode)
          .then((res) => {
            if (res && res.data.success) {
              let userId = res.data.data.userId
              let userName = res.data.data.userName
              message.success("登录成功，你好:" + userName)
                _this.setState({
                  userId: userId,
                  userName: userName,
                })
            } else {
              alert("login failed --->" + JSON.stringify(res))
            }
          })
          .catch((error) => {
            alert("httpRequest failed --->" + JSON.stringify(error))
          })
      },
      onFail: function (err) {
        // 调用失败时回调
        alert("requestAuthCode failed --->" + JSON.stringify(err))
      },
    })
  }
}

export default withRouter(App)