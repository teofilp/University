using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Builders
{
    public class SemesterBuilder : Builder<Semester>
    {
        public void setUniversityYearId(int id)
        {
            product.UniversityYearID = id;
        }

        public void setSemesterDetails(int details)
        {
            product.SemesterDetails = details;
        }
    }
}
