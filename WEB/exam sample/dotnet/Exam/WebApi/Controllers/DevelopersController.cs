using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Data;
using Data.Entities;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DevelopersController : ControllerBase
    {
        private Context _context;

        public DevelopersController(Context context)
        {
            _context = context;
        }

        [HttpPost]
        public void AddDeveloper(SoftwareDeveloper developer)
        {
            _context.SoftwareDevelopers.Add(developer);
            _context.SaveChanges();
        }

        [HttpGet]
        public List<SoftwareDeveloper> GetAll()
        {
            return _context.SoftwareDevelopers.ToList();
        }

        [HttpPost("register")]
        public bool Register([FromQuery] string username)
        {
            return _context.SoftwareDevelopers.FirstOrDefault(x => x.Name.Equals(username)) is not null;
        }
    }
}
