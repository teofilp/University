import { useEffect, useState } from "react";
import { PdfTable } from "../../../components/pdf-table/PdfTable";
import { columns } from "./columns";
import { TeacherCourse } from "../../../models/TeacherCourses";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";

export const Courses = () => {
  const [courses, setCourses] = useState<TeacherCourse[]>([]);

  useEffect(() => {
    api
      .get<TeacherCourse[]>(ApiEndpoints.courses.base)
      .then(({ data }) => setCourses(data));
  }, []);

  return (
    <PdfTable
      title="Courses"
      columns={columns}
      dataSource={courses}
      orientation="landscape"
      size={12}
    />
  );
};
