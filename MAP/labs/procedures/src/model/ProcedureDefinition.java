package model;

import model.statement.Statement;

import java.util.ArrayList;

public class ProcedureDefinition implements Cloneable<ProcedureDefinition> {
    ArrayList<String> parameters;
    Statement statement;

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public Statement getStatement() {
        return statement;
    }

    public ProcedureDefinition(ArrayList<String> parameters, Statement statement) {
        this.parameters = parameters;
        this.statement = statement;
    }

    @Override
    public ProcedureDefinition clone() {
        return new ProcedureDefinition((ArrayList<String>) parameters.clone(), statement.clone());
    }
}
