using Guestbook.Business.Users.Queries;
using Guestbook.Data;
using Guestbook.Data.Models;
using MediatR;
using Microsoft.EntityFrameworkCore;

namespace Guestbook.Business.Users.Handlers;

public class GetUsersQueryHandler: IRequestHandler<GetUsersQuery, List<User>>
{
    private GuestbookContext _context;

    public GetUsersQueryHandler(GuestbookContext context)
    {
        _context = context;
    }

    public Task<List<User>> Handle(GetUsersQuery request, CancellationToken cancellationToken)
    {
        return _context.Users.ToListAsync(cancellationToken);
    }
}