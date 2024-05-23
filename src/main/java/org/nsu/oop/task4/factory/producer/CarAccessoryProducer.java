package org.nsu.oop.task4.factory.producer;

import org.nsu.oop.task4.factory.parts.CarAccessory;
import org.nsu.oop.task4.factory.parts.CarPart;
import org.nsu.oop.task4.factory.storage.Storage;

public class CarAccessoryProducer extends Producer {
    public CarAccessoryProducer(Storage<CarPart> storage) {
        super(storage, 10);
    }

    @Override
    public CarPart producePart() {
        return new CarAccessory(serialNumber++);
    }
}
