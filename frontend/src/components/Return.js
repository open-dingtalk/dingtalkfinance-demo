import react, { useEffect, useState } from "react"
import { Form, Input, Button } from "antd"
import { DatePicker } from "antd-mobile"
import moment from "moment"

const Return = (props) => {
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
                <Form.Item label="退款金额" name="refundAmount">
                    <Input placeholder="请输入退款金额" />
                </Form.Item>
                <Form.Item
                    label="退款时间"
                    name="gmtRefund"
                    rules={[{ required: true, message: "退款时间必选" }]}
                >
                    <div className="table">
                        <Button type="primary" onClick={() => setPickerV(true)}>
                            {time ? "已选择退款时间" : "选择退款时间"}
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
                                gmtRefund: moment(val).format("YYYY-MM-DD HH:mm:ss"),
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

                <Button htmlType="submit" type="primary">
                    提交
                </Button>
            </Form>
        </div>
    )
}

export default Return
