using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Data.Models;

namespace Business.DTOs {
    public class LoginResponse {
        public string Token { get; set; }
        public int UserId { get; set; }
        public Roles Role { get; set; }
    }
}
