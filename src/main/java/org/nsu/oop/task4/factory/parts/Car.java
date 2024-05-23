package org.nsu.oop.task4.factory.parts;

import org.nsu.oop.task4.factory.parts.CarAccessory;
import org.nsu.oop.task4.factory.parts.CarEngine;
import org.nsu.oop.task4.factory.parts.CarTrunk;

public class Car extends CarPart /*temporary*/ {
    private final CarEngine engine;
    private final CarTrunk trunk;
    private final CarAccessory accessory;

    public Car() {
        super(0);
        engine = null;
        trunk = null;
        accessory = null;
    }

    public Car(CarEngine engine, CarTrunk trunk, CarAccessory accessory) {
        super(0);
        this.engine = engine;
        this.trunk = trunk;
        this.accessory = accessory;
    }

    @Override
    public String toString() {
        return String.format("Car (Engine: %d, Trunk: %d, Accessory: %d)",
                engine.getId(), trunk.getId(), accessory.getId());
    }
}
