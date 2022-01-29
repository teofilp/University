package model.statement;

import model.*;
import model.type.Type;

public class CreateProcedureStatement implements Statement {
    private String procName;
    private ProcedureDefinition procedureDefinition;

    public CreateProcedureStatement(String procName, ProcedureDefinition procedureDefinition) {
        this.procName = procName;
        this.procedureDefinition = procedureDefinition;
    }

    @Override
    public Statement clone() {
        return new CreateProcedureStatement(procName, procedureDefinition.clone());
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var procedureTable = state.getProcedureTable();
        procedureTable.put(procName, procedureDefinition);

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {

    }

    @Override
    public String toString() {
        return String.format("procedure %s(%s)", procName, procedureDefinition.getParameters().stream().reduce("", (acc, curr) -> acc + "," + curr));
    }
}
