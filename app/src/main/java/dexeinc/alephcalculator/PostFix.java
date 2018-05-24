package dexeinc.alephcalculator;

import java.util.LinkedList;
import java.util.Stack;

public class PostFix {

    /**The method will receives the operation and convert it to postfix*/
    public static String toPostfix(String operation) {
        Stack<String> symbols = new Stack<>(); //stack for symbols
        LinkedList<String> postFix = new LinkedList<>(); //buffer for postfix operation
        StringBuilder buffer = new StringBuilder(); //buffer for multi digit numbers
        StringBuilder reverse = new StringBuilder();
        operation = "("+ operation +")";
        /*check for any negative numbers*/
        operation = operation.replace("(-", "(0-");
		/*This for loop will iterate through the string and determine what to do depending
		/*if charAt(i) is an operation symbol or a digit of a multi-digit expressions*/
        for (int i = 0; i < operation.length(); i++) {
            switch (operation.charAt(i)) {
                case '(':
                    symbols.push("(");
                    break;
                case ')':
                    if (!buffer.toString().equals("")) {
                        postFix.add(buffer.toString());
                        buffer = new StringBuilder();
                    }
                    while (!symbols.peek().equals("(")) {
                        if (symbols.peek().matches("[+-/*]")) {
                            reverse.append(symbols.pop());
                        }
                    }
                    if (symbols.peek().equals("(")) {
                        symbols.pop();
                        for (int j = reverse.length()-1; j >= 0; j--) {
                            postFix.add(Character.toString(reverse.charAt(j)));
                        }
                    }
                    reverse = new StringBuilder();
                    break;
                case '*':
                    if (!buffer.toString().equals("")) {
                        postFix.add(buffer.toString());
                        buffer = new StringBuilder();
                    }
                    if (symbols.peek().matches("[/*]")) {
                        postFix.add(symbols.pop());
                        
                        symbols.push("*");
                    }
                    else {
                        symbols.push("*");
                    }
                    break;
                case '/':
                    if (!buffer.toString().equals("")) {
                        postFix.add(buffer.toString());
                        buffer = new StringBuilder();
                        
                    }
                    if (symbols.peek().matches("[/*]")) {
                        postFix.add( symbols.pop());
                        
                        symbols.push("/");
                    }
                    else {
                        symbols.push("/");
                    }
                    break;
                case '+':
                    if (!buffer.toString().equals("")) {
                        postFix.add(buffer.toString());
                        buffer = new StringBuilder();
                        
                    }
                    if (symbols.peek().matches("[+-/*]")) {
                        postFix.add( symbols.pop());
                        symbols.push("+");
                    }
                    else {
                        symbols.push("+");
                    }
                    break;
                case '-':
                    if (!buffer.toString().equals("")) {
                        postFix.add(buffer.toString());
                        buffer = new StringBuilder();
                    }
                    if (symbols.peek().matches("[+-/*]")) {
                        postFix.add( symbols.pop());
                        symbols.push("-");
                    }
                    else {
                        symbols.push("-");
                    }
                    break;
                default:
                    buffer.append(Character.toString(operation.charAt(i)));
                    break;
            }
        }
        return postFixEvaluator(postFix);
    }

    /**This method will process the the postFix array wnd give back the resultDisplay*/
    private static String postFixEvaluator(LinkedList<String> postFix) {
        Stack<Double> evaluator = new Stack<>();
        String resultDisplay;
        for (String aPostFix : postFix) {
            /*If the element at i is a number*/
            if (!aPostFix.matches("[+-/*]") && !aPostFix.equals("")) {
                evaluator.push(Double.parseDouble(aPostFix));
            }
            /*If the element at i is an operand*/
            else if (aPostFix.matches("[+-/*]")) {
                double b = evaluator.pop();
                double a = evaluator.pop();
                switch (aPostFix) {
                    case "*":
                        evaluator.push(a * b);
                        break;
                    case "/":
                        evaluator.push(a / b);
                        break;
                    case "+":
                        evaluator.push(a + b);
                        break;
                    case "-":
                        evaluator.push(a - b);
                        break;
                }
            }
            /*if the end of the array is reached*/
            else {
                break;
            }
        }
        if (evaluator.peek()%1 == 0) {
            resultDisplay = String.valueOf(evaluator.peek().intValue());
        }
        else {
            resultDisplay = String.valueOf(evaluator.peek());
        }
        return resultDisplay;
    }

}
