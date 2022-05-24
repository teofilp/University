import { ColumnsType } from "antd/lib/table";

export const columns: ColumnsType<any> = [
  {
    title: "Discipline",
    dataIndex: "courseName",
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
  },
  {
    title: "Grade",
    dataIndex: "grade",
    align: "center",
    render: (value: number) => (!value ? "N/A" : value),
  },
];
