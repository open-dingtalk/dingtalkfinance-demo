import react, { useEffect, useState } from "react"
import { Form, Input, Button } from "antd"
import { DatePicker } from "antd-mobile"
import moment from "moment"

const Create = (props) => {
  const [form] = Form.useForm()
  const [pickerV, setPickerV] = useState(false)
  const [time, settime] = useState(null)

  const onSubmit = (data) => {
    props.onClick(data)
  }
  const now = new Date()
  return (
    <div>
      <h4 className="title">钉工牌——{props.title}</h4>
      <Form form={form} onFinish={onSubmit}>
        <Form.Item label="申请人名称" name="applicantName">
          <Input placeholder="请输入申请人名称" />
        </Form.Item>
        <Form.Item
            label="申请时间"
            name="applyTime"
            rules={[{ required: true, message: "申请时间必选" }]}
        >
          <div className="table">
            <Button type="primary" onClick={() => setPickerV(true)}>
              {time ? "已选择申请时间" : "选择申请时间"}
            </Button>
          </div>

          <DatePicker
              visible={pickerV}
              onClose={() => {
                setPickerV(false)
              }}
              min={new Date()}
              precision="second"
              onConfirm={(val, s) => {
                settime(val)
                form.setFieldsValue({
                  applyTime: val,
                })
              }}
          >
            {(value) => {
              return value
                  ? moment(value).format("YYYY-MM-DD HH:mm:ss")
                  : moment(now).format("YYYY-MM-DD HH:mm:ss")
            }}
          </DatePicker>
        </Form.Item>
        <Form.Item label="访客名称" name="visitorName">
          <Input placeholder="请输入访客名称" />
        </Form.Item>
        <Form.Item label="访客手机号" name="visitorMobile">
          <Input placeholder="请输入访客手机号" />
        </Form.Item>

        <Button htmlType="submit" type="primary">
          提交
        </Button>
      </Form>
    </div>
  )
}

export default Create
