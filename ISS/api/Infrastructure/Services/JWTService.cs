using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Data.models;
using Infrastructure.Models;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;

namespace Infrastructure.Services;

public class JWTService
{
    private JWTSettings _settings;
    
    public JWTService(IOptions<JWTSettings> options)
    {
        _settings = options.Value;
    }
    
    public string GenerateJWT(User user)
    {
        var claims = new[]
        {
            new Claim(ClaimTypes.Role, user.Role.ToString()),
            new Claim(ClaimTypes.NameIdentifier, user.Username),
        };
        
        var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_settings.TokenKey));
        var credentials = new SigningCredentials(key, SecurityAlgorithms.HmacSha512Signature);
        var tokenHandler = new JwtSecurityTokenHandler();
        
        var tokenDescriptor = new SecurityTokenDescriptor
        {
            Subject = new ClaimsIdentity(claims),
            Expires = DateTime.Now.AddHours(_settings.ExpiresInHours),
            SigningCredentials = credentials
        };

        var token = tokenHandler.CreateToken(tokenDescriptor);
            
        return tokenHandler.WriteToken(token);
    } 
}