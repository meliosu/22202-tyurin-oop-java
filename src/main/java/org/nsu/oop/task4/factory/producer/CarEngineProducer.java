package org.nsu.oop.task4.factory.producer;

import org.nsu.oop.task4.factory.parts.CarEngine;
import org.nsu.oop.task4.factory.parts.CarPart;
import org.nsu.oop.task4.factory.storage.Storage;

public class CarEngineProducer extends Producer {
    public CarEngineProducer(Storage<CarPart> storage) {
        super(storage, 10);
    }

    @Override
    public CarPart producePart() {
        return new CarEngine(serialNumber++);
    }
}
