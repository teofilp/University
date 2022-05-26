using MediatR;

namespace Guestbook.Business.Users.Commands;

public class LoginCommand: IRequest<int>
{
    public string Username { get; set; }
    public string Password { get; set; }
}