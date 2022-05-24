namespace Business.DTOs;

public class OptionalCourse
{
    public int CourseId { get; set; }
    public string CourseName { get; set; }
    public string Teacher { get; set; }
    public string Specialization { get; set; }
    public int Semester { get; set; }
    public int Year { get; set; }
    
    public bool IsApproved { get; set; }
}