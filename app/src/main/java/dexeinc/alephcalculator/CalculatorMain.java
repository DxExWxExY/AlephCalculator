package dexeinc.alephcalculator;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorMain extends AppCompatActivity {

    String digits = "0";
    Boolean dotReset = true;
    int parenthesisCount = 0;
    TextView display;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_main);

        Button one = (Button) findViewById(R.id.button1);
        Button two = (Button) findViewById(R.id.button2);
        Button three = (Button) findViewById(R.id.button3);
        Button four = (Button) findViewById(R.id.button4);
        Button five = (Button) findViewById(R.id.button5);
        Button six = (Button) findViewById(R.id.button6);
        Button seven = (Button) findViewById(R.id.button7);
        Button eigth = (Button) findViewById(R.id.button8);
        Button nine = (Button) findViewById(R.id.button9);
        Button zero = (Button) findViewById(R.id.button0);
        Button clear = (Button) findViewById(R.id.buttonC);
        Button equals = (Button) findViewById(R.id.buttonEquals);
        Button dot = (Button) findViewById(R.id.buttonDot);
        Button del = (Button) findViewById(R.id.buttonDel);
        Button parenthesis = (Button) findViewById(R.id.buttonParenthesis);
        Button percent = (Button) findViewById(R.id.buttonPercent);
        Button divide = (Button) findViewById(R.id.divide);
        Button sub = (Button) findViewById(R.id.substract);
        Button add = (Button) findViewById(R.id.add);
        Button mult = (Button) findViewById(R.id.multiply);

        display = (TextView) findViewById(R.id.display);
        result = (TextView) findViewById(R.id.oper);

        display.setText(digits);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(1);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(2);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(3);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(4);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(5);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(6);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(7);
            }
        });

        eigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(8);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(9);
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(0);
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dot();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDisplay();
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                percentage();
            }
        });

        parenthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parenthesis();
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    stringProcessor(digits);
                }
                catch (NumberFormatException a) {
                    String e1 = "App Cannot Process Operation Yet";
                    Toast.makeText(getApplicationContext(), e1, Toast.LENGTH_LONG).show();
                }
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                div();
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mult();
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subs();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.about:
//                Intent aboutIntent = new Intent(CalculatorMain.this, about.class);
//                startActivity(aboutIntent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    void numberPressed(int num) {
        if (digits.equals("0")) {
            digits = String.valueOf(num);
            display.setText(digits);
            result.setText("");
        }
        else {
            digits += String.valueOf(num);
            display.setText(digits);
            result.setText("");
        }
    }

    void clearDisplay() {
        digits = "0";
        display.setText(digits);
        result.setText("");
        dotReset = true;
        parenthesisCount = 0;
    }

    void add() {
        if (!result.getText().equals("")) {
            digits = result.getText() + "+";
            result.setText("");
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
        else if (Character.isDigit(digits.charAt(digits.length()-1)) || digits.charAt(digits.length()-1) == ')') {
            digits += "+";
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
        else if (!Character.isDigit(digits.charAt(digits.length()-1)) &&
                digits.charAt(digits.length()-1) != ')' &&
                digits.charAt(digits.length()-1) != '(') {
            digits = digits.substring(0, digits.length() - 1) + "+";
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
    }

    void subs() {
        if (!result.getText().equals("")) {
            digits = result.getText() + "-";
            result.setText("");
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
        else if (digits.equals("0")) {
            digits = "-";
            display.setText(digits);
        }
        else if (Character.isDigit(digits.charAt(digits.length()-1)) ||
                digits.charAt(digits.length()-1) == ')' ||
                digits.charAt(digits.length()-1) == '(') {
            digits += "-";
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
        else if (!Character.isDigit(digits.charAt(digits.length()-1)) &&
                digits.charAt(digits.length()-1) != ')' &&
                digits.charAt(digits.length()-1) != '(') {
            digits = digits.substring(0, digits.length() - 1) + "-";
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
    }

    void mult() {
        if (!result.getText().equals("")) {
            digits = result.getText() + "*";
            result.setText("");
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
        else if (Character.isDigit(digits.charAt(digits.length()-1)) || digits.charAt(digits.length()-1) == ')') {
            digits += "*";
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
        else if (!Character.isDigit(digits.charAt(digits.length()-1)) &&
                digits.charAt(digits.length()-1) != ')' &&
                digits.charAt(digits.length()-1) != '(') {
            digits = digits.substring(0, digits.length() - 1) + "*";
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
    }

    void div() {
        if (!result.getText().equals("")) {
            digits = result.getText() + "/";
            result.setText("");
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
        else if (Character.isDigit(digits.charAt(digits.length()-1)) || digits.charAt(digits.length()-1) == ')') {
            digits += "/";
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
        else if (!Character.isDigit(digits.charAt(digits.length()-1)) &&
                digits.charAt(digits.length()-1) != ')' &&
                digits.charAt(digits.length()-1) != '(') {
            digits = digits.substring(0, digits.length() - 1) + "/";
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
    }

    void dot() {
        if (digits.length() == 1 && digits.charAt(0) == '0' && dotReset) {
            digits = "0.";
            display.setText(digits);
            dotReset = false;
        }
        else if (dotReset && Character.isDigit(digits.charAt(digits.length()-1))) {
            digits += ".";
            display.setText(digits);
            dotReset = false;
        }
        else if (dotReset && !Character.isDigit(digits.charAt(digits.length()-1))) {
            digits += "0.";
            display.setText(digits);
            dotReset = false;
        }
    }

    void del() {
        if (digits.length() == 1) {
            digits = "0";
            display.setText(digits);
            result.setText("");
        }
        else if (!digits.equals("")) {
            if (digits.charAt(digits.length()-1) == '.') {
                digits = digits.substring(0, digits.length() - 1);
                display.setText(digits);
                dotReset = true;
            }
            else if (digits.charAt(digits.length()-1) == '(' && digits.charAt(digits.length()-2) == '*') {
                digits = digits.substring(0, digits.length() - 2);
                display.setText(digits);
                parenthesisCount--;
            }
            else if (digits.charAt(digits.length()-1) == '(') {
                digits = digits.substring(0, digits.length() - 1);
                display.setText(digits);
                parenthesisCount--;
            }
            else if (digits.charAt(digits.length()-1) == ')') {
                digits = digits.substring(0, digits.length() - 1);
                display.setText(digits);
                parenthesisCount++;
            }
            else {
                digits = digits.substring(0, digits.length() - 1);
                display.setText(digits);
            }
        }
    }

    void parenthesis() {
        if (digits.equals("0")) {
            digits = "(";
            display.setText(digits);
            parenthesisCount++;
        }
        else if (parenthesisCount == 0  && !Character.isDigit(digits.charAt(digits.length()-1))) {
            digits += "(";
            display.setText(digits);
            parenthesisCount++;
        }
        else if (parenthesisCount != 0 && Character.isDigit(digits.charAt(digits.length()-1))) {
            digits += ")";
            display.setText(digits);
            parenthesisCount--;
        }
        else if (parenthesisCount == 0 &&
                (Character.isDigit(digits.charAt(digits.length()-1)) || digits.charAt(digits.length()-1) == ')')) {
            digits += "*(";
            display.setText(digits);
            parenthesisCount++;
        }
        else if (parenthesisCount != 0 && digits.charAt(digits.length()-1) == ')') {
            digits += ")";
            display.setText(digits);
            parenthesisCount--;
        }
        else if (parenthesisCount != 0 && !Character.isDigit(digits.charAt(digits.length()-1))) {
            digits += "(";
            display.setText(digits);
            parenthesisCount++;
        }
    }

    void percentage() {
        //calls result and divides result by 100
    }

    /*******************************/
    //   The following block has   //
    //   methods required by the   //
    //   result() method for the   //
    //   processing of operation   //
    /******************************/

    public void stringProcessor(String operation) {
        operation = "(" + operation + ")";
        while (hasParenthesis(operation)) {
            int openParenthesisIndex = 0;
            int closedParenthesisIndex = operation.length() - 1;
            String subOperation = "";
            String replacedOperation = "";
            String calculation = "";
            for (int i = 0; i < operation.length(); i++) {
                if (operation.charAt(i) == '(') {
                    openParenthesisIndex = i;
                }
                if (operation.charAt(i) == ')') {
                    closedParenthesisIndex = i;
                    break;
                }
            }
            subOperation = operation.substring(openParenthesisIndex, closedParenthesisIndex + 1);
            calculation = operationCalculator(subOperation);
            replacedOperation = operation.replace(subOperation, calculation);
            operation = replacedOperation;
        }
        result.setText(operation);
    }

    public String operationCalculator(String operation) {
        try {
            if (!Character.toString(operation.charAt(operation.length()-1)).matches("[0-9()]")) {
                digits = operation.substring(0, digits.length()-1);
                display.setText(digits);
            }
            if (Double.parseDouble(operation) % 1 == 0) {
                result.setText(String.valueOf(operation));
            }
            else {
                result.setText(String.valueOf(operation));
            }
        }
        catch (NumberFormatException a) {
            //NEW ALGORITHM'S PLACE
            boolean isNegative = false;
            if (operation.charAt(0) == '(' && operation.charAt(operation.length()-1) == ')') {
                operation = operation.substring(1, operation.length()-1);
            }
            if (operation.charAt(0) == '-') {
                operation = operation.substring(1, operation.length());
                isNegative = true;
            }
            //mult or div finder
            while(hasMultOrDiv(operation)) {
                boolean muliplication = false;
                boolean division = false;
                String subOperation = "";
                Double result = 0.0;
                int operationIndex = 0;
                int startIndex = 0;
                int endIndex = operation.length()-1;
                for (int i = 0; i < operation.length(); i++) {
                    if (operation.charAt(i) == '*') {
                        muliplication = true;
                        operationIndex = i;
                        break;
                    }
                    if (operation.charAt(i) == '/') {
                        division = true;
                        operationIndex = i;
                        break;
                    }
                }
                startIndex = operationIndex;
                endIndex = operationIndex;
                while (startIndex-1 >= 0) {
                    if (Character.toString(operation.charAt(startIndex-1)).matches("[0-9.-]")) {
                        startIndex--;
                    }
                    else if (!Character.toString(operation.charAt(startIndex-1)).matches("[0-9.-]")) {
                        break;
                    }
                }
                while (endIndex+1 < operation.length()) {
                    if (Character.toString(operation.charAt(endIndex+1)).matches("[0-9.-]")) {
                        endIndex++;
                    }
                    else if (!Character.toString(operation.charAt(endIndex+1)).matches("[0-9.-]")) {
                        break;
                    }
                }
                subOperation = operation.substring(startIndex, endIndex+1);
                if (muliplication) {
                    String[] calculation = subOperation.split("\\*");
                    result = Double.parseDouble(calculation[0]) * Double.parseDouble(calculation[1]);
                    operation = operation.replace(subOperation, String.valueOf(result));
                }
                if (division) {
                    String[] calculation = subOperation.split("\\/");
                    result = Double.parseDouble(calculation[0]) / Double.parseDouble(calculation[1]);
                    operation = operation.replace(subOperation, String.valueOf(result));
                }
            }
            //add and sub finder
            while(hasAddOrSub(operation)) {
                boolean addition = false;
                boolean substraction = false;
                String subOperation = "";
                Double result = 0.0;
                int operationIndex = 0;
                int startIndex = 0;
                int endIndex = operation.length()-1;
                for (int i = 0; i < operation.length(); i++) {
                    if (operation.charAt(i) == '+') {
                        addition = true;
                        operationIndex = i;
                        break;
                    }
                    if (operation.charAt(i) == '-') {
                        substraction = true;
                        operationIndex = i;
                        break;
                    }
                }
                startIndex = operationIndex;
                endIndex = operationIndex;
                while (startIndex-1 >= 0) {
                    if (Character.toString(operation.charAt(startIndex-1)).matches("[0-9.]")) {
                        startIndex--;
                    }
                    else if (!Character.toString(operation.charAt(startIndex-1)).matches("[0-9.]")) {
                        break;
                    }
                }
                while (endIndex+1 < operation.length()) {
                    if (Character.toString(operation.charAt(endIndex+1)).matches("[0-9.]")) {
                        endIndex++;
                    }
                    else if (!Character.toString(operation.charAt(endIndex+1)).matches("[0-9.]")) {
                        break;
                    }
                }
                subOperation = operation.substring(startIndex, endIndex+1);
                if (isNegative) {
                    subOperation = "0" + subOperation;
                    isNegative = false;
                }
                if (addition) {
                    System.out.print("\nADD LOOP " + subOperation);
                    String[] calculation = subOperation.split("\\+");
                    if (isNegative) {
                        result = -Double.parseDouble(calculation[0]) + Double.parseDouble(calculation[1]);
                        operation = operation.replace(subOperation, String.valueOf(result));
                        isNegative = false;
                    }
                    else {
                        result = Double.parseDouble(calculation[0]) + Double.parseDouble(calculation[1]);
                        operation = operation.replace(subOperation, String.valueOf(result));
                    }
                }
                if (substraction) {
                    System.out.print("\nSUB LOOP");
                    String[] calculation = subOperation.split("\\-");
                    if (isNegative) {
                        result = -Double.parseDouble(calculation[0]) - Double.parseDouble(calculation[1]);
                        operation = operation.replace(subOperation, String.valueOf(result));
                        isNegative = false;
                    }
                    else {
                        result = Double.parseDouble(calculation[0]) - Double.parseDouble(calculation[1]);
                        operation = operation.replace(subOperation, String.valueOf(result));
                    }
                }
                if (result < 0) {
                    break;
                }
            }
            if (isNegative) {
                if (Double.parseDouble(operation)%1 == 0) {
                    isNegative = false;
                    digits = String.valueOf((int) -Double.parseDouble(operation));
                }
                else {
                    isNegative = false;
                    digits = "-" + operation;
                }
            }
            else {
                if (Double.parseDouble(operation)%1 == 0) {
                    digits = String.valueOf((int) Double.parseDouble(operation));
                }
                else {
                    digits = operation;
                }
            }
        }
        return digits;
    }

    public boolean hasParenthesis(String operation) {
        for (int i = 0; i < operation.length(); i++) {
            if (operation.charAt(i) == '(') {
                return true;
            }
        }
        return false;
    }

    public boolean hasMultOrDiv(String operation) {
        for (int i = 0; i < operation.length(); i++) {
            if (operation.charAt(i) == '*' || operation.charAt(i) == '/') {
                return true;
            }
        }
        return false;
    }

    public boolean hasAddOrSub(String operation) {
        for (int i = 0; i < operation.length(); i++) {
            // if (i > 0 && operation.charAt(i) == '-') {
            // 	if (Character.toString(operation.charAt(i-1)).matches("[\\/\\*\\-\\+]")) {
            // 	 	return false;
            // 	}
            // }
            if (operation.charAt(i) == '+' || operation.charAt(i) == '-') {
                return true;
            }
        }
        return false;
    }
}
