using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Business.DTOs {
    public class SemestersAndSpecializationsResponse {
        public List<SemesterDTO> Semesters { get; set; }
        public List<SpecializationDTO> Specializations { get; set; }
    }
}
