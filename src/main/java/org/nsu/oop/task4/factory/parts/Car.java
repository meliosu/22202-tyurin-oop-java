package org.nsu.oop.task4.factory.parts;

public class Car {
    private final Engine engine;
    private final Frame frame;
    private final Accessory accessory;

    public Car(Engine engine, Frame frame, Accessory accessory) {
        this.engine = engine;
        this.frame = frame;
        this.accessory = accessory;
    }

    @Override
    public String toString() {
        return String.format("Car (Engine: %d, Trunk: %d, Accessory: %d)",
                engine.getId(), frame.getId(), accessory.getId());
    }
}
