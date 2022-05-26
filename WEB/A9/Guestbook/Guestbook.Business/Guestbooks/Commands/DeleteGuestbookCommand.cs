using MediatR;

namespace Guestbook.Business.Guestbooks.Commands;

public class DeleteGuestbookCommand: IRequest<int>
{
    public int GuestbookId { get; set; }
}