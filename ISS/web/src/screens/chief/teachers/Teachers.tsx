import { useEffect, useState } from "react";
import { PdfTable } from "../../../components/pdf-table/PdfTable";
import { columns } from "./columns";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";
import { ChiefTeacher } from "../../../models/ChiefTeacher";

export const Teachers = () => {
  const [courses, setCourses] = useState<ChiefTeacher[]>([]);

  useEffect(() => {
    api
      .get<ChiefTeacher[]>(ApiEndpoints.teachers.base)
      .then(({ data }) => setCourses(data));
  }, []);

  return (
    <PdfTable
      title="Teachers"
      columns={columns}
      dataSource={courses}
      orientation="landscape"
      size={12}
    />
  );
};
