import react, { useEffect, useState } from "react"
import { Form, Input, Button } from "antd"
import { DatePicker } from "antd-mobile"
import moment from "moment"

const Pay = (props) => {
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
                <Form.Item label="商户名称" name="merchantName">
                    <Input placeholder="请输入商户名称" />
                </Form.Item>
                <Form.Item label="订单标题" name="title">
                    <Input placeholder="请输入订单标题" />
                </Form.Item>
                <Form.Item label="订单金额" name="amount">
                    <Input placeholder="请输入订单金额" />
                </Form.Item>
                <Form.Item
                    label="交易开始时间"
                    name="gmtTradeCreate"
                    rules={[{ required: true, message: "交易开始时间必选" }]}
                >
                    <div className="table">
                        <Button type="primary" onClick={() => setPickerV(true)}>
                            {time ? "已选择交易开始时间" : "选择交易开始时间"}
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
                                gmtTradeCreate: val,
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
                <Form.Item
                    label="交易结束时间"
                    name="gmtTradeFinish"
                    rules={[{ required: true, message: "交易结束时间必选" }]}
                >
                    <div className="table">
                        <Button type="primary" onClick={() => setPickerV(true)}>
                            {time ? "已选择交易结束时间" : "选择交易结束时间"}
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
                                gmtTradeFinish: val,
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

export default Pay
