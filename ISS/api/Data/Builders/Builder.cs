using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Data.Builders
{
    public class Builder<T> where T : new()
    {
        protected T product;

        public Builder()
        {
            reset();
        }
        public void reset()
        {
            this.product = new T();
        }

        public T getResult()
        {
            T buildedProduct = product;
            reset();
            return buildedProduct;
        }
    }
}
