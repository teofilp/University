using Data.models;
using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BCrypt.Net;

namespace Data.Builders
{
    public class UserBuilder : Builder<User>
    {
        public void setRole(Roles role)
        {
            product.Role = role;
        }

        public void setUsername(string username)
        {
            product.Username = username;
        }

        public void setPassword(string password)
        {
            product.Password = BCrypt.Net.BCrypt.HashPassword(password);
        }

        public void setUserProfile(UserProfile profile)
        {
            product.UserProfile = profile;
        }

        public void setStudent(Student student)
        {
            product.Student = student;
        }
        
        public void setTeacher(Teacher teacher)
        {
            product.Teacher = teacher;
        }

        public void setAdminStaff(AdminStaff adminStaff)
        {
            product.AdminStaff = adminStaff;
        }
    }
}
