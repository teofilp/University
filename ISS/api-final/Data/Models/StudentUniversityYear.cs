using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Models
{
    public class StudentUniversityYear
    { 
        public int Id { get; set; }
        public int StudentId { get; set; }
        public int UniversityYearId { get; set; }
        public Student Student { get; set; }
        public UniversityYear UniversityYear { get; set; }

        public DateTime SignatureDate { get; set; }
    
        public List<CourseEnrolments> Enrolments { get; set; }
    }
}
