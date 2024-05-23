package org.nsu.oop.task4.factory.producers;

import org.nsu.oop.task4.factory.parts.Accessory;
import org.nsu.oop.task4.factory.parts.Part;
import org.nsu.oop.task4.factory.storage.Storage;

public class AccessoryProducer extends Producer {
    public AccessoryProducer(Storage<Part> storage) {
        super(storage, 10);
    }

    @Override
    public Part producePart() {
        return new Accessory(serialNumber++);
    }
}
