package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

import java.util.EmptyStackException;

public class Print extends Instruction {
    @Override
    public void execute(String[] operands, Context context) throws InstructionException {
        if (operands.length != 0) {
            throw new BadOperandsException("no operands expected", this);
        }

        try {
            Double value = context.peek();
            System.out.println(value);
        } catch (EmptyStackException exception) {
            throw new BadContextException("no value in stack", this);
        }
    }
}
