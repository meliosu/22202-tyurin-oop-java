package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

public class Pop extends Instruction {
    @Override
    public void execute(String[] operands, Context context) throws InstructionException {
        checkOperandsCount(operands);
        checkStackSize(context);

        String variable = operands[0];
        Double value = context.stack.pop();
        context.variables.put(variable, value);
    }

    @Override
    protected int getMinimumStackSize() {
        return 1;
    }

    @Override
    protected int getRequiredOperandsCount() {
        return 1;
    }
}
