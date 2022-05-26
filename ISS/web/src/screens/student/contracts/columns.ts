export const columns = [
  {
    title: "Course Name",
    dataIndex: "courseName",
  },
  {
    title: "Teacher",
    dataIndex: "teacher",
  },
  {
    title: "Specialization",
    dataIndex: "specialization",
  },
  {
    title: "Semester",
    dataIndex: "semester",
  },
  {
    title: "Optional",
    dataIndex: "isOptional",
    render: (isOptional: boolean) => (isOptional ? "Yes" : "No"),
  },
];
