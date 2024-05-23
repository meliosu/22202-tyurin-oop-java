package org.nsu.oop.task4.factory.producer;

import org.nsu.oop.task4.factory.parts.CarPart;
import org.nsu.oop.task4.factory.parts.CarTrunk;
import org.nsu.oop.task4.factory.storage.CarTrunkStorage;

public class CarTrunkProducer extends Producer {
    private int serialNumber = 0;

    public CarTrunkProducer(CarTrunkStorage storage) {
        super(storage, 10);
    }

    @Override
    public CarPart producePart() {
        return new CarTrunk(serialNumber++);
    }
}
