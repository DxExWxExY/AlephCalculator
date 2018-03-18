package dexeinc.alephcalculator;

import java.util.Stack;

/**
 * Created by DxExWxExY on 3/8/2018.
 */

public class Operation {
    /**
     * The following variables are required to perform any operation.
     */
    public String operation = "0";
    public String result = "";
    private Boolean dotReset = true;
    private int parenthesisCount = 0;

    void numberPressed(int num) {
        /*if the operation operationDisplay has a 0*/
        if (operation.equals("0")) {
            operation = String.valueOf(num);
            result = "";
        }
        /*if there is a number in the operation operationDisplay*/
        else {
            operation += String.valueOf(num);
            result = "";
        }
    }

    void deleteOperation() {
        operation = "0";
        result = "";
        dotReset = true;
        parenthesisCount = 0;
    }

    void insertAddition() {
        /*if the resultDisplay is not empty, concatenate symbol*/
        if (!result.equals("")) {
            /*if the resultDisplay is negative*/
            if (result.charAt(0) == '-') {
                operation = "(" + result + ")" + "+";
                result = "";
                dotReset = true;
                result = "";
            }
            /*positive number present when adding*/
            else {
                operation = result + "+";
                result = "";
                dotReset = true;
                result = "";
            }
        }
        /*if there is a number or closing parenthesis, concatenate symbol*/
        else if ((Character.isDigit(operation.charAt(operation.length()-1)) && !operation.equals("0"))|| operation.charAt(operation.length()-1) == ')') {
            operation += "+";
            dotReset = true;
            result = "";
        }

        else if (!Character.isDigit(operation.charAt(operation.length()-1)) &&
                operation.charAt(operation.length()-1) != ')' &&
                operation.charAt(operation.length()-1) != '(') {
            operation = operation.substring(0, operation.length() - 1) + "+";
            dotReset = true;
            result = "";
        }
    }

    void insertSubtraction() {
        if (!result.equals("")) {
            if (result.charAt(0) == '-') {
                operation = "(" + result + ")" + "-";
                result = "";
                dotReset = true;
                result = "";
            }
            else {
                operation = result + "-";
                result = "";
                dotReset = true;
                result = "";
            }
        }
        else if (operation.equals("0")) {
            operation = "(-";
            parenthesisCount++;
        }
        else if (Character.isDigit(operation.charAt(operation.length()-1)) ||
                operation.charAt(operation.length()-1) == ')' ||
                operation.charAt(operation.length()-1) == '(') {
            operation += "-";
            dotReset = true;
            result = "";
        }
        else if (!Character.isDigit(operation.charAt(operation.length()-1)) &&
                operation.charAt(operation.length()-1) != ')' &&
                operation.charAt(operation.length()-1) != '(') {
            operation = operation.substring(0, operation.length() - 1) + "-";
            dotReset = true;
            result = "";
        }
    }

    void insertMultiplication() {
        /*Checks if resultDisplay operationDisplay is populated, then takes the resultDisplay and adds * at the end*/
        if (!result.equals("")) {
            if (result.charAt(0) == '-') {
                operation = "(" + result + ")" + "*";
                result = "";
                dotReset = true;
                result = "";
            }
            else {
                operation = result + "*";
                result = "";
                dotReset = true;
                result = "";
            }
        }
        /*Checks if operation last index is a number and that operation in not equals to 0
        * or if the last character is a closing parenthesis*/
        else if ((Character.isDigit(operation.charAt(operation.length()-1)) && !operation.equals("0")) || operation.charAt(operation.length()-1) == ')') {
            operation += "*";
            dotReset = true;
            result = "";
        }
        /*Checks if the last index isn't a number*/
        else if (!Character.isDigit(operation.charAt(operation.length()-1)) &&
                operation.charAt(operation.length()-1) != ')' &&
                operation.charAt(operation.length()-1) != '(') {
            operation = operation.substring(0, operation.length() - 1) + "*";
            dotReset = true;
            result = "";
        }
    }

