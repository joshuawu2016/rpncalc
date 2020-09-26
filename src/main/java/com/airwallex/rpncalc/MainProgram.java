package com.airwallex.rpncalc;

import com.airwallex.rpncalc.operator.IRpnCalcOperator;

import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        RpnCalculator calc = new RpnCalculator();
        calc.setPostProcess(c -> {
            System.out.println(c.getNumStackStatus());
        });
        calc.setOnExceptionProcess(c -> {
            IRpnCalcOperator calcOperator = c.getLastOperator();
            System.out.println(String.format("%s %s (position: %d): %s", calcOperator.getType(), calcOperator.getName(), calcOperator.getPosition(), c.getErrorMessage()));
        });

        printHeader();
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if ("quit".equalsIgnoreCase(line)) {
                    System.out.println("bye-bye");
                    break;
                } else {
                    // System.out.println("you type:[" + line + "]");
                    calc.process(line);
                }
            }
        }
    }

    public static void printHeader() {
        System.out.println("**************** Welcome to RPN Calculator ***************");
        System.out.println("*                                                        *");
        System.out.println("*  Please enter one line contains operators and numbers  *");
        System.out.println("*  Available operators are +, -, *, /, sqrt, undo, clear *");
        System.out.println("*  Type quit to exit program                             *");
        System.out.println("*                                                        *");
        System.out.println("**********************************************************");
    }

}
