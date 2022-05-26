using Guestbook.Business.Guestbooks.Commands;
using Guestbook.Data;
using MediatR;

namespace Guestbook.Business.Guestbooks.Handlers;

public class DeleteGuestbookCommandHandler: IRequestHandler<DeleteGuestbookCommand, int>
{
    private GuestbookContext _context;

    public DeleteGuestbookCommandHandler(GuestbookContext context)
    {
        _context = context;
    }

    public Task<int> Handle(DeleteGuestbookCommand request, CancellationToken cancellationToken)
    {
        var guestbook = _context.Guestbooks.FirstOrDefault(x => x.Id == request.GuestbookId);

        if (guestbook is null)
        {
            throw new Exception("Could not find guestbook");
        }

        _context.Guestbooks.Remove(guestbook);

        return _context.SaveChangesAsync(cancellationToken);
    }
}