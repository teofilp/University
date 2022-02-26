package com.example.gui;

import java.util.List;

public class BarrierTableRow {
    public int index, value;
    public String waitingThreads;

    public BarrierTableRow(int index, int value, String waitingThreads) {
        this.index = index;
        this.value = value;
        this.waitingThreads = waitingThreads;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    public String getWaitingThreads() {
        return waitingThreads;
    }
}
