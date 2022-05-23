namespace Guestbook.Business.Models;

using Models = Guestbook.Data.Models;

public class GetGuestbooksResponse
{
    public List<Models.Guestbook> Items { get; set; }
    public int Pages { get; set; }
}