package com.example.gui;

public class SymbolTableRow {
    private String value, name;

    public SymbolTableRow(String name, String value) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
