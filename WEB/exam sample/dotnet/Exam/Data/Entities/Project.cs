namespace Data.Entities;

public class Project
{
    public int Id { get; set; }
    public string Name { get; set; }
    public string Description { get; set; }
    public string Members { get; set; }
    public int ManagerId { get; set; }
    
    public SoftwareDeveloper Manager { get; set; }
}