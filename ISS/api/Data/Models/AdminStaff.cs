﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Data.models;

namespace Data.Models
{
    public class AdminStaff
    {
        public int Id { get; set; }
        public int UserId { get; set; }

        public User User { get; set; }
    }
}
