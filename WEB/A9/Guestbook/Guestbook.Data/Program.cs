using Microsoft.EntityFrameworkCore;

namespace Guestbook.Data;

public class Program
{
    public static void Main(string[] args)
    {
        var context = new DesignTimeDbContextFactory().CreateDbContext(args);
        Console.WriteLine("Migrating Database...");
        context.Database.Migrate();
        Console.WriteLine("Migrated successfully!");
    }
}