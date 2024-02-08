package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

public class Push extends Instruction {
    @Override
    public void execute(String[] operands, Context context) throws InstructionException {
        checkOperandsCount(operands);

        String item = operands[0];
        try {
            Double itemValue = Double.valueOf(item);
            context.stack.push(itemValue);
        } catch (NumberFormatException ignored) {
            if (!context.variables.containsKey(item)) {
                String message = item + " is not a valid number nor a defined variable";
                throw new BadContextException(message, this);
            } else {
                Double variableValue = context.variables.get(item);
                context.stack.push(variableValue);
            }
        }
    }

    @Override
    protected int getRequiredOperandsCount() {
        return 1;
    }
}
