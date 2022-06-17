using Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data;

public class Context : DbContext
{
    public Context(DbContextOptions options) : base(options) { }

    public DbSet<SoftwareDeveloper> SoftwareDevelopers { get; set; }
    public DbSet<Project> Projects { get; set; }
}