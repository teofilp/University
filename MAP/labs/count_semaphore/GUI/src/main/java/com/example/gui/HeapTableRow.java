package com.example.gui;

import model.value.Value;

public class HeapTableRow {
    private String address, value;

    public HeapTableRow(String name, String value) {
        this.address = name;
        this.value = value;
    }

    public String getAddress() {
        return address;
    }

    public String getValue() {
        return value;
    }
}
