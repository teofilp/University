namespace Infrastructure.Models;

public class JWTSettings
{
    public string TokenKey { get; set; }
    public int ExpiresInHours { get; set; }
}