import { Button } from "antd";
import { ColumnsType } from "antd/lib/table";
import { OptionalCourse } from "../../../models/OptionalCourse";

export const getColumns: (
  cb: (course: OptionalCourse) => void
) => ColumnsType<any> = (cb) => [
  {
    title: "Name",
    dataIndex: "courseName",
    align: "center",
  },
  {
    title: "Professor",
    dataIndex: "teacher",
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
    title: "Actions",
    align: "center",
    dataIndex: "isApproved",
    render: (_, record) => (
      <Button
        type="primary"
        disabled={record.isApproved}
        onClick={() => cb(record)}
      >
        Approve
      </Button>
    ),
  },
];
