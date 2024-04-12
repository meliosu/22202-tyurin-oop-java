package org.nsu.oop.task2.instructions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPrint extends TestInstruction {
    @Test
    void print() {
        String[] instructions = {"PUSH 10", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("10.0", outputStream.toString().trim());
    }
}
