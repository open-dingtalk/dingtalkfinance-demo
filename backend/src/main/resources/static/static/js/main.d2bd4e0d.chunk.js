(this["webpackJsonpfront-end"]=this["webpackJsonpfront-end"]||[]).push([[0],{278:function(e,t,n){},521:function(e,t,n){},622:function(e,t,n){"use strict";n.r(t);var a=n(0),s=n.n(a),i=n(25),r=n.n(i),c=(n(278),n(13)),o=n(18),l=n(22),d=n(23),u=n(186),m=n(53),h=n.n(m),p=(n(521),n(207)),j=n(208),f=n(28),y=n(10),b=n(624),O=n(625),T=n(6),g=function(e){var t=b.a.useForm(),n=Object(y.a)(t,1)[0];return Object(T.jsxs)("div",{children:[Object(T.jsx)("h4",{className:"title",children:"\u9489\u5de5\u724c"}),Object(T.jsxs)(b.a,{form:n,onFinish:function(t){e.onClick(t)},children:[Object(T.jsx)(b.a.Item,{label:"\u652f\u4ed8\u7535\u5b50\u7801",name:"payCode",children:Object(T.jsx)(O.a,{placeholder:"\u8bf7\u8f93\u5165\u9489\u5de5\u724c\u7535\u5b50\u7801\uff0c\u8be5\u7801\u503c\u8bf7\u626b\u63cf\u9489\u5de5\u724c\u7535\u5b50\u7801\u83b7\u53d6"})}),Object(T.jsx)(p.a,{htmlType:"submit",type:"primary",children:"\u63d0\u4ea4"})]})]})},C=n(65),x=n(36),I=n.n(x),v=function(e){var t=b.a.useForm(),n=Object(y.a)(t,1)[0],s=Object(a.useState)(!1),i=Object(y.a)(s,2),r=i[0],c=i[1],o=Object(a.useState)(null),l=Object(y.a)(o,2),d=l[0],u=l[1],m=new Date;return Object(T.jsxs)("div",{children:[Object(T.jsxs)("h4",{className:"title",children:["\u9489\u5de5\u724c\u2014\u2014",e.title]}),Object(T.jsxs)(b.a,{form:n,onFinish:function(t){e.onClick(t)},children:[Object(T.jsx)(b.a.Item,{label:"\u7533\u8bf7\u4eba\u540d\u79f0",name:"applicantName",children:Object(T.jsx)(O.a,{placeholder:"\u8bf7\u8f93\u5165\u7533\u8bf7\u4eba\u540d\u79f0"})}),Object(T.jsxs)(b.a.Item,{label:"\u7533\u8bf7\u65f6\u95f4",name:"applyTime",rules:[{required:!0,message:"\u7533\u8bf7\u65f6\u95f4\u5fc5\u9009"}],children:[Object(T.jsx)("div",{className:"table",children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return c(!0)},children:d?"\u5df2\u9009\u62e9\u7533\u8bf7\u65f6\u95f4":"\u9009\u62e9\u7533\u8bf7\u65f6\u95f4"})}),Object(T.jsx)(C.a,{visible:r,onClose:function(){c(!1)},min:new Date,precision:"second",onConfirm:function(e,t){u(e),n.setFieldsValue({applyTime:I()(e).format("YYYY-MM-DD HH:mm:ss")})},children:function(e){return e?I()(e).format("YYYY-MM-DD HH:mm:ss"):I()(m).format("YYYY-MM-DD HH:mm:ss")}})]}),Object(T.jsx)(b.a.Item,{label:"\u8bbf\u5ba2\u540d\u79f0",name:"visitorName",children:Object(T.jsx)(O.a,{placeholder:"\u8bf7\u8f93\u5165\u8bbf\u5ba2\u540d\u79f0"})}),Object(T.jsx)(b.a.Item,{label:"\u8bbf\u5ba2\u624b\u673a\u53f7",name:"visitorMobile",children:Object(T.jsx)(O.a,{placeholder:"\u8bf7\u8f93\u5165\u8bbf\u5ba2\u624b\u673a\u53f7"})}),Object(T.jsx)(p.a,{htmlType:"submit",type:"primary",children:"\u63d0\u4ea4"})]})]})},D=function(e){var t=b.a.useForm(),n=Object(y.a)(t,1)[0],s=Object(a.useState)(!1),i=Object(y.a)(s,2),r=i[0],c=i[1],o=Object(a.useState)(null),l=Object(y.a)(o,2),d=l[0],u=l[1],m=new Date;return Object(T.jsxs)("div",{children:[Object(T.jsxs)("h4",{className:"title",children:["\u9489\u5de5\u724c\u2014\u2014",e.title]}),Object(T.jsxs)(b.a,{form:n,onFinish:function(t){e.onClick(t)},children:[Object(T.jsx)(b.a.Item,{label:"\u5546\u6237\u540d\u79f0",name:"merchantName",children:Object(T.jsx)(O.a,{placeholder:"\u8bf7\u8f93\u5165\u5546\u6237\u540d\u79f0"})}),Object(T.jsx)(b.a.Item,{label:"\u8ba2\u5355\u6807\u9898",name:"title",children:Object(T.jsx)(O.a,{placeholder:"\u8bf7\u8f93\u5165\u8ba2\u5355\u6807\u9898"})}),Object(T.jsx)(b.a.Item,{label:"\u8ba2\u5355\u91d1\u989d",name:"amount",children:Object(T.jsx)(O.a,{placeholder:"\u8bf7\u8f93\u5165\u8ba2\u5355\u91d1\u989d"})}),Object(T.jsxs)(b.a.Item,{label:"\u4ea4\u6613\u5f00\u59cb\u65f6\u95f4",name:"gmtTradeCreate",children:[Object(T.jsx)("div",{className:"table",children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return c(!0)},children:d?"\u5df2\u9009\u62e9\u4ea4\u6613\u5f00\u59cb\u65f6\u95f4":"\u9009\u62e9\u4ea4\u6613\u5f00\u59cb\u65f6\u95f4"})}),Object(T.jsx)(C.a,{visible:r,onClose:function(){c(!1)},min:new Date,precision:"second",onConfirm:function(e,t){u(e),n.setFieldsValue({gmtTradeCreate:I()(e).format("YYYY-MM-DD HH:mm:ss")})},children:function(e){return e?I()(e).format("YYYY-MM-DD HH:mm:ss"):I()(m).format("YYYY-MM-DD HH:mm:ss")}})]}),Object(T.jsxs)(b.a.Item,{label:"\u4ea4\u6613\u7ed3\u675f\u65f6\u95f4",name:"gmtTradeFinish",rules:[{required:!0,message:"\u4ea4\u6613\u7ed3\u675f\u65f6\u95f4\u5fc5\u9009"}],children:[Object(T.jsx)("div",{className:"table",children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return c(!0)},children:d?"\u5df2\u9009\u62e9\u4ea4\u6613\u7ed3\u675f\u65f6\u95f4":"\u9009\u62e9\u4ea4\u6613\u7ed3\u675f\u65f6\u95f4"})}),Object(T.jsx)(C.a,{visible:r,onClose:function(){c(!1)},min:new Date,precision:"second",onConfirm:function(e,t){u(e),n.setFieldsValue({gmtTradeFinish:I()(e).format("YYYY-MM-DD HH:mm:ss")})},children:function(e){return e?I()(e).format("YYYY-MM-DD HH:mm:ss"):I()(m).format("YYYY-MM-DD HH:mm:ss")}})]}),Object(T.jsx)(p.a,{htmlType:"submit",type:"primary",children:"\u63d0\u4ea4"})]})]})},k=function(e){var t=b.a.useForm(),n=Object(y.a)(t,1)[0],s=Object(a.useState)(!1),i=Object(y.a)(s,2),r=i[0],c=i[1],o=Object(a.useState)(null),l=Object(y.a)(o,2),d=l[0],u=l[1],m=new Date;return Object(T.jsxs)("div",{children:[Object(T.jsxs)("h4",{className:"title",children:["\u9489\u5de5\u724c\u2014\u2014",e.title]}),Object(T.jsxs)(b.a,{form:n,onFinish:function(t){e.onClick(t)},children:[Object(T.jsx)(b.a.Item,{label:"\u9000\u6b3e\u91d1\u989d",name:"refundAmount",children:Object(T.jsx)(O.a,{placeholder:"\u8bf7\u8f93\u5165\u9000\u6b3e\u91d1\u989d"})}),Object(T.jsxs)(b.a.Item,{label:"\u9000\u6b3e\u65f6\u95f4",name:"gmtRefund",rules:[{required:!0,message:"\u9000\u6b3e\u65f6\u95f4\u5fc5\u9009"}],children:[Object(T.jsx)("div",{className:"table",children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return c(!0)},children:d?"\u5df2\u9009\u62e9\u9000\u6b3e\u65f6\u95f4":"\u9009\u62e9\u9000\u6b3e\u65f6\u95f4"})}),Object(T.jsx)(C.a,{visible:r,onClose:function(){c(!1)},min:new Date,precision:"second",onConfirm:function(e,t){u(e),n.setFieldsValue({gmtRefund:I()(e).format("YYYY-MM-DD HH:mm:ss")})},children:function(e){return e?I()(e).format("YYYY-MM-DD HH:mm:ss"):I()(m).format("YYYY-MM-DD HH:mm:ss")}})]}),Object(T.jsx)(p.a,{htmlType:"submit",type:"primary",children:"\u63d0\u4ea4"})]})]})},N=(n(614),function(e){Object(l.a)(n,e);var t=Object(d.a)(n);function n(e){var a;return Object(c.a)(this,n),(a=t.call(this,e)).state={domain:"",corpId:"",userId:"",userName:"",payCode:"",showType:0,codeId:"",now:I()(new Date).format("YYYY-MM-DD HH:mm:ss"),monthLater:I()().add(30,"days").format("YYYY-MM-DD HH:mm:ss"),requestId:a.randomString()},a}return Object(o.a)(n,[{key:"render",value:function(){var e=this;return""===this.state.userId&&this.login(),Object(T.jsxs)("div",{className:"App",children:[0===this.state.showType&&Object(T.jsxs)("div",{children:[Object(T.jsx)("p",{children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return e.saveDingTalkFinance()},children:"\u4f01\u4e1a\u914d\u7f6e\u9489\u5de5\u724c"})}),Object(T.jsx)("p",{children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return e.setState({showType:2})},children:"\u521b\u5efa\u4e2a\u4eba\u9489\u5de5\u724c\u7535\u5b50\u7801"})}),Object(T.jsx)("p",{children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return e.setState({showType:3})},children:"\u66f4\u65b0\u4e2a\u4eba\u9489\u5de5\u724c\u7535\u5b50\u7801"})}),Object(T.jsx)("p",{children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return e.setState({showType:1})},children:"\u9489\u5de5\u724c\u7535\u5b50\u7801\u89e3\u7801"})}),Object(T.jsx)("p",{children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return e.authDingTalkCode()},children:"\u9489\u5de5\u724c\u540c\u6b65\u7535\u5b50\u7801\u9a8c\u8bc1\u7ed3\u679c"})}),Object(T.jsx)("p",{children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return e.setState({showType:4})},children:"\u9489\u5de5\u724c\u540c\u6b65\u652f\u4ed8\u7ed3\u679c"})}),Object(T.jsx)("p",{children:Object(T.jsx)(p.a,{type:"primary",onClick:function(){return e.setState({showType:5})},children:"\u9489\u5de5\u724c\u540c\u6b65\u9000\u6b3e\u7ed3\u679c"})})]}),5===this.state.showType&&Object(T.jsx)(k,{title:"\u540c\u6b65\u9000\u6b3e",onClick:function(t){return e.asyncDingTalkReturnResult(t)}}),4===this.state.showType&&Object(T.jsx)(D,{title:"\u540c\u6b65\u652f\u4ed8",onClick:function(t){return e.asyncDingTalkPayResult(t)}}),2===this.state.showType&&Object(T.jsx)(v,{title:"\u521b\u5efa\u7535\u5b50\u7801",onClick:function(t){return e.createDingTalkFinance(t)}}),3===this.state.showType&&Object(T.jsx)(v,{title:"\u66f4\u65b0\u7535\u5b50\u7801",onClick:function(t){return e.updateDingTalkFinance(t)}}),1===this.state.showType&&Object(T.jsx)(g,{onClick:function(t){e.setState({payCode:t.payCode,showType:0}),e.getDingTalkCode(t.payCode)}})]})}},{key:"saveDingTalkFinance",value:function(){var e={codeIdentity:"DT_VISITOR",status:"OPEN",corpId:this.state.corpId,extInfo:{xx_key:"xx_value"}};h.a.post(this.state.domain+"/save",JSON.stringify(e),{headers:{"Content-Type":"application/json"}}).then((function(e){e.data.success&&j.b.success("\u4f01\u4e1a\u914d\u7f6e\u9489\u5de5\u724c\u6210\u529f")})).catch((function(e){alert("saveDingTalkFinance err "+JSON.stringify(e))}))}},{key:"createDingTalkFinance",value:function(e){var t=this,n=e.applyTime,a=e.applicantName,s=e.visitorName,i=e.visitorMobile,r={requestId:this.state.requestId,codeIdentity:"DT_VISITOR",status:"OPEN",corpId:this.state.corpId,userCorpRelationType:"INTERNAL_STAFF",userIdentity:this.state.userId,gmtExpired:this.state.monthLater,availableTimes:[{gmtStart:this.state.now,gmtEnd:this.state.monthLater}],extInfo:{corpAddress:"\u676d\u5dde\u672a\u6765\u7ec4\u7ec7park",applicantName:a,applyTime:n,visitorName:s,visitorMobile:i,visitorCorpInfo:"\u9489\u9489\u7f51\u7edc",visitorExtInfo:"\u8bbf\u5ba2ID\uff1a20210907001",remarks:"\u6b22\u8fce\u5149\u4e34",corpWebsite:"www.dingtalk.com"}};h.a.post(this.state.domain+"/createinstance",JSON.stringify(r),{headers:{"Content-Type":"application/json"}}).then((function(e){e.data.success&&(j.b.success("\u521b\u5efa\u6210\u529f\uff0c\u8bf7\u5230\u624b\u673a\u9489\u9489--\u53f3\u4e0a\u89d2+--\u9489\u5de5\u724c\u4e2d\u67e5\u770b"),t.setState({codeId:e.data.data,showType:0}))})).catch((function(e){alert("createDingTalkFinance err "+JSON.stringify(e))}))}},{key:"updateDingTalkFinance",value:function(e){var t=this,n=e.applyTime,a=e.applicantName,s=e.visitorName,i=e.visitorMobile,r={codeId:this.state.codeId,codeIdentity:"DT_VISITOR",status:"OPEN",corpId:this.state.corpId,userCorpRelationType:"INTERNAL_STAFF",userIdentity:this.state.userId,gmtExpired:this.state.monthLater,availableTimes:[{gmtStart:this.state.now,gmtEnd:this.state.monthLater}],extInfo:{corpAddress:"\u676d\u5dde\u672a\u6765\u7ec4\u7ec7park",applicantName:a,applyTime:n,visitorName:s,visitorMobile:i,visitorCorpInfo:"\u6d77\u4e91\u9489\u7f51\u7edc",visitorExtInfo:"\u8bbf\u5ba2ID\uff1a20210907001",remarks:"\u6b22\u8fce\u5149\u4e34",corpWebsite:"www.dingtalk.com"}};h.a.post(this.state.domain+"/updateinstance",JSON.stringify(r),{headers:{"Content-Type":"application/json"}}).then((function(e){e.data.success&&(t.setState({showType:0}),j.b.success("\u66f4\u65b0\u6210\u529f\uff0c\u8bf7\u5230\u624b\u673a\u9489\u9489--\u53f3\u4e0a\u89d2+--\u9489\u5de5\u724c\u4e2d\u67e5\u770b"))})).catch((function(e){alert("updateDingTalkFinance err "+JSON.stringify(e))}))}},{key:"getDingTalkCode",value:function(e){var t={payCode:e,requestId:this.state.requestId};h.a.post(this.state.domain+"/decode",JSON.stringify(t),{headers:{"Content-Type":"application/json"}}).then((function(e){e.data.success&&j.b.success("\u9489\u5de5\u724c\u7535\u5b50\u7801\u89e3\u7801\u6210\u529f\uff01\u4ed8\u6b3e\u7801\uff1a"+e.data.data.body.alipayCode)})).catch((function(e){alert("getDingTalkCode err "+JSON.stringify(e))}))}},{key:"authDingTalkCode",value:function(){var e={corpId:this.state.corpId,payCode:this.state.payCode,userCorpRelationType:"INTERNAL_STAFF",userIdentity:this.state.userId,verifyLocation:"\u672a\u6765park",verifyResult:!0,verifyTime:this.state.now};h.a.post(this.state.domain+"/verify",JSON.stringify(e),{headers:{"Content-Type":"application/json"}}).then((function(e){e.data.success?j.b.success("\u9489\u5de5\u724c\u9a8c\u8bc1\u540c\u6b65\u6210\u529f"):j.b.error("\u9489\u5de5\u724c\u9a8c\u8bc1\u540c\u6b65\u5931\u8d25")})).catch((function(e){alert("authDingTalkCode err "+JSON.stringify(e))}))}},{key:"asyncDingTalkPayResult",value:function(e){var t=this,n=e.merchantName,a=e.title,s=e.amount,i=(e.gmtTradeCreate,e.gmtTradeFinish),r={amount:s,chargeAmount:"0",corpId:this.state.corpId,gmtTradeCreate:i,gmtTradeFinish:i,merchantName:n,payChannelDetailList:[{amount:"0.01",fundToolDetailList:[{amount:"0.01",extInfo:"",fundToolName:"\u6570\u5b57\u98df\u5802\u4f59\u989d",gmtCreate:this.state.now,gmtFinish:this.state.now,promotionFundTool:!1}],payChannelName:"\u6570\u5b57\u98df\u5802\u4f59\u989d",payChannelOrderNo:"37164671863227445",payChannelType:"BALANCE",promotionAmount:"0.00"}],payCode:this.state.payCode,promotionAmount:"0",remark:"\u9489\u9489\u652f\u4ed8\u7801",title:a,tradeNo:this.randomString(),tradeStatus:"SUCCESS",userId:this.state.userId};h.a.post(this.state.domain+"/notify",JSON.stringify(r),{headers:{"Content-Type":"application/json"}}).then((function(e){e.data.success&&(j.b.success("\u652f\u4ed8\u7ed3\u679c\u540c\u6b65\u6210\u529f"),t.setState({showType:0}))})).catch((function(e){alert("asyncDingTalkPayResult err "+JSON.stringify(e))}))}},{key:"asyncDingTalkReturnResult",value:function(e){var t=this,n=e.refundAmount,a=e.gmtRefund,s={corpId:this.state.corpId,userId:this.state.userId,tradeNo:this.randomString(),refundOrderNo:this.randomString(),remark:"\u9489\u9489\u9000\u6b3e",refundAmount:n,refundPromotionAmount:"0.00",gmtRefund:a,payCode:this.state.payCode,payChannelDetailList:[{amount:"0.01",fundToolDetailList:[{amount:"0.01",extInfo:"",fundToolName:"\u6570\u5b57\u98df\u5802\u4f59\u989d",gmtCreate:this.state.now,gmtFinish:this.state.now,promotionFundTool:!1}],payChannelName:"\u6570\u5b57\u98df\u5802\u4f59\u989d",payChannelOrderNo:"37164671863227444",payChannelRefundOrderNo:"37164671863227446",payChannelType:"BALANCE",promotionAmount:"0.00"}]};h.a.post(this.state.domain+"/refund",JSON.stringify(s),{headers:{"Content-Type":"application/json"}}).then((function(e){e.data.success&&(j.b.success("\u9000\u6b3e\u7ed3\u679c\u540c\u6b65\u6210\u529f"),t.setState({showType:0}))})).catch((function(e){alert("asyncDingTalkReturnResult err "+JSON.stringify(e))}))}},{key:"randomString",value:function(e){e=e||32;for(var t="ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678",n=t.length,a="",s=0;s<e;s++)a+=t.charAt(Math.floor(Math.random()*n));return a}},{key:"login",value:function(){var e=this;h.a.get(this.state.domain+"/getCorpId").then((function(t){t.data&&e.loginAction(t.data)})).catch((function(e){alert("corpId err, "+JSON.stringify(e))}))}},{key:"loginAction",value:function(e){var t=this;u.runtime.permission.requestAuthCode({corpId:e,onSuccess:function(n){t.state.authCode=n.code,h.a.get(t.state.domain+"/login?authCode="+t.state.authCode).then((function(n){if(n&&n.data.success){var a=n.data.data.userId,s=n.data.data.userName;j.b.success("\u767b\u5f55\u6210\u529f\uff0c\u4f60\u597d:"+s),t.setState({userId:a,userName:s,corpId:e})}else alert("login failed ---\x3e"+JSON.stringify(n))})).catch((function(e){alert("httpRequest failed ---\x3e"+JSON.stringify(e))}))},onFail:function(e){alert("requestAuthCode failed ---\x3e"+JSON.stringify(e))}})}}]),n}(s.a.Component)),Y=Object(f.e)(N),S=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,626)).then((function(t){var n=t.getCLS,a=t.getFID,s=t.getFCP,i=t.getLCP,r=t.getTTFB;n(e),a(e),s(e),i(e),r(e)}))},w=n(142);r.a.render(Object(T.jsx)(w.a,{basename:"/",children:Object(T.jsx)(f.a,{path:"/",component:Y})}),document.getElementById("root")),S()}},[[622,1,2]]]);
//# sourceMappingURL=main.d2bd4e0d.chunk.js.map