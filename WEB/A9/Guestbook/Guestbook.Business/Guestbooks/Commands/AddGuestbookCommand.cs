using MediatR;

namespace Guestbook.Business.Guestbooks.Commands;

public class AddGuestbookCommand: IRequest<int>
{
    public string Author { get; set; }
    public string Title { get; set; }
    public string Comment { get; set; }
}