import { Button, Col, Form, Input, Modal, Row } from "antd";
import { useEffect } from "react";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";
import { OptionalCourse } from "../../../models/OptionalCourse";

const requiredField = { required: true, message: "This field is required" };

interface ApproveOptionalCourseModalProps {
  visible: boolean;
  onOk: () => void;
  onCancel: () => void;
  course?: OptionalCourse;
}

export const ApproveCourseModal = ({
  visible,
  onOk,
  onCancel,
  course,
}: ApproveOptionalCourseModalProps) => {
  const [form] = Form.useForm();
  useEffect(() => {
    if (!visible) return;

    form.resetFields();
  }, [visible, form]);

  useEffect(() => {
    form.setFieldsValue({
      name: course?.courseName,
    });
  }, [course]);

  const handleSubmit = (data: any) => {
    api
      .post(ApiEndpoints.courses.approveCourse, {
        courseId: course!.courseId,
        maxStudents: Number(data.maxStudents),
      })
      .then(onOk);
  };

  return (
    <Modal
      title="Approve optional course"
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
        <Form.Item label="Course name" name="name">
          <Input disabled />
        </Form.Item>
        <Form.Item
          label="Max students"
          name="maxStudents"
          rules={[requiredField]}
        >
          <Input type="number" />
        </Form.Item>
        <Row>
          <Col span={6} offset={18}>
            <Button type="primary" htmlType="submit">
              Approve
            </Button>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};
