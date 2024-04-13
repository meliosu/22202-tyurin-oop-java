package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

import java.util.EmptyStackException;

public class Pop extends Instruction {
    @Override
    public void execute(String[] operands, Context context) throws InstructionException {
        try {
            String variable = operands[0];
            Double value = context.pop();
            context.insertVariable(variable, value);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new BadOperandsException("not enough operands", this);
        } catch (EmptyStackException exception) {
            throw new BadContextException("no value in stack", this);
        }
    }
}
