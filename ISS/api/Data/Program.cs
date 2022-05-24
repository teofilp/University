using Data.Builders;
using Data.models;
using Data.Models;
using Microsoft.EntityFrameworkCore;

namespace Data
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var dbContext = new DesignTimeDbContextFactory().CreateDbContext(args);

            Console.WriteLine("Starting database migration");

            dbContext.Database.Migrate();

            SeedGroup(dbContext);
            SeedSpecialization(dbContext);
            SeedUsers(dbContext);
            seedUniversityYear(dbContext);
            seedSemester(dbContext);
            seedStudentUniversityYear(dbContext);
            seedCourses(dbContext);
            seedCourseEnrolments(dbContext);
        }

        public static void seedCourseEnrolments(UMSDatabaseContext context)
        {

            CourseEnrolmentBuilder builder = new CourseEnrolmentBuilder();

            builder.setStudentUniversityYearId(1);
            builder.setCoursesId(6);
            var enrolment = builder.getResult();
            if(context.CoursesEnrolments.FirstOrDefault(x => x.Id.Equals(1)) is null)
            {
                context.CoursesEnrolments.Add(enrolment);
            }

            builder.setStudentUniversityYearId(1);
            builder.setCoursesId(5);
            enrolment = builder.getResult();
            if (context.CoursesEnrolments.FirstOrDefault(x => x.Id.Equals(2)) is null)
            {
                context.CoursesEnrolments.Add(enrolment);
            }

            builder.setStudentUniversityYearId(2);
            builder.setCoursesId(1);
            enrolment = builder.getResult();
            if (context.CoursesEnrolments.FirstOrDefault(x => x.Id.Equals(3)) is null)
            {
                context.CoursesEnrolments.Add(enrolment);
            }

            builder.setStudentUniversityYearId(2);
            builder.setCoursesId(2);
            enrolment = builder.getResult();
            if (context.CoursesEnrolments.FirstOrDefault(x => x.Id.Equals(4)) is null)
            {
                context.CoursesEnrolments.Add(enrolment);
            }

            builder.setStudentUniversityYearId(2);
            builder.setCoursesId(3);
            enrolment = builder.getResult();
            if (context.CoursesEnrolments.FirstOrDefault(x => x.Id.Equals(5)) is null)
            {
                context.CoursesEnrolments.Add(enrolment);
            }

            builder.setStudentUniversityYearId(3);
            builder.setCoursesId(5);
            enrolment = builder.getResult();
            if (context.CoursesEnrolments.FirstOrDefault(x => x.Id.Equals(6)) is null)
            {
                context.CoursesEnrolments.Add(enrolment);
            }

            builder.setStudentUniversityYearId(3);
            builder.setCoursesId(6);
            enrolment = builder.getResult();
            if (context.CoursesEnrolments.FirstOrDefault(x => x.Id.Equals(7)) is null)
            {
                context.CoursesEnrolments.Add(enrolment);
            }

            context.SaveChanges();
        }

        public static void seedCourses(UMSDatabaseContext context)
        {

            CourseBuilder builder = new CourseBuilder();

            builder.setDisciplineName("Algebră 2");
            builder.setTeacherId(1);
            builder.setSemesterId(3);
            builder.setOptionalFlag(false);
            builder.setNumberOfStudents(200);
            builder.setSpecializationId(2);
            var course = builder.getResult();
            if (context.Courses.FirstOrDefault(x => x.DisciplineName == course.DisciplineName) is null)
            {
                context.Courses.Add(course);
            }
            
            builder.setDisciplineName("Analiză matematică 2");
            builder.setTeacherId(1);
            builder.setSemesterId(3);
            builder.setOptionalFlag(false);
            builder.setNumberOfStudents(200);
            builder.setSpecializationId(2);
            course = builder.getResult();
            if (context.Courses.FirstOrDefault(x => x.DisciplineName == course.DisciplineName) is null)
            {
                context.Courses.Add(course);
            }


            builder.setDisciplineName("Sisteme de operare 3");
            builder.setTeacherId(1);
            builder.setSemesterId(4);
            builder.setOptionalFlag(false);
            builder.setNumberOfStudents(200);
            builder.setSpecializationId(2);
            course = builder.getResult();
            if (context.Courses.FirstOrDefault(x => x.DisciplineName == course.DisciplineName) is null)
            {
                context.Courses.Add(course);
            }

            builder.setDisciplineName("OOP 1");
            builder.setTeacherId(1);
            builder.setSemesterId(4);
            builder.setOptionalFlag(false);
            builder.setNumberOfStudents(200);
            builder.setSpecializationId(2);
            course = builder.getResult();
            if (context.Courses.FirstOrDefault(x => x.DisciplineName == course.DisciplineName) is null)
            {
                context.Courses.Add(course);
            }


            builder.setDisciplineName("Rețele de calculatoare 123");
            builder.setTeacherId(2);
            builder.setSemesterId(3);
            builder.setOptionalFlag(false);
            builder.setNumberOfStudents(150);
            builder.setSpecializationId(1);
            course = builder.getResult();
            if (context.Courses.FirstOrDefault(x => x.DisciplineName == course.DisciplineName) is null)
            {
                context.Courses.Add(course);
            }


            builder.setDisciplineName("Baze de date 123");
            builder.setTeacherId(2);
            builder.setSemesterId(4);
            builder.setOptionalFlag(false);
            builder.setNumberOfStudents(200);
            builder.setSpecializationId(1);
            course = builder.getResult();
            if (context.Courses.FirstOrDefault(x => x.DisciplineName == course.DisciplineName) is null)
            {
                context.Courses.Add(course);
            }

            context.SaveChanges();  
        }

        public static void seedSemester(UMSDatabaseContext context)
        {

            SemesterBuilder builder = new SemesterBuilder();
            builder.setSemesterDetails(1);
            builder.setUniversityYearId(1);
            var sem = builder.getResult();
            if(context.Semesters.FirstOrDefault(x=> x.UniversityYearID.Equals(sem.UniversityYearID) && x.SemesterDetails.Equals(sem.SemesterDetails)) is null)
            {
                context.Semesters.Add(sem);
            }

            builder.setSemesterDetails(1);
            builder.setUniversityYearId(2);
            sem = builder.getResult();
            if (context.Semesters.FirstOrDefault(x => x.UniversityYearID.Equals(sem.UniversityYearID) && x.SemesterDetails.Equals(sem.SemesterDetails)) is null)
            {
                context.Semesters.Add(sem);
            }

            builder.setSemesterDetails(2);
            builder.setUniversityYearId(1);
            sem = builder.getResult();
            if (context.Semesters.FirstOrDefault(x => x.UniversityYearID.Equals(sem.UniversityYearID) && x.SemesterDetails.Equals(sem.SemesterDetails)) is null)
            {
                context.Semesters.Add(sem);
            }

            builder.setSemesterDetails(2);
            builder.setUniversityYearId(2);
            sem = builder.getResult();
            if (context.Semesters.FirstOrDefault(x => x.UniversityYearID.Equals(sem.UniversityYearID) && x.SemesterDetails.Equals(sem.SemesterDetails)) is null)
            {
                context.Semesters.Add(sem);
            }

            builder.setSemesterDetails(3);
            builder.setUniversityYearId(1);
            sem = builder.getResult();
            if (context.Semesters.FirstOrDefault(x => x.UniversityYearID.Equals(sem.UniversityYearID) && x.SemesterDetails.Equals(sem.SemesterDetails)) is null)
            {
                context.Semesters.Add(sem);
            }

            builder.setSemesterDetails(3);
            builder.setUniversityYearId(2);
            sem = builder.getResult();
            if (context.Semesters.FirstOrDefault(x => x.UniversityYearID.Equals(sem.UniversityYearID) && x.SemesterDetails.Equals(sem.SemesterDetails)) is null)
            {
                context.Semesters.Add(sem);
            }

            context.SaveChanges();
        }

        public static void seedStudentUniversityYear(UMSDatabaseContext context)
        {

            StudentUniversityYearBuilder builder = new StudentUniversityYearBuilder();
            builder.setStudentId(1);
            builder.setUniversityYear(1);
            var contract = builder.getResult();

            if (context.StudentUniversityYears.FirstOrDefault(x => 
                x.StudentId.Equals(contract.StudentId) && 
                x.UniversityYearId.Equals(contract.UniversityYearId)) 
                is null)
            {
                context.StudentUniversityYears.Add(contract);
            }

            builder.setStudentId(2);
            builder.setUniversityYear(2);
            contract = builder.getResult();

            if (context.StudentUniversityYears.FirstOrDefault(x =>
                 x.StudentId.Equals(contract.StudentId) &&
                 x.UniversityYearId.Equals(contract.UniversityYearId))
                 is null)
            {
                context.StudentUniversityYears.Add(contract);
            }

            builder.setStudentId(3);
            builder.setUniversityYear(3);
            contract = builder.getResult();

            if (context.StudentUniversityYears.FirstOrDefault(x =>
                 x.StudentId.Equals(contract.StudentId) &&
                 x.UniversityYearId.Equals(contract.UniversityYearId))
                 is null)
            {
                context.StudentUniversityYears.Add(contract);
            }

            context.SaveChanges();

        }

        public static void seedUniversityYear(UMSDatabaseContext context)
        {

            UniversityYearBuilder builder = new UniversityYearBuilder();
            builder.setYear(1);
            var year = builder.getResult();
            if(context.UniversityYears.FirstOrDefault(x => x.Year.Equals(year.Year)) is null)
            {
                context.UniversityYears.Add(year);
            }


            builder.setYear(2);
            year = builder.getResult();
            if(context.UniversityYears.FirstOrDefault(x => x.Year.Equals(year.Year)) is null)
            {
                context.UniversityYears.Add(year);
            }
            

            builder.setYear(3);
            year = builder.getResult();
            if (context.UniversityYears.FirstOrDefault(x => x.Year.Equals(year.Year)) is null)
            {
                context.UniversityYears.Add(year);
            }

            context.SaveChanges();
        }

        public static void SeedUsers(UMSDatabaseContext context)
        {
            StudentBuilder studentBuilder = new StudentBuilder();
            UserProfileBuilder userProfileBuilder = new UserProfileBuilder();
            UserBuilder userBuilder = new UserBuilder();

            userProfileBuilder.setFullname("AdminStaff1 fullname");
            userProfileBuilder.setEmail("adminStaff1@test.com");
            userProfileBuilder.setAge(30);
            userProfileBuilder.setProfileImage("https://www.inkspired.ro/media/catalog/product/cache/1/image/500x500/3de37283ddf23e1390f09983ed9630a3/S/c/Scooby_Doo_artwork_.png");

            userBuilder.setRole(Roles.Admin);
            userBuilder.setUsername("adminStaff1");
            userBuilder.setPassword("password");
            userBuilder.setUserProfile(userProfileBuilder.getResult());
            userBuilder.setAdminStaff(new AdminStaff());
            var adminStaff1 = userBuilder.getResult();
            if (context.Users.FirstOrDefault(x => x.Username.Equals(adminStaff1.Username)) is null)
            {
                context.Users.Add(adminStaff1);
            }


            userProfileBuilder.setFullname("AdminStaff2 fullname");
            userProfileBuilder.setEmail("adminStaff2@test.com");
            userProfileBuilder.setAge(35);
            userProfileBuilder.setProfileImage("https://www.inkspired.ro/media/catalog/product/cache/1/image/500x500/3de37283ddf23e1390f09983ed9630a3/S/c/Scooby_Doo_artwork_.png");

            userBuilder.setRole(Roles.Admin);
            userBuilder.setUsername("adminStaff2");
            userBuilder.setPassword("password");
            userBuilder.setUserProfile(userProfileBuilder.getResult());
            userBuilder.setAdminStaff(new AdminStaff());
            var adminStaff2 = userBuilder.getResult();
            if (context.Users.FirstOrDefault(x => x.Username.Equals(adminStaff2.Username)) is null)
            {
                context.Users.Add(adminStaff2);
            }


            userProfileBuilder.setFullname("Teacher1 fullname");
            userProfileBuilder.setEmail("teacher1@test.com");
            userProfileBuilder.setAge(26);
            userProfileBuilder.setProfileImage("https://www.inkspired.ro/media/catalog/product/cache/1/image/500x500/3de37283ddf23e1390f09983ed9630a3/S/c/Scooby_Doo_artwork_.png");

            userBuilder.setRole(Roles.Teacher);
            userBuilder.setUsername("teacher1");
            userBuilder.setPassword("password");
            userBuilder.setUserProfile(userProfileBuilder.getResult());
            userBuilder.setTeacher(new Teacher());
            var teacher1 = userBuilder.getResult();
            if (context.Users.FirstOrDefault(x => x.Username.Equals(teacher1.Username)) is null)
            {
                context.Users.Add(teacher1);
            }

            userProfileBuilder.setFullname("Teacher2 fullname");
            userProfileBuilder.setEmail("teacher2@test.com");
            userProfileBuilder.setAge(30);
            userProfileBuilder.setProfileImage("https://www.inkspired.ro/media/catalog/product/cache/1/image/500x500/3de37283ddf23e1390f09983ed9630a3/S/c/Scooby_Doo_artwork_.png");

            userBuilder.setRole(Roles.Teacher);
            userBuilder.setUsername("teacher2");
            userBuilder.setPassword("password");
            userBuilder.setUserProfile(userProfileBuilder.getResult());
            userBuilder.setTeacher(new Teacher());
            var teacher2 = userBuilder.getResult();
            if (context.Users.FirstOrDefault(x => x.Username.Equals(teacher2.Username)) is null)
            {
                context.Users.Add(teacher2);
            }


            userProfileBuilder.setFullname("Student1 fullname");
            userProfileBuilder.setEmail("student1@test.com");
            userProfileBuilder.setAge(22);
            userProfileBuilder.setProfileImage("https://www.inkspired.ro/media/catalog/product/cache/1/image/500x500/3de37283ddf23e1390f09983ed9630a3/S/c/Scooby_Doo_artwork_.png");

            studentBuilder.setGroup(context.Groups.FirstOrDefault(g => g.Id == 1));
            studentBuilder.setSpecialization(context.Specializations.FirstOrDefault(g => g.Id == 1));

            userBuilder.setRole(Roles.Student);
            userBuilder.setUsername("student1");
            userBuilder.setPassword("password");
            userBuilder.setUserProfile(userProfileBuilder.getResult());
            userBuilder.setStudent(studentBuilder.getResult());
            var student1 = userBuilder.getResult();
            if (context.Users.FirstOrDefault(x => x.Username.Equals(student1.Username)) is null)
            {
                context.Users.Add(student1);
            }

            userProfileBuilder.setFullname("Student2 fullname");
            userProfileBuilder.setEmail("student2@test.com");
            userProfileBuilder.setAge(21);
            userProfileBuilder.setProfileImage("https://www.inkspired.ro/media/catalog/product/cache/1/image/500x500/3de37283ddf23e1390f09983ed9630a3/S/c/Scooby_Doo_artwork_.png");

            studentBuilder.setGroup(context.Groups.FirstOrDefault(g => g.Id == 2));
            studentBuilder.setSpecialization(context.Specializations.FirstOrDefault(g => g.Id == 1));

            userBuilder.setRole(Roles.Student);
            userBuilder.setUsername("student2");
            userBuilder.setPassword("password");
            userBuilder.setUserProfile(userProfileBuilder.getResult());
            userBuilder.setStudent(studentBuilder.getResult());
            var student2 = userBuilder.getResult();
            if (context.Users.FirstOrDefault(x => x.Username.Equals(student2.Username)) is null)
            {
                context.Users.Add(student2);
            }

            userProfileBuilder.setFullname("Student3 fullname");
            userProfileBuilder.setEmail("student3@test.com");
            userProfileBuilder.setAge(22);
            userProfileBuilder.setProfileImage("https://www.inkspired.ro/media/catalog/product/cache/1/image/500x500/3de37283ddf23e1390f09983ed9630a3/S/c/Scooby_Doo_artwork_.png");

            studentBuilder.setGroup(context.Groups.FirstOrDefault(g => g.Id == 2));
            studentBuilder.setSpecialization(context.Specializations.FirstOrDefault(g => g.Id == 2));

            userBuilder.setRole(Roles.Student);
            userBuilder.setUsername("student3");
            userBuilder.setPassword("password");
            userBuilder.setUserProfile(userProfileBuilder.getResult());
            userBuilder.setStudent(studentBuilder.getResult());
            var student3 = userBuilder.getResult();
            if (context.Users.FirstOrDefault(x => x.Username.Equals(student3.Username)) is null)
            {
                context.Users.Add(student3);
            }

            // add teacher, add adminStaff
            context.SaveChanges();
        }

        public static void SeedSpecialization(UMSDatabaseContext context)
        {
            SpecializationBuilder builder = new SpecializationBuilder();
            builder.setName("Informatică în limba română");
            var specialization = builder.getResult();

            if (context.Specializations.FirstOrDefault(x => x.Name.Equals(specialization.Name)) is null)
            {
                context.Specializations.Add(specialization);
            };

            builder.setName("Informatică în limba engleză");
            specialization = builder.getResult();

            if (context.Specializations.FirstOrDefault(x => x.Name.Equals(specialization.Name)) is null)
            {
                context.Specializations.Add(specialization);
            };

            builder.setName("Informatică în limba germană");
            specialization = builder.getResult();

            if (context.Specializations.FirstOrDefault(x => x.Name.Equals(specialization.Name)) is null)
            {
                context.Specializations.Add(specialization);
            };

            builder.setName("Informatică în limba maghiară");
            specialization = builder.getResult();

            if (context.Specializations.FirstOrDefault(x => x.Name.Equals(specialization.Name)) is null)
            {
                context.Specializations.Add(specialization);
            };

            builder.setName("Informatică în limba română");
            specialization = builder.getResult();

            if (context.Specializations.FirstOrDefault(x => x.Name.Equals(specialization.Name)) is null)
            {
                context.Specializations.Add(specialization);
            };

            builder.setName("Matematică în limba engleză");
            specialization = builder.getResult();

            if (context.Specializations.FirstOrDefault(x => x.Name.Equals(specialization.Name)) is null)
            {
                context.Specializations.Add(specialization);
            };

            builder.setName("Matematică în limba germană");
            specialization = builder.getResult();

            if (context.Specializations.FirstOrDefault(x => x.Name.Equals(specialization.Name)) is null)
            {
                context.Specializations.Add(specialization);
            };

            builder.setName("Matematică în limba maghiară");
            specialization = builder.getResult();

            if (context.Specializations.FirstOrDefault(x => x.Name.Equals(specialization.Name)) is null)
            {
                context.Specializations.Add(specialization);
            };

            context.SaveChanges();
        }

        public static void SeedGroup(UMSDatabaseContext context)
        {
            var builder = new GroupBuilder();

            builder.setName("Group1");
            Group group = builder.getResult();

            if (context.Groups.FirstOrDefault(x => x.Name.Equals(group.Name)) is null)
            {
                context.Groups.Add(group);
            };

            builder.setName("Group2");
            group = builder.getResult();

            if (context.Groups.FirstOrDefault(x => x.Name.Equals(group.Name)) is null)
            {
                context.Groups.Add(group);
            };

            builder.setName("Group3");
            group = builder.getResult();

            if (context.Groups.FirstOrDefault(x => x.Name.Equals(group.Name)) is null)
            {
                context.Groups.Add(group);
            };

            context.SaveChanges();
        }
    }
}