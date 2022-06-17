namespace WebApi.Requests;

public class AssignDeveloperRequest
{
    public string Username { get; set; }
    public List<string> Projects { get; set; }
}