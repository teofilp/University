using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BCrypt.Net;

using Data;
using Data.models;
using Infrastructure.Services;
using Business;
using Business.DTOs;

namespace Business.Services;
public class UserService {
    private UMSDatabaseContext _context;
    private JWTService _jWTService;

    public UserService(UMSDatabaseContext context, JWTService jWTService) {
        _context = context;
        _jWTService = jWTService;
    }

    public LoginResponse Login(string username, string password) {
        User user = _context.Users.FirstOrDefault(x => x.Username.Equals(username));

        if (user == null) {
            throw new Exception("Username doesn't exist!");
        }

        if (!BCrypt.Net.BCrypt.Verify(password, user.Password))
            throw new Exception("Incorrect password!");


        return new LoginResponse {
            Token = _jWTService.GenerateJWT(user),
            UserId = user.Id,
            Role = user.Role
        };
    }
}
