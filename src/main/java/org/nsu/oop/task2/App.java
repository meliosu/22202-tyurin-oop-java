package org.nsu.oop.task2;

public class App {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        if (args.length > 0) {
            calculator.executeFile(args[0]);
        } else {
            calculator.executeStdin();
        }
    }
}
