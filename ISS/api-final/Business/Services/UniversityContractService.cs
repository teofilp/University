using Business.DTOs;
using Data;
using Data.Models;
using Microsoft.EntityFrameworkCore;

namespace Business.Services;

public class UniversityContractService
{
    private UMSDatabaseContext _context;

    public UniversityContractService(UMSDatabaseContext context)
    {
        _context = context;
    }

    public List<AnnualContractDetails> GetContractDetails(int userId)
    {
        var createdContractYears = _context.Students.Where(x => x.UserId == userId)
            .Include(x => x.StudentUniversityYears)
                .ThenInclude(x => x.UniversityYear)
            .SelectMany(x => x.StudentUniversityYears)
            .Select(x => x.UniversityYear.Year)
            .ToList();

        if (createdContractYears.Count == 2)
        {
            return new();
        }
        
        return _context.Courses
            .Where(x => !x.OptionalFlag || x.Enrolments.Count < x.NumberOfStudents)
            .Where(x => !createdContractYears.Contains(x.Semester.UniversityYear.Year))
            .Include(c => c.Semester)
                .ThenInclude(s => s.UniversityYear)
            .Include(c => c.Specialization)
            .Include(c => c.Teacher)
                .ThenInclude(t => t.User)
                    .ThenInclude(u => u.UserProfile)
            .ToList()
            .GroupBy(x => x.Semester.UniversityYear.Year)
            .Select(y => new AnnualContractDetails
            {
                Year = y.Key,
                MandatoryDisciplines = GetContractDisciplineDetails(y, false),
                OptionalDisciplines = GetContractDisciplineDetails(y, true)
            }).ToList();
    }

    private List<ContractDisciplineDetails> GetContractDisciplineDetails(IGrouping<int, Courses> grouping , bool isOptional)
    {
        return grouping.Where(x => x.OptionalFlag == isOptional)
            .Select(c => new ContractDisciplineDetails
            {
                CourseId = c.Id,
                CourseName = c.DisciplineName,
                Teacher = c.Teacher.User.UserProfile.Fullname,
                Specialization = c.Specialization.Name,
                Semester = c.Semester.SemesterDetails,
                IsOptional = c.OptionalFlag
            })
            .ToList();
    }

    public List<StudentContract> GetStudentContracts(int userId)
    {
        return _context.StudentUniversityYears.Where(x => x.Student.UserId == userId)
            .Select(x => new StudentContract
            {
                Year = x.UniversityYear.Year,
                ContractDisciplineDetails = x.Enrolments.Select(e => new ContractDisciplineDetails
                {
                    CourseId = e.Id,
                    CourseName = e.Course.DisciplineName,
                    Teacher = e.Course.Teacher.User.UserProfile.Fullname,
                    Specialization = e.Course.Specialization.Name,
                    Semester = e.Course.Semester.SemesterDetails,
                    IsOptional = e.Course.OptionalFlag
                }).ToList()
            })
            .ToList();
    }

    public void CreateContract(AddAnnualContract contractDetails)
    {
        var universityYear = _context.UniversityYears.FirstOrDefault(x => x.Year == contractDetails.Year);
        var student = _context.Students.FirstOrDefault(x => x.UserId == contractDetails.UserId);
        
        if (universityYear is null || student is null)
        {
            throw new Exception("Cannot find university year or student");
        }

        var studentUniversityYear = new StudentUniversityYear
        {
            Student = student,
            UniversityYear = universityYear,
            SignatureDate = DateTime.Now,
            Enrolments = contractDetails.CourseIds.Select(x => new CourseEnrolments
            {
                CoursesID = x
            }).ToList()
        };

        _context.StudentUniversityYears.Add(studentUniversityYear);
        _context.SaveChanges();
    }
}