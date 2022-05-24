using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Api.Requests;
using Business.DTOs;
using Business.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Api.Controllers
{
    // [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class GradesController : ControllerBase
    {
        private GradesService _service;

        public GradesController(GradesService service)
        {
            _service = service;
        }

        [HttpGet("getTeacherGrades/{userId}")]
        public List<TeacherCourseGrades> GetTeacherCourseGrades(int userId)
        {
            return _service.GetTeacherCourseGrades(userId);
        }

        [HttpPut()]
        public void UpdateGrade(UpdateGradeRequest request)
        {
            _service.UpdateGrade(request.EnrolmentId, request.Grade);
        }

        [HttpGet("getStudentGrades/{userId}")]
        public List<StudentGrade> GetStudentGrades(int userId)
        {
            return _service.GetStudentGrades(userId);
        }
    }
}