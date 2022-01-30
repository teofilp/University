package com.example.gui;

import java.util.List;

public class SemaphoreTableRow {
    private String key, count, value;

    public SemaphoreTableRow(String key, String count, List<Integer> value) {
        this.key = key;
        this.count = count;
        this.value = value.stream().map(Object::toString).reduce((acc, curr) -> acc + ", " + curr).orElse("");
    }

    public String getCount() {
        return count;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
