import React, { useEffect, useState } from "react";
import "./main.css";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";
import { OptionalCourse } from "../../../models/OptionalCourse";
import { Table } from "antd";
import { getColumns } from "./columns";
import { ApproveCourseModal } from "./ApproveCourseModal";

export const OptionalCoursesContent = () => {
  const [courses, setCourses] = useState<OptionalCourse[]>([]);
  const [isLoading, setLoading] = useState(false);
  const [selectedCourse, setSelectedCourse] = useState<
    OptionalCourse | undefined
  >();
  const [isOpen, setOpen] = useState(false);

  const fetchData = () => {
    setLoading(true);
    api
      .get<OptionalCourse[]>(ApiEndpoints.courses.getOptionalCourses)
      .then(({ data }) => setCourses(data))
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    fetchData();
  }, []);

  const tryApproveOptionalCourse = (course: OptionalCourse) => {
    setSelectedCourse(course);
    setOpen(true);
  };

  return (
    <>
      <h2 className="page-title">Approve optional courses</h2>
      <Table
        dataSource={courses}
        columns={getColumns(tryApproveOptionalCourse)}
        className="table-content"
        pagination={false}
        loading={isLoading}
      />
      <ApproveCourseModal
        visible={isOpen}
        onOk={() => {
          setOpen(false);
          fetchData();
        }}
        onCancel={() => setOpen(false)}
        course={selectedCourse}
      />
    </>
  );
};
