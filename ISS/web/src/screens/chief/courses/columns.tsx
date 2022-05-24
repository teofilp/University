export const columns = [
  {
    title: "Course name",
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
    title: "Approved",
    dataIndex: "approved",
    render: (isApproved: boolean) => (isApproved ? "Yes" : "No"),
  },
  {
    title: "Optional",
    dataIndex: "isOptional",
    render: (isOptional: boolean) => (isOptional ? "Yes" : "No"),
  },
  {
    title: "Max Students Number",
    dataIndex: "maxStudentsNumber",
    render: (value: boolean) => (value ? value : "N/A"),
  },
  {
    title: "Semester",
    dataIndex: "semester",
  },
  {
    title: "Year",
    dataIndex: "year",
  },
];
