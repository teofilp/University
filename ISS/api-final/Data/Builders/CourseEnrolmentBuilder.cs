using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Builders
{
    internal class CourseEnrolmentBuilder : Builder<CourseEnrolments>
    {
        public void setStudentUniversityYearId(int id)
        {
            product.StudentUniversityYearID = id;
        }

        public void setCoursesId(int id)
        {
            product.CoursesID = id;
        }
    }
}
