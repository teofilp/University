using Guestbook.Data.Models;
using Microsoft.EntityFrameworkCore;

namespace Guestbook.Data;

public class GuestbookContext: DbContext
{
    public GuestbookContext(DbContextOptions<GuestbookContext> options) : base(options) { }

    public DbSet<User> Users { get; set; }
    public DbSet<Models.Guestbook> Guestbooks { get; set; }
}