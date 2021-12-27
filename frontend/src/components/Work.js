import react, { useEffect } from "react"
import { Form, Input, Button } from "antd"

const Work = (props) => {
  const [form] = Form.useForm()

  const onSubmit = (data) => {
    props.onClick(data)
  }

  return (
    <div>
      <h4 className="title">钉工牌</h4>
      <Form form={form} onFinish={onSubmit}>
        <Form.Item label="支付电子码" name="payCode">
          <Input placeholder="请输入钉工牌电子码，该码值请扫描钉工牌电子码获取" />
        </Form.Item>

        <Button htmlType="submit" type="primary">
          提交
        </Button>
      </Form>
    </div>
  )
}

export default Work
