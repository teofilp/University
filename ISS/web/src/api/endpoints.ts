export const ApiEndpoints = {
  login: "/Users/login",
  userProfile: "/UserProfile",
  courses: {
    base: "/Courses",
    getSemestersAndSpecializations: "/Courses/semestersAndSpecializations",
    addOptionalCourse: "/Courses/addOptional",
    getCourses: "/Courses/getCourses",
    getOptionalCourses: "/Courses/getOptionalCourses",
    approveCourse: "/Courses/approveCourse",
  },
  contract: {
    base: "/Contract",
    getStudentContracts: "/Contract/getStudentContracts",
  },
  teachers: {
    base: "/Teachers",
  },
  grades: {
    getTeacherGrades: "/Grades/getTeacherGrades",
    getStudentGrades: "/Grades/getStudentGrades",
    updateGrade: "/Grades",
  },
  reports: {
    students: "/Reports/students",
  },
};
