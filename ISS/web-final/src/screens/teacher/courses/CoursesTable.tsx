import { Table } from "antd";
import { TeacherCourse } from "../../../models/TeacherCourses";
import { columns } from "./columns";

interface CoursesTableProps {
  courses: TeacherCourse[];
  isLoading: boolean;
}

export const CoursesTable = ({ courses, isLoading }: CoursesTableProps) => {
  return (
    <div className="courses-table-container">
      <Table
        className="table-content"
        dataSource={courses}
        columns={columns}
        loading={isLoading}
        pagination={false}
      />
    </div>
  );
};
