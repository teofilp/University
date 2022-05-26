namespace Business.DTOs;

public class TeacherCourse
{
    public string CourseName { get; set; }
    public string Specialization { get; set; }
    public bool IsOptional { get; set; }
    public bool Approved { get; set; }
    public int MaxStudentsNumber { get; set; }
    public int Semester { get; set; }
    public int Year { get; set; }
    public string Teacher { get; set; }
}