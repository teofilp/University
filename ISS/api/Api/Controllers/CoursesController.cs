using Business.DTOs;
using Business.Services;
using Data;
using Data.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;
using Api.Attributes;
using Api.Requests;

namespace Api.Controllers;

[Authorize]
[Route("api/[controller]")]
[ApiController]
public class CoursesController : ControllerBase
{
    private UMSDatabaseContext _context;
    private CoursesService _service;

    public CoursesController(UMSDatabaseContext context, CoursesService service)
    {
        _context = context;
        _service = service;
    }

    [HttpGet("courses")]
    [AuthorizeRoles(Roles.DepartmentChief)]
    [AllowAnonymous]
    public List<Courses> GetCourses()
    {
        return _context.Courses.ToList();
    }

    [HttpGet("semestersAndSpecializations")]
    [AllowAnonymous]
    public SemestersAndSpecializationsResponse GetSemestersAndSpecializations()
    {
        return _service.GetSemestersAndSpecializations();
    }

    [AuthorizeRoles(Roles.Teacher)]
    [HttpPost("addOptional")]
    public void AddOptionalCourse(AddOptionalCourseRequest request)
    {
        _service.AddOptionalCourse(request.UserId, request.Name, request.SemesterId, request.SpecializationId);
    }

    [AuthorizeRoles(Roles.DepartmentChief)]
    [HttpPost("approveCourse")]
    public void ApproveCourse(ApproveOptionalCourseRequest request)
    {
        _service.UpdateCourse(request.CourseId, request.MaxStudents);
    }

    [AuthorizeRoles(Roles.Teacher, Roles.DepartmentChief)]
    [HttpGet("getCourses/{userId}")]
    public List<TeacherCourse> GetCoursesForTeacher(int userId)
    {
        return _service.GetCoursesForTeacher(userId);
    }

    [AuthorizeRoles(Roles.DepartmentChief)]
    [HttpGet("getOptionalCourses")]
    public List<OptionalCourse> GetOptionalCourses()
    {
        return _service.GetOptionalCourses();
    }

    [AuthorizeRoles(Roles.DepartmentChief, Roles.Admin)]
    [HttpGet]
    public List<TeacherCourse> GetTeacherCourses()
    {
        return _service.GetTeachersCourses();
    }
}
