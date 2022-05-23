using MediatR;

namespace Guestbook.Business.Users.Commands;

public record AddUserCommand: IRequest<int>
{
    public string Username { get; set; }
    public string Password { get; set; }
    public string Role { get; set; }
}