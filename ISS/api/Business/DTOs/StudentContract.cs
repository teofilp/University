namespace Business.DTOs;

public class StudentContract
{
    public int Year { get; set; }
    public List<ContractDisciplineDetails> ContractDisciplineDetails { get; set; }
}