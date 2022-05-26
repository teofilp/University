using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Builders
{
    public class StudentBuilder : Builder<Student>
    {
        public void setGroup(Group group)
        {
            product.Group = group;
        }

        public void setSpecialization(Specialization specialization)
        {
            product.Specialization = specialization;
        }
    }
}
