package org.nsu.oop.task4.factory.producer;

import org.nsu.oop.task4.factory.parts.CarAccessory;
import org.nsu.oop.task4.factory.parts.CarPart;
import org.nsu.oop.task4.factory.storage.CarAccessoryStorage;
import org.nsu.oop.task4.factory.storage.CarEngineStorage;

public class CarAccessoryProducer extends Producer {
    private int serialNumber = 0;

    public CarAccessoryProducer(CarAccessoryStorage storage) {
        super(storage, 10);
    }

    @Override
    public CarPart producePart() {
        return new CarAccessory(serialNumber++);
    }
}
