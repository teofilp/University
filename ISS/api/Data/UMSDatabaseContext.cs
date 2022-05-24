using Data.models;
using Data.Models;
using Microsoft.EntityFrameworkCore;

namespace Data;

public class UMSDatabaseContext : DbContext
{
    public UMSDatabaseContext(DbContextOptions<UMSDatabaseContext> options) : base(options) { }

    public DbSet<User> Users { get; set; }

    public DbSet<UserProfile> UserProfiles { get; set; }

    public DbSet<Group> Groups { get; set; }

    public DbSet<Student> Students { get; set;}

    public DbSet<AdminStaff> AdminStaffs { get; set; }

    public DbSet<Teacher> Teachers { get; set; }

    public DbSet<StudentUniversityYear> StudentUniversityYears { get; set; }

    public DbSet<UniversityYear> UniversityYears { get; set; }

    public DbSet<Semester> Semesters { get; set; }
    public DbSet<Specialization> Specializations { get; set; }

    public DbSet<Courses> Courses { get; set; }

    public DbSet<CourseEnrolments> CoursesEnrolments { get; set; }  

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<CourseEnrolments>().HasOne<Courses>(CE => CE.Course).WithMany(c => c.Enrolments).OnDelete(DeleteBehavior.Restrict);
        modelBuilder.Entity<CourseEnrolments>().HasOne(CE => CE.StudentYear).WithMany(c => c.Enrolments).OnDelete(DeleteBehavior.Restrict);

        //  modelBuilder.Entity<CourseEnrolment>.HasOne<Course>(ce => ce.Course).WithMany(c => c.CourseEnrolments).OnDelete(DeleteBehavior.Restrict);
    }
}
