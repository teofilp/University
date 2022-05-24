import { ContractDiscipline } from "./ContractDiscipline";

export interface AnnualContractDetails {
  year: number;
  mandatoryDisciplines: ContractDiscipline[];
  optionalDisciplines: ContractDiscipline[];
}
