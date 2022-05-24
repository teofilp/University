namespace Business.DTOs;

public class AddAnnualContract
{
    public int UserId { get; set; }
    public int Year { get; set; }
    public List<int> CourseIds { get; set; }
}