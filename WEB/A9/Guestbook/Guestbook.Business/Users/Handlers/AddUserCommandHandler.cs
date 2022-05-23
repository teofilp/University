using Guestbook.Business.Users.Commands;
using Guestbook.Data;
using Guestbook.Data.Models;
using MediatR;

namespace Guestbook.Business.Users.Handlers;

public class AddUserCommandHandler: IRequestHandler<AddUserCommand, int>
{
    private GuestbookContext _context;

    public AddUserCommandHandler(GuestbookContext context)
    {
        _context = context;
    }

    public async Task<int> Handle(AddUserCommand request, CancellationToken cancellationToken)
    {
        var existingUser = _context.Users.FirstOrDefault(x => x.Username == request.Username);

        if (existingUser is not null)
        {
            return existingUser.Id;
        }

        var newUser = new User
        {
            Username = request.Username,
            Password = BCrypt.Net.BCrypt.HashPassword(request.Password),
            Role = request.Role
        };

        _context.Add(newUser);
        await _context.SaveChangesAsync(cancellationToken);

        return newUser.Id;
    }
}