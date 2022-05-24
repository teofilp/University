using Business.DTOs;
using Data;
using Data.Models;
using Microsoft.EntityFrameworkCore;

namespace Business.Services;

public class TeachersService
{
    private UMSDatabaseContext _context;

    public TeachersService(UMSDatabaseContext context)
    {
        _context = context;
    }

    public List<ChiefTeacher> GetTeachers()
    {
        return _context.Teachers
            .Include(x => x.Courses)
                .ThenInclude(x => x.Enrolments)
            .Include(x => x.User)
                .ThenInclude(x => x.UserProfile)
            .ToList()
            .Select(x => new ChiefTeacher
            {
                Teacher = x.User.UserProfile.Fullname,
                StudentsNumber = x.Courses.SelectMany(x => x.Enrolments).Count(),
                DisciplinesNumber = x.Courses.Count,
                AverageGrade = GetAverageGrade(x.Courses)
            })
            .OrderByDescending(x => x.AverageGrade)
            .ToList();
    }

    private float GetAverageGrade(List<Courses> courses)
    {
        var enrolmentsNumber = Math.Max(courses.SelectMany(x => x.Enrolments).Count(), 1);
        var gradesSum = courses.SelectMany(x => x.Enrolments).Sum(x => x.Grade);

        return gradesSum / enrolmentsNumber;
    }
}