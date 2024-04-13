package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

public abstract class Instruction {
    public abstract void execute(String[] operands, Context context) throws InstructionException;
}
