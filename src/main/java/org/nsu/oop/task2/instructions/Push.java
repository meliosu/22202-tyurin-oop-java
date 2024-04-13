package org.nsu.oop.task2.instructions;

import org.nsu.oop.task2.Context;
import org.nsu.oop.task2.exceptions.*;

public class Push extends Instruction {
    @Override
    public void execute(String[] operands, Context context) throws InstructionException {
        String item;
        try {
            item = operands[0];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new BadOperandsException("no operands provided", this);
        }

        try {
            Double itemValue = Double.valueOf(item);
            context.push(itemValue);
        } catch (NumberFormatException ignored) {
            if (!context.hasVariable(item)) {
                String message = item + " is not a valid number nor a defined variable";
                throw new BadContextException(message, this);
            } else {
                Double variableValue = context.getVariable(item);
                context.push(variableValue);
            }
        }
    }
}
