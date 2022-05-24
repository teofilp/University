using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Models
{
    public class Courses
    {
        public int Id { get; set; }
        public string DisciplineName { get; set; }
        public int TeacherId { get; set; }
        public int SemesterId { get; set; }
        public bool OptionalFlag { get; set; }
        public int NumberOfStudents { get; set; }
        public int SpecializationID { get; set; }

        public Teacher Teacher { get; set; }
        public Semester Semester { get; set; }
        public Specialization Specialization { get; set; }

        public List<CourseEnrolments> Enrolments { get; set; }
    }
}
