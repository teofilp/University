using Business.DTOs;
using Data;
using Data.Models;
using Microsoft.EntityFrameworkCore;

namespace Business.Services;

public class ReportsService
{
    private UMSDatabaseContext _context;

    public ReportsService(UMSDatabaseContext context)
    {
        _context = context;
    }

    public List<StudentReport> GetStudentsReport()
    {
        return _context.Students
            .Include(x => x.User)
                .ThenInclude(x => x.UserProfile)
            .Include(x => x.StudentUniversityYears)
                .ThenInclude(x => x.Enrolments)
            .Include(x => x.Group)
            .ToList()
            .Select(x => new StudentReport
            {
                Student = x.User.UserProfile.Fullname,
                AverageGrade = GetAverageGrade(x.StudentUniversityYears
                    .SelectMany(x => x.Enrolments)
                    .ToList()),
                Group = x.Group.Name
            })
            .OrderByDescending(x => x.AverageGrade)
            .ThenBy(x => x.Group)
            .ToList();
    }

    private float GetAverageGrade(List<CourseEnrolments> enrolments)
    {
        var gradesSum = enrolments.Sum(x => x.Grade);
        var gradesNumber = enrolments.Count;

        if (gradesNumber is 0) return 0;
        
        return gradesSum / gradesNumber;
    }
}