namespace Data.Entities;

public class SoftwareDeveloper
{
    public int Id { get; set; }
    public string Name { get; set; }
    public int Age { get; set; }
    public string Skills { get; set; }
    
    public List<Project> Projects { get; set; }
}