    void insertDivision() {
        if (!result.equals("")) {
            if (result.charAt(0) == '-') {
                operation = "(" + result + ")" + "/";
                result = "";
                dotReset = true;
                result = "";
            }
            else {
                operation = result + "/";
                result = "";
                dotReset = true;
                result = "";
            }
        }
        else if ((Character.isDigit(operation.charAt(operation.length()-1)) && !operation.equals("0")) || operation.charAt(operation.length()-1) == ')') {
            operation += "/";
            dotReset = true;
            result = "";
        }
        else if (!Character.isDigit(operation.charAt(operation.length()-1)) &&
                operation.charAt(operation.length()-1) != ')' &&
                operation.charAt(operation.length()-1) != '(') {
            operation = operation.substring(0, operation.length() - 1) + "/";
            dotReset = true;
            result = "";
        }
    }

    void insertDot() {
        if (operation.length() == 1 && operation.charAt(0) == '0' && dotReset) {
            operation = "0.";
            dotReset = false;
        }
        else if (dotReset && Character.isDigit(operation.charAt(operation.length()-1))) {
            operation += ".";
            dotReset = false;
        }
        else if (dotReset && !Character.isDigit(operation.charAt(operation.length()-1))) {
            operation += "0.";
            dotReset = false;
        }
    }

    void deleteLast() {
        /*only 1 character left in the string*/
        if (operation.length() == 1) {
            operation = "0";

            result = "";
        }
        /*if operation string not empty*/
        else if (!operation.equals("")) {
            /*if last is a dot*/
            if (operation.charAt(operation.length()-1) == '.') {
                operation = operation.substring(0, operation.length() - 1);

                dotReset = true;
            }
            /*if last is a multiplication parenthesis*/
            else if (operation.charAt(operation.length()-1) == '(' && operation.charAt(operation.length()-2) == '*') {
                operation = operation.substring(0, operation.length() - 2);

                parenthesisCount--;
            }
            /*if last is an opening parenthesis*/
            else if (operation.charAt(operation.length()-1) == '(') {
                operation = operation.substring(0, operation.length() - 1);

                parenthesisCount--;
            }
            /*if last is an opening parenthesis*/
            else if (operation.charAt(operation.length()-1) == ')') {
                operation = operation.substring(0, operation.length() - 1);

                parenthesisCount++;
            }
            /*if last is "[0-9*+-/]"*/
            else {
                operation = operation.substring(0, operation.length() - 1);

            }
        }
    }

    void insertParenthesis() {
        /*Checks if operation is equal to 0 and replaces it with an opening parenthesis*/
        if (operation.equals("0")) {
            operation = "(";

            parenthesisCount++;
        }
        /*Checks if the last character isn't a digit and parenthesis count equals 0*/
        else if (parenthesisCount == 0  && !Character.isDigit(operation.charAt(operation.length()-1))) {
            if (operation.charAt(operation.length()-1) == ')') {
                operation += "*(";

                parenthesisCount++;
            }
            else {
                operation += "(";

                parenthesisCount++;
            }
        }
        /*Checks if the last character is a digit and parenthesis count doesn't equal 0*/
        else if (parenthesisCount != 0 && Character.isDigit(operation.charAt(operation.length()-1))) {
            operation += ")";

            parenthesisCount--;
        }
        /*Checks if the las char is a num or parenthesis then adds a *( to the operation*/
        else if (parenthesisCount == 0 &&
                (Character.isDigit(operation.charAt(operation.length()-1)) || operation.charAt(operation.length()-1) == ')')) {
            operation += "*(";

            parenthesisCount++;
        }
        /*Checks if the last char is ) and if the parenthesis count  != equal 0*/
        else if (parenthesisCount != 0 && operation.charAt(operation.length()-1) == ')') {
            operation += ")";

            parenthesisCount--;
        }
        /*Checks if the last char is an operand and adds an ( */
        else if (parenthesisCount != 0 && !Character.isDigit(operation.charAt(operation.length()-1))) {
            operation += "(";

            parenthesisCount++;
        }
    }

