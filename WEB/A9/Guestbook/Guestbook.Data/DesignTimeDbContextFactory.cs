using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;

namespace Guestbook.Data;

public class DesignTimeDbContextFactory: IDesignTimeDbContextFactory<GuestbookContext>
{
    public GuestbookContext CreateDbContext(string[] args)
    {
        var optionsBuilder = new DbContextOptionsBuilder<GuestbookContext>();
        optionsBuilder.UseSqlServer("Server=localhost;Database=Guestbook;User Id=sa;Password=Formula123#;");

        return new GuestbookContext(optionsBuilder.Options);
    }
}