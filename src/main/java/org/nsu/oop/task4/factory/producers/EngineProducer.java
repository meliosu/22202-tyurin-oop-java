package org.nsu.oop.task4.factory.producers;

import org.nsu.oop.task4.factory.parts.Engine;
import org.nsu.oop.task4.factory.parts.Part;
import org.nsu.oop.task4.factory.storage.Storage;

public class EngineProducer extends Producer {
    public EngineProducer(Storage<Part> storage) {
        super(storage, 10);
    }

    @Override
    public Part producePart() {
        return new Engine(serialNumber++);
    }
}
