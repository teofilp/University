using Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Builders
{
    public class GroupBuilder : Builder<Group>
    {
        public void setName(String name)
        {
            product.Name = name;
        }

    }
}
