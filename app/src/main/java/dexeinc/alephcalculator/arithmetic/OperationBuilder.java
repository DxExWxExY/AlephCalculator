package dexeinc.alephcalculator.arithmetic;

import java.util.Locale;

/**
 * OperationBuilder Class is responsible for building an this.operation in
 * infix form.
 */
public class OperationBuilder {
    /**
     * The following variables are required to build an expression.
     */
    private static final String DECIMAL_FORMAT = "%,.6f";
    
    private String operation;
    private String result;
    private Boolean dotReset = true;
    private int parenthesisCount = 0;
    private Locale locale;

    public String getOperation() {
        return this.operation;
    }

    public String getResult() {
        return this.result;
    }

    public OperationBuilder(Locale locale) {
        this.operation = "0";
        this.result = "";
        this.locale = locale;
    }

    public OperationBuilder(String operation, String result, Locale locale) {
        this.operation = operation;
        this.result = result;
        this.locale = locale;
    }

    public void numberPressed(int num) {
        /*if the this.operation this.operationDisplay has a 0*/
        if (this.operation.equals("0")) {
            this.operation = String.valueOf(num);
            this.result = "";
        }
        /*if there is a number in the this.operation this.operationDisplay*/
        else {
            this.operation += String.valueOf(num);
            this.result = "";
        }
    }

    public void clearOperation() {
        this.operation = "0";
        this.result = "";
        this.dotReset = true;
        this.parenthesisCount = 0;
    }

    public void insertOperand(Operand operand){
        /*if the resultDisplay is not empty, concatenate symbol*/
        if (!this.result.isEmpty()) {
            /*if the resultDisplay is negative*/
            if (this.result.charAt(0) == '-') {
                this.operation = "(" + this.result + ")" + operand.toString();
                this.result = "";
                this.dotReset = true;
            }
            /*positive number present when adding*/
            else {
                this.operation = this.result + operand.toString();
                this.result = "";
                this.dotReset = true;
            }
        }
    }

    public void insertAddition() {

        if ((this.operation.matches("\\d$") && !this.operation.equals("0")) ||
                this.operation.matches("\\)$")) {
            this.operation += "+";
            this.dotReset = true;
            this.result = "";
        }

        else if (!this.operation.matches("\\d$") &&
                this.operation.charAt(this.operation.length()-1) != ')' &&
                this.operation.charAt(this.operation.length()-1) != '(') {
            this.operation = this.operation.substring(0, this.operation.length() - 1) + "+";
            this.dotReset = true;
            this.result = "";
        }
    }

    public void insertSubtraction() {
        if (!this.result.equals("")) {
            if (this.result.charAt(0) == '-') {
                this.operation = "(" + this.result + ")" + "-";
                this.result = "";
                this.dotReset = true;
                this.result = "";
            }
            else {
                this.operation = this.result + "-";
                this.result = "";
                this.dotReset = true;
            }
        }
        else if (this.operation.equals("0")) {
            this.operation = "(-";
            this.parenthesisCount++;
        }
        else if (Character.isDigit(this.operation.charAt(this.operation.length()-1)) ||
                this.operation.charAt(this.operation.length()-1) == ')' ||
                this.operation.charAt(this.operation.length()-1) == '(') {
            this.operation += "-";
            this.dotReset = true;
            this.result = "";
        }
        else if (!Character.isDigit(this.operation.charAt(this.operation.length()-1)) &&
                this.operation.charAt(this.operation.length()-1) != ')' &&
                this.operation.charAt(this.operation.length()-1) != '(') {
            this.operation = this.operation.substring(0, this.operation.length() - 1) + "-";
            this.dotReset = true;
            this.result = "";
        }
    }

    public void insertMultiplication() {
        /*Checks if resultDisplay this.operationDisplay is populated, then takes the resultDisplay and adds * at the end*/
        if (!this.result.equals("")) {
            if (this.result.charAt(0) == '-') {
                this.operation = "(" + this.result + ")" + "*";
                this.result = "";
                this.dotReset = true;
                this.result = "";
            }
            else {
                this.operation = this.result + "*";
                this.result = "";
                this.dotReset = true;
                this.result = "";
            }
        }
        /*Checks if this.operation last index is a number and that this.operation in not equals to 0
        * or if the last character is a closing parenthesis*/
        else if ((Character.isDigit(this.operation.charAt(this.operation.length()-1)) && !this.operation.equals("0")) || this.operation.charAt(this.operation.length()-1) == ')') {
            this.operation += "*";
            this.dotReset = true;
            this.result = "";
        }
        /*Checks if the last index isn't a number*/
        else if (!Character.isDigit(this.operation.charAt(this.operation.length()-1)) &&
                this.operation.charAt(this.operation.length()-1) != ')' &&
                this.operation.charAt(this.operation.length()-1) != '(') {
            this.operation = this.operation.substring(0, this.operation.length() - 1) + "*";
            this.dotReset = true;
            this.result = "";
        }
    }

