using System.Collections.Generic;
using Api.Attributes;
using Business.DTOs;
using Business.Services;
using Data.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Api.Controllers
{
    [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class TeachersController : ControllerBase
    {
        private TeachersService _service;

        public TeachersController(TeachersService service)
        {
            _service = service;
        }

        [HttpGet]
        [AuthorizeRoles(Roles.DepartmentChief, Roles.Admin)]
        public List<ChiefTeacher> GetTeachers()
        {
            return _service.GetTeachers();
        }
    }
}