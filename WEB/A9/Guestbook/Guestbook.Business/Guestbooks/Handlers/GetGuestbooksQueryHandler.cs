using Guestbook.Business.Guestbooks.Queries;
using Guestbook.Business.Models;
using Guestbook.Data;
using MediatR;
using Microsoft.EntityFrameworkCore;
using DataModels = Guestbook.Data.Models;

namespace Guestbook.Business.Guestbooks.Handlers;

public class GetGuestbooksQueryHandler: IRequestHandler<GetGuestbooksQuery, GetGuestbooksResponse>
{
    private GuestbookContext _context;
    private const int PageSize = 4;

    public GetGuestbooksQueryHandler(GuestbookContext context)
    {
        _context = context;
    }

    public async Task<GetGuestbooksResponse> Handle(GetGuestbooksQuery request, CancellationToken cancellationToken)
    {
        var currentPage = Math.Max(request.CurrentPage, 1);
        
        var query = _context.Guestbooks
            .Where(x => x.Author.Contains(request.SearchKey) || x.Title.Contains((request.SearchKey)));
        
        var items = await query
            .Skip(PageSize * (currentPage - 1))
            .Take(PageSize)
            .ToListAsync(cancellationToken);
        
        var itemsCount = await query.CountAsync(cancellationToken);
        var pages = itemsCount / PageSize + (itemsCount % PageSize > 0 ? 1 : 0);

        return new GetGuestbooksResponse
        {
            Items = items,
            Pages = pages
        };
    }
}