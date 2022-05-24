using Business.DTOs;
using Data;
using Data.models;
using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Business.Services {
    public class UserProfileService {
        private UMSDatabaseContext _context;

        public UserProfileService(UMSDatabaseContext context) {
            _context = context;
        }

        public int AddUserProfile(int userId, string fullname, int age, string imageUrl, string email) {
            var user = _context.Users.FirstOrDefault(u => u.Id == userId);
            if ( user == null)
                throw new Exception("User doesn't exist!");

            if (user.UserProfile != null)
                throw new Exception("User already has a profile!");

            UserProfile userProfile = new UserProfile {
                User = user,
                Fullname = fullname,
                Age = age,
                Email = email,
                ProfileImageUrl=imageUrl
            };

            user.UserProfile = userProfile;

            _context.Users.Update(user);
            _context.UserProfiles.Add(userProfile);
            _context.SaveChanges();

            return userProfile.Id;
        }

        public UserProfileDetails GetUserDetails(int userId) {
            var userProfile = _context.UserProfiles.FirstOrDefault(p => p.UserId == userId);

            if (userProfile == null)
                throw new Exception("User doesn't exist!");

            return new UserProfileDetails {
                Age = userProfile.Age,
                Email = userProfile.Email,
                ProfileImageUrl = userProfile.ProfileImageUrl,
                Fullname = userProfile.Fullname
            };
        }

        public bool UpdateUserProfile(int UserId, string Fullname, int Age, string ImageURL) {
            var userProfile = _context.UserProfiles.FirstOrDefault(u => u.UserId == UserId);

            if (Fullname == "" || Age < 14)
                throw new Exception("Invalid input!");

            userProfile.Fullname = Fullname;
            userProfile.Age = Age;
            userProfile.ProfileImageUrl = ImageURL;
            _context.UserProfiles.Update(userProfile);
            _context.SaveChanges();
          
            return true;
        }
    }
}
