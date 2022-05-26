using Guestbook.Business.Guestbooks.Commands;
using Guestbook.Data;
using MediatR;
using Microsoft.EntityFrameworkCore;

using DataModels = Guestbook.Data.Models;

namespace Guestbook.Business.Guestbooks.Handlers;

public class GetByIdQueryHandler: IRequestHandler<GetByIdQuery, DataModels.Guestbook>
{
    private GuestbookContext _context;

    public GetByIdQueryHandler(GuestbookContext context)
    {
        _context = context;
    }

    public Task<DataModels.Guestbook> Handle(GetByIdQuery request, CancellationToken cancellationToken)
    {
        return _context.Guestbooks.FirstOrDefaultAsync(x => x.Id == request.GuestbookId, cancellationToken); 
    }
}