import React, { useContext, useEffect, useState } from "react";
import { Table } from "antd";
import { columns } from "./columns";
import { StudentGrade } from "../../../models/StudentGrade";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";
import { UserContext } from "../../../context/UserContext";

import "./main.css";

export const GradesContent = () => {
  const { user } = useContext(UserContext);
  const [data, setData] = useState<StudentGrade[]>([]);

  useEffect(() => {
    api
      .get<StudentGrade[]>(`${ApiEndpoints.grades.getStudentGrades}/${user.id}`)
      .then(({ data }) => setData(data));
  }, []);

  return (
    <div className="site-layout-content">
      <Table
        className="table-content"
        dataSource={data}
        columns={columns}
        pagination={false}
      />
    </div>
  );
};
