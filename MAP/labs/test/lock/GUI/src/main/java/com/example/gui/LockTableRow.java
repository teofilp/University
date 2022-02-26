package com.example.gui;

public class LockTableRow {
    private int Key, Value;

    public LockTableRow(int key, int value) {
        Key = key;
        Value = value;
    }

    public int getKey() {
        return Key;
    }

    public int getValue() {
        return Value;
    }
}
