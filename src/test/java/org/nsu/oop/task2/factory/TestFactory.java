package org.nsu.oop.task2.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nsu.oop.task2.InstructionFactory;
import org.nsu.oop.task2.exceptions.InstructionFactoryException;

public class TestFactory {
    InstructionFactory factory;

    @BeforeEach
    public void setup() {
        factory = new InstructionFactory("/factory.cfg");
    }

    @Test
    void getValidInstructions() {
        String[] instructions = {"ADD", "SUB", "MUL", "DIV", "PRINT"};

        Assertions.assertDoesNotThrow(() -> {
            for (String instructionName : instructions) {
                factory.getInstruction(instructionName);
            }
        });
    }

    @Test
    void getInvalidInstruction() {
        Assertions.assertThrows(InstructionFactoryException.class,
                () -> factory.getInstruction("DOESNT_EXIST")
        );
    }

    @Test
    void invalidFactoryConfigPath() {
        Assertions.assertThrows(InstructionFactoryException.class,
                () -> new InstructionFactory("/not a valid path")
        );
    }
}
