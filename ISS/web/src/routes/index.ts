import { Route } from "../enums/Route";
import { Route as RouteConfig } from "../models/Route";
import { Login } from "../screens/login";
import { Dashboard } from "../screens/dashboard";
import { GradesContent } from "../screens/student/grades";
import { CurriculumContent } from "../screens/student/curriculum";
import { Courses } from "../screens/teacher/courses";
import { OptionalCoursesContent } from "../screens/chief/optional-courses";
import { Role } from "../enums/Role";
import { TeacherGrades } from "../screens/teacher/grades";
import { Home } from "../screens/home";
import { Contracts } from "../screens/student/contracts/Contracts";
import { Courses as ChiefCourses } from "../screens/chief/courses/Courses";
import { Teachers } from "../screens/chief/teachers/Teachers";
import { StudentsReports } from "../screens/admin/students/Students";

export const routes: RouteConfig[] = [
  {
    path: Route.Login,
    component: Login,
  },
  {
    path: Route.Home,
    component: Dashboard,
    isProtected: true,
  },
  {
    path: Route.Grades,
    component: GradesContent,
    isProtected: true,
  },
  {
    path: Route.Curriculum,
    component: CurriculumContent,
    isProtected: true,
    authorizedRoles: [Role.Student],
  },
  {
    path: Route.OptionalCourses,
    component: OptionalCoursesContent,
    isProtected: true,
    authorizedRoles: [Role.DepartmentChief],
  },
  {
    path: Route.Courses,
    component: Courses,
    isProtected: true,
    authorizedRoles: [Role.Teacher, Role.DepartmentChief],
  },
  {
    path: Route.TeacherGrades,
    component: TeacherGrades,
    isProtected: true,
    authorizedRoles: [Role.Teacher, Role.DepartmentChief],
  },
  {
    path: Route.Contracts,
    component: Contracts,
    isProtected: true,
    authorizedRoles: [Role.Student],
  },
  {
    path: Route.ChiefCourses,
    component: ChiefCourses,
    isProtected: true,
    authorizedRoles: [Role.DepartmentChief, Role.Admin],
  },
  {
    path: Route.Teachers,
    component: Teachers,
    isProtected: true,
    authorizedRoles: [Role.DepartmentChief, Role.Admin],
  },
  {
    path: Route.StudentsReport,
    component: StudentsReports,
    isProtected: true,
    authorizedRoles: [Role.Admin],
  },
];
