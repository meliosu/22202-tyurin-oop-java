package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

public class Print extends Instruction {
    @Override
    public void execute(String[] operands, Context context) throws InstructionException {
        checkOperandsCount(operands);
        checkStackSize(context);

        Double value = context.stack.peek();
        System.out.println(value);
    }

    @Override
    protected int getMinimumStackSize() {
        return 1;
    }
}
