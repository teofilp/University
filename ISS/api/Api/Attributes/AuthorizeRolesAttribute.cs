using Data.Models;
using Microsoft.AspNetCore.Authorization;

namespace Api.Attributes;

public class AuthorizeRolesAttribute : AuthorizeAttribute
{
    public AuthorizeRolesAttribute(params Roles[] roles) : base()
    {
        Roles = string.Join(",", roles);
    }
}