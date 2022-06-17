using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;

namespace Data;

public class DesignTimeContextFactory : IDesignTimeDbContextFactory<Context>
{
    public Context CreateDbContext(string[] args)
    {
        var builder = new DbContextOptionsBuilder<Context>()
            .UseSqlServer("Server=localhost;Initial Catalog=ExamWeb;User Id=sa; Password=Formula123#");;
        
        return new Context(builder.Options);
    }
}