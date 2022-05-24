using Data.Models;

namespace Data.models;

public class User
{
    public int Id { get; set; }
    
    public string Username { get; set; }

    public string Password { get; set; }

    public Roles Role { get; set; }

    public UserProfile UserProfile { get; set; }

    public Student Student { get; set; }

    public AdminStaff AdminStaff{ get; set;}

    public Teacher Teacher { get; set; }
}