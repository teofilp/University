using Guestbook.Business.Guestbooks.Commands;
using Guestbook.Data;
using MediatR;

namespace Guestbook.Business.Guestbooks.Handlers;

public class UpdateGuestbookCommandHandler: IRequestHandler<UpdateGuestbookCommand, int>
{
    private GuestbookContext _context;

    public UpdateGuestbookCommandHandler(GuestbookContext context)
    {
        _context = context;
    }

    public Task<int> Handle(UpdateGuestbookCommand request, CancellationToken cancellationToken)
    {
        var guestbook = _context.Guestbooks.FirstOrDefault(x => x.Id == request.Id);

        if (guestbook is null)
        {
            throw new Exception("Guestbook was not found");
        }

        guestbook.Author = request.Author;
        guestbook.Comment = request.Comment;
        guestbook.Title = request.Title;
        guestbook.Date = DateTime.Now;

        return _context.SaveChangesAsync(cancellationToken);
    }
}