    public void insertDivision() {
        if (!this.result.equals("")) {
            if (this.result.charAt(0) == '-') {
                this.operation = "(" + this.result + ")" + "/";
                this.result = "";
                this.dotReset = true;
                this.result = "";
            }
            else {
                this.operation = this.result + "/";
                this.result = "";
                this.dotReset = true;
                this.result = "";
            }
        }
        else if ((Character.isDigit(this.operation.charAt(this.operation.length()-1)) && !this.operation.equals("0")) || this.operation.charAt(this.operation.length()-1) == ')') {
            this.operation += "/";
            this.dotReset = true;
            this.result = "";
        }
        else if (!Character.isDigit(this.operation.charAt(this.operation.length()-1)) &&
                this.operation.charAt(this.operation.length()-1) != ')' &&
                this.operation.charAt(this.operation.length()-1) != '(') {
            this.operation = this.operation.substring(0, this.operation.length() - 1) + "/";
            this.dotReset = true;
            this.result = "";
        }
    }

    public void insertDot() {
        if (this.operation.length() == 1 && this.operation.charAt(0) == '0' && this.dotReset) {
            this.operation = "0.";
            this.dotReset = false;
        }
        else if (this.dotReset && Character.isDigit(this.operation.charAt(this.operation.length()-1))) {
            this.operation += ".";
            this.dotReset = false;
        }
        else if (this.dotReset && !Character.isDigit(this.operation.charAt(this.operation.length()-1))) {
            this.operation += "0.";
            this.dotReset = false;
        }
    }

    public void deleteLast() {
        /*only 1 character left in the string*/
        if (this.operation.length() == 1) {
            this.operation = "0";

            this.result = "";
        }
        /*if this.operation string not empty*/
        else if (!this.operation.isEmpty()) {
            int lastIndex = this.operation.length() - 1;
            /*if last is a dot*/
            if (this.operation.charAt(this.operation.length()-1) == '.') {
                this.operation = this.operation.substring(0, lastIndex);
                this.dotReset = true;
            }
            /*if last is a multiplication parenthesis*/
            else if (this.operation.matches("\\)\\*$")) {
                this.operation = this.operation.substring(0, this.operation.length() - 2);
                this.parenthesisCount--;
            }
            /*if last is an opening parenthesis*/
            else if (this.operation.matches("\\($")) {
                this.operation = this.operation.substring(0, lastIndex);
                this.parenthesisCount--;
            }
            /*if last is an opening parenthesis*/
            else if (this.operation.matches("\\)$")) {
                this.operation = this.operation.substring(0, lastIndex);
                this.parenthesisCount++;
            }
            /*if last is "[0-9*+-/]"*/
            else {
                this.operation = this.operation.substring(0, lastIndex);

            }
        }
    }

    public void insertParenthesis() {
        /*Checks if this.operation is equal to 0 and replaces it with an opening parenthesis*/
        if (this.operation.equals("0")) {
            this.operation = "("; // FIXME
            this.parenthesisCount++;
        }
        /*Checks if the last character isn't a digit and parenthesis count equals 0*/
        else if (this.parenthesisCount == 0  && !this.operation.matches("\\d$")) {
            if (this.operation.matches("\\)$")) {
                this.operation += "*(";
                this.parenthesisCount++;
            }
            else {
                this.operation += "(";
                this.parenthesisCount++;
            }
        }
        /*Checks if the last character is a digit and parenthesis count doesn't equal 0*/
        else if (this.parenthesisCount != 0 && this.operation.matches("\\d$")) {
            this.operation += ")";
            this.parenthesisCount--;
        }
        /*Checks if the las char is a num or parenthesis then adds a *( to the this.operation*/
        else if (this.parenthesisCount == 0 && this.operation.matches("[\\d)]$")) {
            this.operation += "*(";
            this.parenthesisCount++;
        }
        /*Checks if the last char is ) and if the parenthesis count  != equal 0*/
        else if (this.parenthesisCount != 0 && this.operation.matches("\\)$")) {
            this.operation += ")";
            this.parenthesisCount--;
        }
        /*Checks if the last char is an operand and adds an ( */
        else if (this.parenthesisCount != 0 && this.operation.matches("[-+*/]$")) {
            this.operation += "(";
            this.parenthesisCount++;
        }
    }

    public void getPercentage() {
        double evaluationResult = Double.parseDouble(ExpressionEvaluator.evaluate(this.operation)) / 100;
        this.result = String.format(this.locale, DECIMAL_FORMAT, evaluationResult);
    }

    public void getAnswer() {
        if (this.operation.matches("[-+*/]$")) {
            this.operation = this.operation.substring(0, this.operation.length() - 1);
        }
        double evaluationResult = Double.parseDouble(ExpressionEvaluator.evaluate(this.operation));
        this.result = String.format(this.locale, DECIMAL_FORMAT, evaluationResult);
    }
}
