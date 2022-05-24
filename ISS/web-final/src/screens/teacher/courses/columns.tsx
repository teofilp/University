import { ColumnsType } from "antd/lib/table";

export const columns: ColumnsType<any> = [
  {
    title: "Discipline",
    dataIndex: "courseName",
    align: "center",
  },
  {
    title: "Specialization",
    dataIndex: "specialization",
    align: "center",
  },
  {
    title: "Semester",
    dataIndex: "semester",
    align: "center",
  },
  {
    title: "Year",
    dataIndex: "year",
    align: "center",
  },
  {
    title: "Optional",
    dataIndex: "isOptional",
    align: "center",
    render: (isOptional: boolean) => (isOptional ? "Yes" : "No"),
  },
  {
    title: "Approved",
    dataIndex: "approved",
    align: "center",
    render: (approved: boolean) => (approved ? "Yes" : "No"),
  },
  {
    title: "Max Students Number",
    dataIndex: "maxStudentsNumber",
    align: "center",
    render: (_, record) =>
      record.isOptional ? record.maxStudentsNumber : "N/A",
  },
];
