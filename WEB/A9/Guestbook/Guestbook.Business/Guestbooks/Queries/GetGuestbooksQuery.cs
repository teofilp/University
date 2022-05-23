using Guestbook.Business.Models;
using MediatR;
using Models = Guestbook.Data.Models;

namespace Guestbook.Business.Guestbooks.Queries;

public class GetGuestbooksQuery: IRequest<GetGuestbooksResponse>
{
    public string? SearchKey { get; set; }
    public int CurrentPage { get; set; }
}