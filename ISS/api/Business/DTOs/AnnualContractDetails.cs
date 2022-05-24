namespace Business.DTOs;

public class AnnualContractDetails
{
    public int Year { get; set; }
    public List<ContractDisciplineDetails> MandatoryDisciplines { get; set; }
    public List<ContractDisciplineDetails> OptionalDisciplines { get; set; }
    
}