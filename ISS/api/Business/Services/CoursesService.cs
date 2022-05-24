using Business.DTOs;
using Data;
using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace Business.Services;
public class CoursesService {
    private UMSDatabaseContext _context;
    private const int MaxOptionalCoursesCount = 2;

    public CoursesService(UMSDatabaseContext context) {
        _context = context;
    }

    public SemestersAndSpecializationsResponse GetSemestersAndSpecializations()
    {
        SemestersAndSpecializationsResponse semestersAndSpecializations = new SemestersAndSpecializationsResponse
        {
            Specializations = _context.Specializations.Select(x => new SpecializationDTO
                {
                    Id = x.Id,
                    Name = x.Name
                }).ToList(),
            Semesters = _context.Semesters.Select(x => new SemesterDTO
                {
                    Id = x.Id, 
                    SemesterDetails = x.SemesterDetails, 
                    UniversityYear = _context.UniversityYears.FirstOrDefault(y => y.Id == x.UniversityYearID).Year
                }).ToList()
        };
        
        return semestersAndSpecializations;
    }

    public void UpdateCourse(int id, int maxStudents)
    {
        var course = _context.Courses.FirstOrDefault(x => x.Id ==  id);

        if (course is null)
        {
            throw new Exception("Course was not found!");
        }
        
        course.NumberOfStudents = maxStudents;

        _context.SaveChanges();
    }

    public void AddOptionalCourse(int userId, string name, int semesterId, int specializationId)
    {
        var teacherId = _context.Teachers.Where(o => o.UserId == userId).Select(o => o.Id).FirstOrDefault();
        var optionalCoursesNumber = _context.Courses
            .Count(o => o.TeacherId == teacherId && o.OptionalFlag == true);

        if (optionalCoursesNumber == MaxOptionalCoursesCount)
        {
            throw new Exception("Optional courses number exceeded!");
        }

        var optionalCourse = new Courses
        {
            DisciplineName = name,
            TeacherId = teacherId,
            SemesterId = semesterId,
            OptionalFlag = true,
            NumberOfStudents = 0,
            SpecializationID = specializationId
        };
        
        _context.Courses.Add(optionalCourse);
        _context.SaveChanges();
    }

    public List<TeacherCourse> GetCoursesForTeacher(int userId)
    {
        return _context.Teachers.Where(x => x.UserId == userId)
            .SelectMany(t => t.Courses)
            .Select(c => new TeacherCourse
            {
                CourseName = c.DisciplineName,
                Specialization = c.Specialization.Name,
                IsOptional = c.OptionalFlag,
                Approved = !c.OptionalFlag || c.NumberOfStudents > 0,
                MaxStudentsNumber = c.NumberOfStudents,
                Semester = c.Semester.SemesterDetails,
                Year = c.Semester.UniversityYear.Year,
                Teacher = c.Teacher.User.UserProfile.Fullname
            })
            .ToList();
    }

    public List<OptionalCourse> GetOptionalCourses()
    {
        return _context.Courses.Where(x => x.OptionalFlag)
            .Select(c => new OptionalCourse
            {
                CourseId = c.Id,
                CourseName = c.DisciplineName,
                Specialization = c.Specialization.Name,
                Semester = c.Semester.SemesterDetails,
                Year = c.Semester.UniversityYear.Year,
                Teacher = c.Teacher.User.UserProfile.Fullname,
                IsApproved = c.NumberOfStudents > 0
            })
            .OrderBy(x => x.IsApproved)
            .ToList();
    }

    public List<TeacherCourse> GetTeachersCourses()
    {
        return _context.Courses.Select(c => new TeacherCourse
        {
            CourseName = c.DisciplineName,
            Specialization = c.Specialization.Name,
            IsOptional = c.OptionalFlag,
            Approved = !c.OptionalFlag || c.NumberOfStudents > 0,
            MaxStudentsNumber = c.NumberOfStudents,
            Semester = c.Semester.SemesterDetails,
            Year = c.Semester.UniversityYear.Year,
            Teacher = c.Teacher.User.UserProfile.Fullname
        }).ToList();
    }
}
