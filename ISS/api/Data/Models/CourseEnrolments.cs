using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Models
{
    public class CourseEnrolments
    {
        public int Id { get; set; }
        public int StudentUniversityYearID { get; set; }
        public int CoursesID { get; set; }
        public float Grade { get; set; }

        public Courses Course { get; set; }
        public StudentUniversityYear StudentYear { get; set; }
    }
}
