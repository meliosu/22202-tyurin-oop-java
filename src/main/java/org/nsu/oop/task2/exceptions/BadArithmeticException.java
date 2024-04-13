package org.nsu.oop.task2.exceptions;

import org.nsu.oop.task2.instructions.Instruction;

public class BadArithmeticException extends InstructionException {
    public BadArithmeticException(String message, Instruction instruction) {
        super("arithmetic error: " + message, instruction);
    }
}
