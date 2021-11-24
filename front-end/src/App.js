import * as dd from "dingtalk-jsapi"
import axios from "axios"
import React from "react"
import "./App.css"
import { message, Button } from "antd"
import { Link, withRouter } from "react-router-dom"
import Work from "./components/Work"
import Create from "./components/Create"
import Pay from "./components/Pay"
import Return from "./components/Return"
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
      codeId: ""
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
            <p><Button type="primary" onClick={() => this.setState({ showType: 2 })}>
              创建个人钉工牌电子码
            </Button></p>
            <p><Button type="primary" onClick={() => this.setState({ showType: 3 })}>
              更新个人钉工牌电子码
            </Button></p>
            <p><Button
              type="primary"
              onClick={() => this.setState({ showType: 1 })}
            >
              钉工牌电子码解码
            </Button></p>
            <p><Button type="primary" onClick={() => this.authDingTalkCode()}>
              钉工牌同步电子码验证结果
            </Button></p>
            <p><Button
              type="primary"
              onClick={() => this.setState({ showType: 4 })}
            >
              钉工牌同步支付结果
            </Button></p>
            <p><Button
              type="primary"
              onClick={() => this.setState({ showType: 5 })}
            >
              钉工牌同步退款结果
            </Button></p>
            
          </div>
        )}
        {/* 同步退款结果 */}
        {this.state.showType === 5 && (
            <Return
                title={"同步退款"}
                onClick={(e) => this.asyncDingTalkReturnResult(e)}
            />
        )}
        {/* 同步支付结果 */}
        {this.state.showType === 4 && (
            <Pay
                title={"同步支付"}
                onClick={(e) => this.asyncDingTalkPayResult(e)}
            />
        )}
        {/* 创建电子码 */}
        {this.state.showType === 2 && (
            <Create
                title={"创建电子码"}
                onClick={(e) => this.createDingTalkFinance(e)}
            />
        )}
        {/* 更新电子码 */}
        {this.state.showType === 3 && (
            <Create
                title={"更新电子码"}
                onClick={(e) => this.updateDingTalkFinance(e)}
            />
        )}
        {/* 电子码解码 */}
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
      corpId: this.state.corpId,
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
  createDingTalkFinance(userData) {
    const { applyTime,applicantName,visitorName,visitorMobile } = userData
    let data = {
      requestId: "qwerttyui",
      codeIdentity: "DT_VISITOR",
      status: "OPEN",
      corpId: this.state.corpId, //  corpId
      userCorpRelationType: "INTERNAL_STAFF",
      userIdentity: this.state.userId, //  userId
      gmtExpired: "2021-11-28 00:00:00",
      availableTimes: [
        {
          gmtStart: "2021-10-28 15:00:00",
          gmtEnd: "2021-11-28 00:00:00",
        },
      ],
      extInfo: {
        corpAddress: "杭州未来组织park",
        applicantName: applicantName,
        applyTime: applyTime,
        visitorName: visitorName,
        visitorMobile: visitorMobile,
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
            showType: 0
          })
        }
      })
      .catch((error) => {
        alert("createDingTalkFinance err " + JSON.stringify(error))
      })
  }
  // 更新个人钉工牌电子码
  updateDingTalkFinance(userData) {
    // if (!this.state.codeId) {
    //   message.error("请先创建钉工牌电子码")
    // }
    const { applyTime,applicantName,visitorName,visitorMobile } = userData
    let data = {
      codeId: this.state.codeId, //  code
      codeIdentity: "DT_VISITOR",
      status: "OPEN",
      corpId: this.state.corpId, //  corpId
      userCorpRelationType: "INTERNAL_STAFF",
      userIdentity: this.state.userId, //  userId
      gmtExpired: "2021-11-28 00:00:00",
      availableTimes: [
        {
          gmtStart: "2021-10-28 15:00:00",
          gmtEnd: "2021-11-28 00:00:00",
        },
      ],
      extInfo: {
        corpAddress: "杭州未来组织park",
        applicantName: applicantName,
        applyTime: applyTime,
        visitorName: visitorName,
        visitorMobile: visitorMobile,
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
          this.setState({
            showType: 0
          })
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
      requestId: "250134742608720142-V0ami1d7o7vap4uwlx", // 随机生成即可
    }
    axios
      .post(this.state.domain + "/decode", JSON.stringify(data), {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        if (res.data.success) {
          message.success("钉工牌电子码解码成功！付款码：" + res.data.data.body.alipayCode)
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
      verifyTime: "2021-10-28 10:30:06",
    }
    axios
      .post(this.state.domain + "/verify", JSON.stringify(data), {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        if (res.data.success) {
          message.success("钉工牌验证同步成功")
        } else {
          message.error("钉工牌验证同步失败")
        }
      })
      .catch((error) => {
        alert("authDingTalkCode err " + JSON.stringify(error))
      })
  }
  // 钉工牌同步支付结果
  asyncDingTalkPayResult(payData) {
    const { merchantName,title,amount,gmtTradeCreate,gmtTradeFinish } = payData
    const data = {
      amount: amount,
      chargeAmount: "0",
      corpId: this.state.corpId, //  corpId
      gmtTradeCreate: gmtTradeFinish,
      gmtTradeFinish: gmtTradeFinish,
      merchantName: merchantName,
      payChannelDetailList: [
        {
          amount: "0.01",
          fundToolDetailList: [
            {
              amount: "0.01",
              extInfo: "",
              fundToolName: "数字食堂余额",
              gmtCreate: "2021-10-28 12:11:24",
              gmtFinish: "2021-10-28 12:11:24",
              promotionFundTool: false,
            },
          ],
          payChannelName: "数字食堂余额",
          payChannelOrderNo: "37164671863227333",
          payChannelType: "BALANCE",
          promotionAmount: "0.00",
        },
      ],
      payCode: this.state.payCode, //  code
      promotionAmount: "0",
      remark: "钉钉支付码",
      title: title,
      tradeNo: "2021102612112383609613ecf6b2e222",
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
          this.setState({
            showType: 0
          })
        }
      })
      .catch((error) => {
        alert("asyncDingTalkPayResult err " + JSON.stringify(error))
      })
  }
  // 钉工牌同步退款结果
  asyncDingTalkReturnResult(returnData) {
    const { refundAmount,gmtRefund} = returnData
    const data = {
      corpId: this.state.corpId, //  corpId
      userId: this.state.userId, //  userId
      tradeNo: "2021102612112383609613ecf6b2e222",
      refundOrderNo: "2021102612112383609613ecf6b2e222",
      remark: "钉钉退款",
      refundAmount: refundAmount,
      refundPromotionAmount: "0.00",
      gmtRefund: gmtRefund,
      payCode: this.state.payCode, //  code
      payChannelDetailList: [
        {
          amount: "0.01",
          fundToolDetailList: [
            {
              amount: "0.01",
              extInfo: "",
              fundToolName: "数字食堂余额",
              gmtCreate: "2021-10-28 12:11:24",
              gmtFinish: "2021-10-28 12:11:24",
              promotionFundTool: false,
            },
          ],
          payChannelName: "数字食堂余额",
          payChannelOrderNo: "37164671863227333",
          payChannelRefundOrderNo: "37164671863227333",
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
          this.setState({
            showType: 0
          })
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
                  corpId: corpId,
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
