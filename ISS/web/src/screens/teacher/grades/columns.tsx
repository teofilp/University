import { Button } from "antd";
import { ColumnsType } from "antd/lib/table";

export const getColumns: (cb: (record: any) => void) => ColumnsType<any> = (
  cb
) => [
  {
    title: "Student",
    dataIndex: "studentName",
    align: "center",
  },
  {
    title: "Grade",
    dataIndex: "grade",
    align: "center",
    render: (grade: number) => (!grade ? "N/A" : grade),
  },
  {
    title: "Actions",
    dataIndex: "grade",
    align: "center",
    render: (_: number, record: any) => (
      <Button type="primary" onClick={() => cb(record)}>
        Update grade
      </Button>
    ),
  },
];
