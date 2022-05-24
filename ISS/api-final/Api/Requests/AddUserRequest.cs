using Data.Models;

namespace Api.Requests;

public class AddUserRequest {
    public string Username { get; set; }
    public string Password { get; set; }
    public Roles Role { get; set; }
    public AddUserProfileRequest UserProfile { get; set;}
}