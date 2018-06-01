package dexeinc.alephcalculator.Evaluation;

/**
 * Operation Class is responsible for building an operation in
 * infix form.
 */
public class Operation {
    /**
     * The following variables are required to perform any operation.
     */
    public String operation = "0";
    public String result = "";
    private Boolean dotReset = true;
    private int parenthesisCount = 0;

    public Operation() {}

    public Operation(String operation, String result) {
        this.operation = operation;
        this.result = result;
    }

    public void numberPressed(int num) {
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

    public void deleteOperation() {
        operation = "0";
        result = "";
        dotReset = true;
        parenthesisCount = 0;
    }

    public void insertAddition() {
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

    public void insertSubtraction() {
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

    public void insertMultiplication() {
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

    public void insertDivision() {
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

    public void insertDot() {
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

    public void deleteLast() {
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

    public void insertParenthesis() {
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

    public void getPercentage() {
        double percent = Double.parseDouble(PostFix.toPostfix(operation)) / 100;
        result = String.valueOf(percent);
    }

    public void getAnswer() {
        if (Character.toString(operation.charAt(operation.length() - 1)).matches("[-+*/]")) {
            operation = operation.substring(0, operation.length() - 1);
        }
        result = PostFix.toPostfix(operation);
    }
}
