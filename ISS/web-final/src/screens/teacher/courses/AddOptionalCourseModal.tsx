import { Button, Modal, Form, Input, Select, Row, Col } from "antd";
import { useContext, useEffect, useState } from "react";
import { AddOptionalCourseFormModel } from "./types";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";
import { UserContext } from "../../../context/UserContext";

const requiredField = { required: true, message: "This field is required" };

interface AddOptionalCourseModalProps {
  visible: boolean;
  onOk: () => void;
  onCancel: () => void;
}

export const AddOptionalCourseModal = ({
  visible,
  onOk,
  onCancel,
}: AddOptionalCourseModalProps) => {
  const [form] = Form.useForm();
  const [semesters, setSemesters] = useState([]);
  const [specializations, setSpecializations] = useState([]);
  const { user } = useContext(UserContext);

  const handleSubmit = (data: AddOptionalCourseFormModel) => {
    api
      .post(ApiEndpoints.courses.addOptionalCourse, {
        ...data,
        userId: user.id,
      })
      .then(onOk);
  };

  useEffect(() => {
    if (!visible) return;

    form.resetFields();
  }, [visible, form]);

  useEffect(() => {
    api
      .get<any>(ApiEndpoints.courses.getSemestersAndSpecializations)
      .then(({ data }) => {
        setSemesters(
          data.semesters.map((x: any) => ({
            id: x.id,
            name: `Year ${x.universityYear}, Semester ${x.semesterDetails}`,
          }))
        );

        setSpecializations(data.specializations);
      });
  }, []);

  return (
    <Modal
      title="Add new optional course"
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
        <Form.Item label="Course name" name="name" rules={[requiredField]}>
          <Input />
        </Form.Item>
        <Form.Item label="Semester" name="semesterId" rules={[requiredField]}>
          <Select
            options={semesters}
            fieldNames={{ value: "id", label: "name" }}
          />
        </Form.Item>
        <Form.Item
          label="Specialization"
          name="specializationId"
          rules={[requiredField]}
        >
          <Select
            options={specializations}
            fieldNames={{ value: "id", label: "name" }}
          />
        </Form.Item>
        <Row>
          <Col span={6} offset={18}>
            <Button id="add-course" type="primary" htmlType="submit">
              Add
            </Button>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};
