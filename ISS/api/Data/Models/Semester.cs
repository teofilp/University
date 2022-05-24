using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Models
{
    public class Semester
    {
        public int Id { get; set; }
        public int UniversityYearID { get; set; }
        public int SemesterDetails { get; set; }
        public UniversityYear UniversityYear { get; set; }
        public List<Courses> Courses { get; set; }

    }
}
