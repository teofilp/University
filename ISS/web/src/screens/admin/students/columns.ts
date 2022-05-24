import uniq from "lodash.uniq";
import { StudentReport } from "../../../models/StudentReport";

export const getColumns = (dataSource: StudentReport[]) => [
  {
    title: "Student name",
    dataIndex: "student",
    align: "center",
  },
  {
    title: "Group",
    dataIndex: "group",
    align: "center",
    filters: uniq(dataSource.map((x) => x.group)).map((x) => ({
      text: x,
      value: x,
    })),
    onFilter: (value: string, record: StudentReport) => record.group === value,
  },
  {
    title: "Average Grade",
    dataIndex: "averageGrade",
    align: "center",
    render: (value: number) => value.toFixed(2),
    sorter: (a: StudentReport, b: StudentReport) =>
      a.averageGrade - b.averageGrade,
  },
];
