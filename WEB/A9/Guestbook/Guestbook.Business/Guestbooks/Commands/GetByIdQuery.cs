using MediatR;
using DataModels = Guestbook.Data.Models;

namespace Guestbook.Business.Guestbooks.Commands;

public class GetByIdQuery: IRequest<DataModels.Guestbook>
{
    public int GuestbookId { get; set; }
}