import { Table } from "antd";
import { useEffect, useState } from "react";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";
import { PdfTable } from "../../../components/pdf-table/PdfTable";
import { StudentReport } from "../../../models/StudentReport";
import { getColumns } from "./columns";

export const StudentsReports = () => {
  const [students, setStudents] = useState<StudentReport[]>([]);
  const [activeDataset, setActiveDataset] = useState<StudentReport[]>([]);

  useEffect(() => {
    api
      .get<StudentReport[]>(ApiEndpoints.reports.students)
      .then(({ data }) => setStudents(data));
  }, []);

  useEffect(() => {
    setActiveDataset(students);
  }, [students]);

  return (
    <>
      <PdfTable
        title="Students"
        dataSource={activeDataset}
        columns={getColumns(activeDataset)}
        show={false}
        orientation="landscape"
      />
      <Table
        className="table-content"
        columns={getColumns(students) as any[]}
        dataSource={students}
        pagination={false}
        onChange={(_, __, ___, extra) =>
          setActiveDataset(extra.currentDataSource)
        }
      />
    </>
  );
};
