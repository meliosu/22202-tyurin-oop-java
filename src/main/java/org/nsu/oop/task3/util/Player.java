package org.nsu.oop.task3.util;

public enum Player {
    First,
    Second;

    @Override
    public String toString() {
        switch (this) {
            case First:
                return "First";
            case Second:
                return "Second";
            default:
                return "";
        }
    }
}
