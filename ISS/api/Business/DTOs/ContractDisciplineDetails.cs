namespace Business.DTOs;

public class ContractDisciplineDetails
{
    public int CourseId { get; set; }
    public string CourseName { get; set; }
    public string Teacher { get; set; }
    public string Specialization { get; set; }
    public int Semester { get; set; }
    public bool IsOptional { get; set; }
}