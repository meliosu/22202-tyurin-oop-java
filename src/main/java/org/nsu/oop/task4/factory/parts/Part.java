package org.nsu.oop.task4.factory.parts;

public abstract class Part {
    private final int id;

    public Part(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