    void getPercentage() {
        double percent = Double.parseDouble(postFixProcessor()) / 100;
        result = String.valueOf(percent);
    }

    void getAnswer() {
        if (Character.toString(operation.charAt(operation.length()-1)).matches("[-+*/]")) {
            operation = operation.substring(0, operation.length()-1);

        }
        result = postFixProcessor();
    }

    /*Calculator Logic*/
    /*This will determine the size of the postFix array created in postFixProcessor method*/
    private int postFixArraySize(String operation) {
        int size = 0;
        for (int i = 0; i < operation.length(); i++) {
            if (Character.toString(operation.charAt(i)).matches("[+-/*]")) {
                size++;
            }
        }
        return (size * 2) + 1;
    }

    /*The method will receives the operation and convert it to postfix*/
    private String postFixProcessor() {
        Stack<String> symbols = new Stack<>(); //stack for symbols
        String buffer = ""; //buffer for multi digit numbers
        String[] postFix = new String[postFixArraySize(operation)]; //buffer for postfix operation
        int pfPointer = 0;
        String reverse = "";
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
                    if ((buffer != null) && !buffer.equals("")) {
                        postFix[pfPointer] = buffer;
                        buffer = "";
                        pfPointer++;
                    }
                    while (!symbols.peek().equals("(")) {
                        if (symbols.peek().matches("[+-/*]")) {
                            reverse += symbols.pop();
                        }
                    }
                    if (symbols.peek().equals("(")) {
                        symbols.pop();
                        for (int j = reverse.length()-1; j >= 0; j--) {
                            postFix[pfPointer] = Character.toString(reverse.charAt(j));
                            pfPointer++;
                        }
                    }
                    reverse = "";
                    break;
                case '*':
                    if ((buffer != null) && !buffer.equals("")) {
                        postFix[pfPointer] = buffer;
                        buffer = "";
                        pfPointer++;
                    }
                    if (symbols.peek().matches("[/*]")) {
                        postFix[pfPointer] = symbols.pop();
                        pfPointer++;
                        symbols.push("*");
                    }
                    else {
                        symbols.push("*");
                    }
                    break;
                case '/':
                    if ((buffer != null) && !buffer.equals("")) {
                        postFix[pfPointer] = buffer;
                        buffer = "";
                        pfPointer++;
                    }
                    if (symbols.peek().matches("[/*]")) {
                        postFix[pfPointer] = symbols.pop();
                        pfPointer++;
                        symbols.push("/");
                    }
                    else {
                        symbols.push("/");
                    }
                    break;
                case '+':
                    if ((buffer != null) && !buffer.equals("")) {
                        postFix[pfPointer] = buffer;
                        buffer = "";
                        pfPointer++;
                    }
                    if (symbols.peek().matches("[+-/*]")) {
                        postFix[pfPointer] = symbols.pop();
                        pfPointer++;
                        symbols.push("+");
                    }
                    else {
                        symbols.push("+");
                    }
                    break;
                case '-':
                    if ((buffer != null) && !buffer.equals("")) {
                        postFix[pfPointer] = buffer;
                        buffer = "";
                        pfPointer++;
                    }
                    if (symbols.peek().matches("[+-/*]")) {
                        postFix[pfPointer] = symbols.pop();
                        pfPointer++;
                        symbols.push("-");
                    }
                    else {
                        symbols.push("-");
                    }
                    break;
                default:
                    buffer += Character.toString(operation.charAt(i));
                    break;
            }
        }
        postFix = nullRm(postFix);
        return postFixEvaluator(postFix);
    }

    /*This method will process the the postFix array wnd give back the resultDisplay*/
    private String postFixEvaluator(String[] postFix) {
        Stack<Double> evaluator = new Stack<Double>();
        String resultDisplay = "";
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

    /*This will delete any nulls in the array if any*/
    private String[] nullRm(String[] postFix) {
        for (int x = 0; x < postFix.length; x++) {
            if (postFix[x] == null) {
                postFix[x] = "";
            }
        }
        return postFix;
    }
}
