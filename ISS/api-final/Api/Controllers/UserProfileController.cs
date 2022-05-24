using Api.Requests;
using Business.DTOs;
using Business.Services;
using Data;
using Data.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Api.Controllers {
    [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class UserProfileController : ControllerBase {

        private UMSDatabaseContext _context;
        private UserProfileService _service;

        public UserProfileController(UserProfileService service, UMSDatabaseContext context) {
            _service = service;
            _context = context;
        }

        [AllowAnonymous]
        [HttpPost("add")]
        public int AddUserProfile(AddUserProfileRequest request) {
            return _service.AddUserProfile(request.UserId,request.Fullname,request.Age,request.ProfileImageUrl,request.Email);
        }

        [HttpGet()]
        [AllowAnonymous]
        public UserProfileDetails GetUserDetails(int userId) {
            return _service.GetUserDetails(userId);
        }

        [HttpPost("update")]
        [AllowAnonymous]
        public bool UpdateUserProfile(UpdateUserProfileRequest Request) {
            _service.UpdateUserProfile(Request.UserId,Request.Fullname,Request.Age,Request.ProfileImageUrl);
            return true;
        }
    }
}
