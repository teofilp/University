using MediatR;

namespace Guestbook.Business.Guestbooks.Commands;

public class UpdateGuestbookCommand: IRequest<int>
{
    public int Id { get; set; }
    public string Title { get; set; }
    public string Author { get; set; }
    public string Comment { get; set; }
}