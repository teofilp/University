namespace Guestbook.Data.Models;

public class Guestbook
{
    public int Id { get; set; }
    public string Author { get; set; }
    public string Title { get; set; }
    public string Comment { get; set; }
    public DateTime Date { get; set; }
}