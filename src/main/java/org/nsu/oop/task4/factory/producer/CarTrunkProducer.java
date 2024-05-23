package org.nsu.oop.task4.factory.producer;

import org.nsu.oop.task4.factory.parts.CarPart;
import org.nsu.oop.task4.factory.parts.CarTrunk;
import org.nsu.oop.task4.factory.storage.Storage;

public class CarTrunkProducer extends Producer {
    public CarTrunkProducer(Storage<CarPart> storage) {
        super(storage, 10);
    }

    @Override
    public CarPart producePart() {
        return new CarTrunk(serialNumber++);
    }
}
