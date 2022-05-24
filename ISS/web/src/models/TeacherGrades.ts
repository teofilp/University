export interface TeacherGrades {
  courseName: string;
  teacherGrades: {
    enrolmentId: number;
    studentName: string;
    grade: number;
  }[];
}
