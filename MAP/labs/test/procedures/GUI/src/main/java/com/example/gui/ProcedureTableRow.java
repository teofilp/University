package com.example.gui;

import model.statement.Statement;

public class ProcedureTableRow {
    private String name, parameters, body;

    public ProcedureTableRow(String name, String parameters, Statement body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body.toString();
    }

    public String getName() {
        return name;
    }

    public String getParameters() {
        return parameters;
    }

    public String getBody() {
        return body;
    }
}
