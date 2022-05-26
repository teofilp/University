using Guestbook.Business.Users.Commands;
using Guestbook.Data;
using MediatR;
using Microsoft.EntityFrameworkCore;

namespace Guestbook.Business.Users.Handlers;

public class LoginCommandHandler: IRequestHandler<LoginCommand, int>
{

    private GuestbookContext _context;

    public LoginCommandHandler(GuestbookContext context)
    {
        _context = context;
    }

    public async Task<int> Handle(LoginCommand request, CancellationToken cancellationToken)
    {
        var user = await _context.Users.FirstOrDefaultAsync(x => x.Username == request.Username, cancellationToken);

        if (user is null)
        {
            throw new Exception("User was not found");
        }

        if (!BCrypt.Net.BCrypt.Verify(request.Password, user.Password))
        {
            throw new Exception("Invalid password");
        }

        return user.Id;
    }
}