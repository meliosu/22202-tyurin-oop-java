package org.nsu.oop.task2.instructions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nsu.oop.task2.exceptions.BadContextException;

public class TestMul extends TestInstruction {
    @Test
    void elementary() {
        String[] instructions = {"PUSH 4", "PUSH 4", "MUL", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("16.0", outputStream.toString().trim());
    }

    @Test
    void advanced() {
        String[] instructions = {"PUSH 20", "PUSH 0.5", "MUL", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("10.0", outputStream.toString().trim());
    }

    @Test
    void exception() {
        String[] instructions = {"PUSH 2", "MUL"};

        Assertions.assertThrows(BadContextException.class, () -> calculator.executeInstructions(instructions));
    }
}
