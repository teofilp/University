using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Business.DTOs;
using Business.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ReportsController : ControllerBase
    {
        private ReportsService _service;

        public ReportsController(ReportsService service)
        {
            _service = service;
        }

        [HttpGet("students")]
        public List<StudentReport> GetStudentsReport()
        {
            return _service.GetStudentsReport();
        }
    }
}