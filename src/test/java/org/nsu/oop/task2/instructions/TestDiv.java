package org.nsu.oop.task2.instructions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nsu.oop.task2.exceptions.BadArithmeticException;
import org.nsu.oop.task2.exceptions.BadContextException;

public class TestDiv extends TestInstruction {
    @Test
    void elementary() {
        String[] instructions = {"PUSH 5", "PUSH 10", "DIV", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("2.0", outputStream.toString().trim());
    }

    @Test
    void advanced() {
        String[] instructions = {"PUSH 15", "PUSH 225", "DIV", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("15.0", outputStream.toString().trim());
    }

    @Test
    void exception() {
        String[] instructions = {"PUSH 2", "DIV"};

        Assertions.assertThrows(BadContextException.class, () -> calculator.executeInstructions(instructions));
    }

    @Test
    void divisionByZero() {
        String[] instructions = {"PUSH 0", "PUSH 100", "DIV"};

        Assertions.assertThrows(BadArithmeticException.class, () -> calculator.executeInstructions(instructions));
    }
}
