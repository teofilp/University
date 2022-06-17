using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Data;
using Data.Entities;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApi.Requests;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProjectsController : ControllerBase
    {
        private Context _context;

        public ProjectsController(Context context)
        {
            _context = context;
        }

        [HttpGet]
        public List<Project> GetAll()
        {
            return _context.Projects
                .ToList();
        }

        [HttpPost]
        public void Add(AddProjectRequest project)
        {
            _context.Projects.Add(new Project()
            {
                Name = project.Name,
                Description = project.Description,
                ManagerId = project.ManagerId,
                Members = project.Members
            });
            _context.SaveChanges();
        }

        [HttpGet("getByUsername/{username}")]
        public List<Project> GetByUsername(string username)
        {
            return _context.Projects
                .Where(x => x.Members.Contains(username))
                .ToList();
        }

        [HttpPost("assign")]
        public bool AssignDeveloper(AssignDeveloperRequest request)
        {
            var developer = _context.SoftwareDevelopers.FirstOrDefault(x => x.Name.Equals(request.Username));

            if (developer is null)
            {
                return false;
            }
            
            foreach (var project in request.Projects)
            {
                var existingProject = _context.Projects.FirstOrDefault(x => x.Name.Equals(project));

                if (existingProject is null)
                {
                    _context.Projects.Add(new Project()
                    {
                        Name = project,
                        Description = "",
                        Members = developer.Name,
                        ManagerId = developer.Id
                    });
                }
                else
                {
                    existingProject.Members = string.IsNullOrEmpty(existingProject.Members)
                        ? developer.Name
                        : $"{existingProject.Members}, ${developer.Name}";
                }
            }

            _context.SaveChanges();
            
            return true;
        }
    }
}
