package org.nsu.oop.task5.util;

import java.io.Serializable;

public enum Player implements Serializable {
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
