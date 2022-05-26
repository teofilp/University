using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Api.Attributes;
using Api.Requests;
using Business.DTOs;
using Business.Services;
using Data.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Api.Controllers
{
    [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class ContractController : ControllerBase
    {
        private UniversityContractService _service;

        public ContractController(UniversityContractService service)
        {
            _service = service;
        }

        [HttpGet("{userId}")]
        [AuthorizeRoles(Roles.Student)]
        public List<AnnualContractDetails> GetContractSetupDetails(int userId)
        {
            return _service.GetContractDetails(userId);
        }

        [HttpPost()]
        [AuthorizeRoles(Roles.Student)]
        public void AddAnnualContract(AddAnnualContractRequest contractDetails)
        {
            _service.CreateContract(new AddAnnualContract
            {
                UserId = contractDetails.UserId,
                Year = contractDetails.Year,
                CourseIds = contractDetails.CourseIds
            });
        }

        [AuthorizeRoles(Roles.Student)]
        [HttpGet("getStudentContracts/{userId}")]
        public List<StudentContract> GetStudentContracts(int userId)
        {
            return _service.GetStudentContracts(userId);
        }
    }
}