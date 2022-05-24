using Business.DTOs;
using Data;
using Microsoft.EntityFrameworkCore;

namespace Business.Services;

public class GradesService
{
    private UMSDatabaseContext _context;

    public GradesService(UMSDatabaseContext context)
    {
        _context = context;
    }

    public List<TeacherCourseGrades> GetTeacherCourseGrades(int userId)
    {
        return _context.Teachers.Where(x => x.UserId == userId)
            .Include(x => x.Courses)
            .ThenInclude(c => c.Enrolments)
            .SelectMany(t => t.Courses)
            .Select(c => new TeacherCourseGrades
            {
                CourseName = c.DisciplineName,
                TeacherGrades = c.Enrolments.Select(e => new TeacherGrade
                {
                    EnrolmentId = e.Id,
                    StudentName = e.StudentYear.Student.User.UserProfile.Fullname,
                    Grade = e.Grade
                }).ToList()
            }).ToList();
    }

    public void UpdateGrade(int enrolmentId, float grade)
    {
        if (grade is < 1 or > 10)
        {
            throw new Exception("invalid grade");
        }
        
        var enrolment = _context.CoursesEnrolments.FirstOrDefault(x => x.Id == enrolmentId);

        if (enrolment is null)
        {
            throw new Exception("Enrolment was not found");
        }

        enrolment.Grade = grade;
        
        _context.SaveChanges();
    }

    public List<StudentGrade> GetStudentGrades(int userId)
    {
        return _context.Students.Where(x => x.UserId == userId)
            .SelectMany(x => x.StudentUniversityYears)
            .SelectMany(x => x.Enrolments)
            .Select(x => new StudentGrade
            {
                CourseName = x.Course.DisciplineName,
                Grade = x.Grade,
                Semester = x.Course.Semester.SemesterDetails,
                Year = x.Course.Semester.UniversityYear.Year
            })
            .OrderByDescending(x => x.Year)
            .ThenByDescending(x => x.Semester)
            .ToList();
    }
}