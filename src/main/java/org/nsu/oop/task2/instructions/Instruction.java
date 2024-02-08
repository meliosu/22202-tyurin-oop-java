package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

public abstract class Instruction {
    public abstract void execute(String[] operands, Context context) throws InstructionException;

    protected int getRequiredOperandsCount() {
        return 0;
    }

    protected int getMinimumStackSize() {
        return 0;
    }

    public void checkOperandsCount(Object[] operands) throws BadOperandsException {
        if (getRequiredOperandsCount() != operands.length) {
            String message = "expected " + getRequiredOperandsCount()
                    + " operands, got " + operands.length;

            throw new BadOperandsException(message, this);
        }
    }

    public void checkStackSize(Context context) throws BadContextException {
        if (context.stack.size() < getMinimumStackSize()) {
            String message = "not enough numbers in stack: has " + context.stack.size()
                    + ", but instruction needs" + getMinimumStackSize();

            throw new BadContextException(message, this);
        }
    }
}
