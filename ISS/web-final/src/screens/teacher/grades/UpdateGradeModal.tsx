import { Button, Col, Form, Input, Modal, Row } from "antd";
import { useEffect } from "react";
import { toast } from "react-toastify";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";

const requiredField = { required: true, message: "This field is required" };

interface ApproveOptionalCourseModalProps {
  visible: boolean;
  onOk: () => void;
  onCancel: () => void;
  enrolment?: any;
}

export const UpdateGradeModal = ({
  visible,
  onOk,
  onCancel,
  enrolment,
}: ApproveOptionalCourseModalProps) => {
  const [form] = Form.useForm();
  useEffect(() => {
    if (!visible) return;

    form.resetFields();
  }, [visible, form]);

  useEffect(() => {
    form.setFieldsValue({
      studentName: enrolment?.studentName,
      grade: enrolment?.grade ?? 0,
    });
  }, [enrolment]);

  const handleSubmit = (data: any) => {
    api
      .put(ApiEndpoints.grades.updateGrade, {
        enrolmentId: Number(enrolment.enrolmentId),
        grade: Number(data.grade),
      })
      .then(() => {
        toast("Grade updated successfully", {
          type: "success",
        });
      })
      .then(onOk)
      .catch((e) => toast(e.message, { type: "error" }));
  };

  return (
    <Modal
      title="Update student grade"
      visible={visible}
      onCancel={onCancel}
      footer={null}
    >
      <Form
        onFinish={handleSubmit}
        labelCol={{ span: 6 }}
        wrapperCol={{ span: 18 }}
        form={form}
      >
        <Form.Item label="Student's name" name="studentName">
          <Input disabled />
        </Form.Item>
        <Form.Item label="Grade" name="grade" rules={[requiredField]}>
          <Input type="number" />
        </Form.Item>
        <Row>
          <Col span={6} offset={18}>
            <Button type="primary" htmlType="submit">
              Update grade
            </Button>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};
