package org.nsu.oop.task4.factory.parts;

import org.nsu.oop.task4.factory.parts.CarAccessory;
import org.nsu.oop.task4.factory.parts.CarEngine;
import org.nsu.oop.task4.factory.parts.CarTrunk;

public class Car {
    private final CarEngine engine;
    private final CarTrunk trunk;
    private final CarAccessory accessory;

    public Car(CarEngine engine, CarTrunk trunk, CarAccessory accessory) {
        this.engine = engine;
        this.trunk = trunk;
        this.accessory = accessory;
    }
}