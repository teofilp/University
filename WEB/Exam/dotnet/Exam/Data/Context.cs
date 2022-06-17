using Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data;

public class Context : DbContext
{
    public Context(DbContextOptions options) : base(options) { }

    public DbSet<User> Users { get; set; }
}