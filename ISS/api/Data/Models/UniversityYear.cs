using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Models
{
    public class UniversityYear
    {
        public int Id { get; set; }
        public int Year { get; set; }

        public List<StudentUniversityYear> StudentUniversityYears { get; set; }
        public List<Semester> Semesters { get; set; }
    }
}
