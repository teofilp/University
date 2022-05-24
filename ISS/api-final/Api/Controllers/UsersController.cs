using System.Collections.Generic;
using System.Linq;
using Api.Requests;
using Data;
using Data.models;
using Infrastructure.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Business.Services;
using Business.DTOs;
using Data.Models;

namespace Api.Controllers {
    [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class UsersController : ControllerBase
    {
        private UMSDatabaseContext _context;
        private JWTService _jwtService;
        private UserService _userService;

        public UsersController(UMSDatabaseContext context, JWTService jwtService, UserService userService)
        {
            _context = context;
            _jwtService = jwtService;
            _userService = userService;
        }
        
        [HttpGet()]
        [AllowAnonymous]
        public List<User> GetUsers()
        {
            return _context.Users.ToList();
        }

        [AllowAnonymous]
        [HttpPost()]
        public int AddUser(AddUserRequest request)
        {
            var user = new User
            {
                Username = request.Username,
                Password = BCrypt.Net.BCrypt.HashPassword(request.Password),
                Role = request.Role,
                UserProfile = new UserProfile {
                    Age = request.UserProfile.Age,
                    Email = request.UserProfile.Email,
                    Fullname = request.UserProfile.Fullname,
                    ProfileImageUrl = request.UserProfile.ProfileImageUrl
                },
                Student = new Student
                {
                    Specialization = _context.Specializations.First(),
                    Group = _context.Groups.First()
                },
                AdminStaff = new(),
                Teacher = new()
            };
            
            _context.Users.Add(user);
            _context.SaveChanges();

            return user.Id;
        }
        
        [AllowAnonymous]
        [HttpPost("login")]
        public LoginResponse Authenticate(LoginRequest request)
        {
            return _userService.Login(request.Username,request.Password);
        }
    }
}