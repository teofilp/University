using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Data.Models;

namespace Data.Builders
{
    public class CourseBuilder : Builder<Courses>
    {
        public void setDisciplineName(string name)
        {
            product.DisciplineName = name;
        }

        public void setTeacherId(int id)
        {
            product.TeacherId = id;
        }

        public void setSemesterId(int id)
        {
            product.SemesterId = id;
        }

        public void setOptionalFlag(bool flag)
        {
            product.OptionalFlag = flag;
        }

        public void setNumberOfStudents(int nr)
        {
            product.NumberOfStudents = nr;
        }

        public void setSpecializationId(int id)
        {
            product.SpecializationID = id;
        }

    }
}
