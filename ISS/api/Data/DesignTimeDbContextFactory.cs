using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;

namespace Data;

public class DesignTimeDbContextFactory : IDesignTimeDbContextFactory<UMSDatabaseContext>
{
    public UMSDatabaseContext CreateDbContext(string[] args)
    {
        var optionsBuilder = new DbContextOptionsBuilder<UMSDatabaseContext>();
        optionsBuilder.UseSqlServer(
            "Server=localhost;Database=UniversityManagement;User Id=sa;Password=Formula123#");

        return new UMSDatabaseContext(optionsBuilder.Options);
    }
}