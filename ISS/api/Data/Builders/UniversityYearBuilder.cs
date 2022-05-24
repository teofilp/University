using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Builders
{
    public class UniversityYearBuilder : Builder<UniversityYear>
    {
        public void setYear(int year)
        {
            product.Year = year;
        }
    }
}
