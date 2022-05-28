using Guestbook.Data;

namespace Guestbook.WebApi.Middleware;

public class AuthorizationMiddleware
{
    private readonly RequestDelegate _next;
    private GuestbookContext _context;

    public AuthorizationMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    public async Task InvokeAsync(HttpContext context, GuestbookContext dbContext)
    {
        if (context.Request.Method.Equals("OPTIONS"))
        {
            await _next(context);
            return;
        }
        _context = dbContext;

        var path = context.Request.Path.ToString();

        if (path.StartsWith("/api") && !path.Equals("/api/Users/login"))
        {
            var userId = context.Request.Headers["x-userId"].FirstOrDefault();
            
            if (userId is null || !UserExists(Convert.ToInt32(userId)))
            {
                context.Response.StatusCode = 401;
                return;
            }
        }
        await _next(context);
    }

    private bool UserExists(int userId)
    {
        return _context.Users.FirstOrDefault(x => x.Id == userId) is not null;
    }
}

public static class AuthorizationMiddlewareExtensions
{
    public static IApplicationBuilder UseCustomAuthorization(this IApplicationBuilder builder)
    {
        return builder.UseMiddleware<AuthorizationMiddleware>();
    }
}