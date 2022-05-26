using Guestbook.Business.Guestbooks.Commands;
using Guestbook.Data;
using MediatR;
using DataModels = Guestbook.Data.Models;

namespace Guestbook.Business.Guestbooks.Handlers;

public class AddGuestbookCommandHandler: IRequestHandler<AddGuestbookCommand, int>
{
    private GuestbookContext _context;

    public AddGuestbookCommandHandler(GuestbookContext context)
    {
        _context = context;
    }

    public Task<int> Handle(AddGuestbookCommand request, CancellationToken cancellationToken)
    {
        _context.Guestbooks.Add(new DataModels.Guestbook
        {
            Title = request.Title,
            Author = request.Author,
            Comment = request.Comment,
            Date = DateTime.Now
        });

        return _context.SaveChangesAsync(cancellationToken);
    }
}