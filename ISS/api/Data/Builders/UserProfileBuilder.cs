using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Builders
{
    public class UserProfileBuilder : Builder<UserProfile>
    {
        public void setAge(int age)
        {
            product.Age = age;
        }

        public void setEmail(string email)
        {
            product.Email = email;
        }

        public void setFullname(string fullname)
        {
            product.Fullname = fullname;
        }

        public void setProfileImage(string imageUrl)
        {
            product.ProfileImageUrl = imageUrl;
        }
    }
}
