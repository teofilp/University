using Data.models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Models
{
    public class UserProfile
    {
        public int Id { get; set; }

        public string Fullname { get; set; }
        
        public string Email { get; set; }
        
        public int Age { get; set; }
        
        public string ProfileImageUrl { get; set; }

        public int UserId { get; set; }

        public User User { get; set; }
    }
}
