import { Button } from "antd";
import { ColumnsType } from "antd/lib/table";
import { ContractDiscipline } from "../../../models/ContractDiscipline";

export const baseColumns: ColumnsType<any> = [
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
    title: "Teacher",
    dataIndex: "teacher",
  },
  {
    title: "Semester",
    dataIndex: "semester",
    align: "center",
  },
];

export const getSelectedDisciplinesColumns = (
  cb: (record: ContractDiscipline) => void
) => [
  ...baseColumns,
  {
    title: "Actions",
    dataIndex: "isOptional",
    render: (_: any, record: ContractDiscipline) => (
      <Button disabled={!record.isOptional} onClick={() => cb(record)}>
        Remove
      </Button>
    ),
  },
];

export const getOptionalDisciplinesColumns = (
  cb: (record: ContractDiscipline) => void
) => [
  ...baseColumns,
  {
    title: "Actions",
    dataIndex: "isOptional",
    render: (_: any, record: ContractDiscipline) => (
      <Button onClick={() => cb(record)}>Add</Button>
    ),
  },
];
