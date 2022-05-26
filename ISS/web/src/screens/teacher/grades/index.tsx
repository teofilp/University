import { Select, Table } from "antd";
import { useContext, useEffect, useState } from "react";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";
import { UserContext } from "../../../context/UserContext";
import { TeacherGrades as TeacherGradesModel } from "../../../models/TeacherGrades";
import { getColumns } from "./columns";
import "./main.css";
import { UpdateGradeModal } from "./UpdateGradeModal";

export const TeacherGrades = () => {
  const { user } = useContext(UserContext);
  const [courseName, setCourseName] = useState<string | undefined>();
  const [isLoading, setLoading] = useState<boolean>(false);
  const [teacherGrades, setTeacherGrades] = useState<TeacherGradesModel[]>([]);
  const [coursesOptions, setCoursesOptions] = useState<
    { value: string; label: string }[]
  >([]);
  const [isOpen, setOpen] = useState(false);
  const [activeEnrolment, setActiveEnrolment] = useState<any>();

  const fetchGrades = () => {
    setLoading(true);
    return api
      .get<TeacherGradesModel[]>(
        `${ApiEndpoints.grades.getTeacherGrades}/${user.id}`
      )
      .then(({ data }) => {
        setTeacherGrades(data);
        return data;
      })
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    fetchGrades().then((data) => {
      setCoursesOptions(
        data.map((x) => ({
          value: x.courseName,
          label: x.courseName,
        }))
      );
      setCourseName(data[0]?.courseName);
    });
  }, []);

  const selectEnrolment = (record: any) => {
    setActiveEnrolment(record);
    setOpen(true);
  };

  return (
    <>
      <h2 className="page-title">Manage students grades</h2>
      <span className="label">Select course</span>
      <Select
        value={courseName}
        onChange={setCourseName}
        loading={isLoading}
        options={coursesOptions}
      />
      <Table
        style={{ marginTop: 32 }}
        columns={getColumns(selectEnrolment)}
        loading={isLoading}
        className="table-content"
        dataSource={
          teacherGrades.find((x) => x.courseName === courseName)
            ?.teacherGrades || []
        }
        pagination={false}
      />
      <UpdateGradeModal
        enrolment={activeEnrolment}
        visible={isOpen}
        onCancel={() => setOpen(false)}
        onOk={() => {
          setOpen(false);
          fetchGrades();
        }}
      />
    </>
  );
};
