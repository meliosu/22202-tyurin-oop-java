package org.nsu.oop.task2.instructions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nsu.oop.task2.exceptions.BadContextException;

public class TestSub extends TestInstruction {
    @Test
    void elementary() {
        String[] instructions = {"PUSH 2", "PUSH 3", "SUB", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("1.0", outputStream.toString().trim());
    }

    @Test
    void advanced() {
        String[] instructions = {"PUSH 12.5", "PUSH 225", "SUB", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("212.5", outputStream.toString().trim());
    }

    @Test
    void exception() {
        String[] instructions = {"PUSH 2", "SUB"};

        Assertions.assertThrows(BadContextException.class, () -> calculator.executeInstructions(instructions));
    }
}
