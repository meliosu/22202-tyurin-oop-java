package org.nsu.oop.task2.instructions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nsu.oop.task2.exceptions.BadContextException;

public class TestPop extends TestInstruction {
    @Test
    void pushPopPrint() {
        String[] instructions = {"PUSH 10", "POP VAR", "PUSH VAR", "PRINT"};
        calculator.executeInstructions(instructions);

        Assertions.assertEquals("10.0", outputStream.toString().trim());
    }

    @Test
    void popEmpty() {
        String[] instructions = {"POP VAR"};

        Assertions.assertThrows(BadContextException.class, () -> calculator.executeInstructions(instructions));
    }
}
