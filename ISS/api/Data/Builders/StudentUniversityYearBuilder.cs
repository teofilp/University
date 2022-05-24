using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Builders
{
    public class StudentUniversityYearBuilder : Builder<StudentUniversityYear>
    {
        public void setStudentId(int id)
        {
            product.StudentId = id;
        }
        public void setUniversityYear(int id)
        {
            product.UniversityYearId = id;
        }
    }
}
