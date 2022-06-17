namespace WebApi.Requests;

public class AddProjectRequest
{
    public string Name { get; set; }
    public string Description { get; set; }
    public int ManagerId { get; set; }
    public string Members { get; set; }
}