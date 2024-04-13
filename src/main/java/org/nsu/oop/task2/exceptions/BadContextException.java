package org.nsu.oop.task2.exceptions;

import org.nsu.oop.task2.instructions.Instruction;

public class BadContextException extends InstructionException {
    public BadContextException(String message, Instruction instruction) {
        super("context error: " + message, instruction);
    }
}
