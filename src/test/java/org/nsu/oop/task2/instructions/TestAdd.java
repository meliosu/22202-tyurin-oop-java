package org.nsu.oop.task2.instructions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nsu.oop.task2.exceptions.BadContextException;


public class TestAdd extends TestInstruction {
    @Test
    void elementary() {
        String[] instructions = {"PUSH 2", "PUSH 2", "ADD", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("4.0", outputStream.toString().trim());
    }

    @Test
    void advanced() {
        String[] instructions = {"PUSH 225", "PUSH 30.9", "ADD", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("255.9", outputStream.toString().trim());
    }

    @Test
    void exception() {
        String[] instructions = {"PUSH 2", "ADD"};

        Assertions.assertThrows(BadContextException.class, () -> calculator.executeInstructions(instructions));
    }
}
