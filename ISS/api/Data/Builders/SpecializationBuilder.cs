using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Builders
{
    public class SpecializationBuilder : Builder<Specialization>
    {
        public void setName(string name)
        {
            product.Name = name;
        }
    }
}
