using Data.models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Models
{
    public class Student
    {
        public int Id { get; set; }
        public int GroupId { get; set; }
        public int UserId { get; set; }
        public int SpecializationId { get; set; }
        public User User { get; set; }
        public Group Group { get; set; }
        public List<StudentUniversityYear> StudentUniversityYears { get; set; }
        public Specialization Specialization { get; set; }

    }
}
