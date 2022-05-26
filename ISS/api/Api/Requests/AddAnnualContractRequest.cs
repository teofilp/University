using System.Collections.Generic;

namespace Api.Requests;

public class AddAnnualContractRequest
{
    public int UserId { get; set; }
    public int Year { get; set; }
    public List<int> CourseIds { get; set; }
}