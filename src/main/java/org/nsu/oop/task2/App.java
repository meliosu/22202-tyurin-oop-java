package org.nsu.oop.task2;

public class App {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        if (args.length > 1) {
            calculator.executeFile(args[1]);
        } else {
            calculator.executeStdin();
        }
    }
}
