import { Button } from "antd";
import { useContext, useEffect, useState, useMemo } from "react";
import { IoMdAddCircleOutline } from "react-icons/io";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";
import { UserContext } from "../../../context/UserContext";
import { TeacherCourse } from "../../../models/TeacherCourses";
import { AddOptionalCourseModal } from "./AddOptionalCourseModal";
import { CoursesTable } from "./CoursesTable";

import "./main.css";

export const Courses = () => {
  const [isOpen, setOpen] = useState(false);
  const [isLoading, setLoading] = useState(true);
  const [courses, setCourses] = useState<TeacherCourse[]>([]);
  const {
    user: { id },
  } = useContext(UserContext);

  const fetchCourses = () => {
    setLoading(true);
    api
      .get<TeacherCourse[]>(`${ApiEndpoints.courses.getCourses}/${id}`)
      .then(({ data }) => setCourses(data))
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    fetchCourses();
  }, []);

  const optionalCoursesCount = useMemo(
    () => courses.filter((x) => x.isOptional).length,
    [courses]
  );

  return (
    <div id="courses-contaner">
      <h2 id="page-title">Your courses</h2>
      <Button
        type="primary"
        className="add-button"
        size="large"
        disabled={isLoading || optionalCoursesCount === 2}
        icon={<IoMdAddCircleOutline size={24} />}
        onClick={() => setOpen(true)}
      >
        Add optional course
      </Button>
      <CoursesTable isLoading={isLoading} courses={courses} />
      <AddOptionalCourseModal
        visible={isOpen}
        onOk={() => {
          setOpen(false);
          fetchCourses();
        }}
        onCancel={() => setOpen(false)}
      />
    </div>
  );
};
