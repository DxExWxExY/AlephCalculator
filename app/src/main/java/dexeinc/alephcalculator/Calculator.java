package dexeinc.alephcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.SoundEffectConstants;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

public class Calculator extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String digits = "0";
    String resultTemp = "";
    Boolean dotReset = true;
    Boolean calculationReset = false;
    int parenthesisCount = 0;
    TextView display;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        /*Nav Bar Code*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Buttons Code*/
        Button one = (Button) findViewById(R.id.button1);
        Button two = (Button) findViewById(R.id.button2);
        Button three = (Button) findViewById(R.id.button3);
        Button four = (Button) findViewById(R.id.button4);
        Button five = (Button) findViewById(R.id.button5);
        Button six = (Button) findViewById(R.id.button6);
        Button seven = (Button) findViewById(R.id.button7);
        Button eight = (Button) findViewById(R.id.button8);
        Button nine = (Button) findViewById(R.id.button9);
        Button zero = (Button) findViewById(R.id.button0);
        Button clear = (Button) findViewById(R.id.buttonC);
        final Button equals = (Button) findViewById(R.id.buttonEquals);
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

        eight.setOnClickListener(new View.OnClickListener() {
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
                ans();
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
    /*Nav Bar Code*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_about:
                Intent about = new Intent(Calculator.this, About.class);
                startActivity(about);
                return true;
            case R.id.nav_other:
                Intent store = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=DxExWxExY&c=apps&hl=en"));
                startActivity(store);
                return true;
            case R.id.nav_contact:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("message/rfc822");
                email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"DxExWxExY@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Aleph Calculator");
                startActivity(email);
                return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*Buttons Logic*/
    void numberPressed(int num) {
        /*if the operation display has a 0*/
        if (digits.equals("0")) {
            digits = String.valueOf(num);
            display.setText(digits);
            result.setText("");
        }
        /*clears operation after number is pressed*/
        else if (calculationReset) {
            digits = String.valueOf(num);
            display.setText(digits);
            result.setText("");
            calculationReset = false;
        }
        /*if there is a number in the operation display*/
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
        calculationReset = false;
    }

    void add() {
        /*if the result is not empty, concatenate symbol*/
        if (!result.getText().equals("")) {
            /*if the result is negative*/
            if (result.getText().charAt(0) == '-') {
                digits = "(" + result.getText() + ")" + "+";
                result.setText("");
                display.setText(digits);
                dotReset = true;
                result.setText("");
                calculationReset = false;
            }
            /*positive number present when adding*/
            else {
                digits = result.getText() + "+";
                result.setText("");
                display.setText(digits);
                dotReset = true;
                result.setText("");
                calculationReset = false;
            }
        }
        /*if there is a number or closing parenthesis, concatenate symbol*/
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
            if (result.getText().charAt(0) == '-') {
                digits = "(" + result.getText() + ")" + "-";
                result.setText("");
                display.setText(digits);
                dotReset = true;
                result.setText("");
                calculationReset = false;
            }
            else {
                digits = result.getText() + "-";
                result.setText("");
                display.setText(digits);
                dotReset = true;
                result.setText("");
                calculationReset = false;
            }
        }
        else if (digits.equals("0")) {
            digits = "(-";
            parenthesisCount++;
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
        /*Checks if result display is populated, then takes the result and adds * at the end*/
        if (!result.getText().equals("")) {
            if (result.getText().charAt(0) == '-') {
                digits = "(" + result.getText() + ")" + "*";
                result.setText("");
                display.setText(digits);
                dotReset = true;
                result.setText("");
                calculationReset = false;
            }
            else {
                digits = result.getText() + "*";
                result.setText("");
                display.setText(digits);
                dotReset = true;
                result.setText("");
                calculationReset = false;
            }
        }
        /*Checks if digits last index is a number and that digits in not equals to 0
        * or if the last character is a closing parenthesis*/
        else if ((Character.isDigit(digits.charAt(digits.length()-1)) && !digits.equals("0")) || digits.charAt(digits.length()-1) == ')') {
            digits += "*";
            display.setText(digits);
            dotReset = true;
            result.setText("");
        }
        /*Checks if the last index isn't a number*/
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
            if (result.getText().charAt(0) == '-') {
                digits = "(" + result.getText() + ")" + "/";
                result.setText("");
                display.setText(digits);
                dotReset = true;
                result.setText("");
                calculationReset = false;
            }
            else {
                digits = result.getText() + "/";
                result.setText("");
                display.setText(digits);
                dotReset = true;
                result.setText("");
                calculationReset = false;
            }
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
        calculationReset = false;
        /*only 1 character left in the string*/
        if (digits.length() == 1) {
            digits = "0";
            display.setText(digits);
            result.setText("");
        }
        /*if operation string not empty*/
        else if (!digits.equals("")) {
            /*if last is a dot*/
            if (digits.charAt(digits.length()-1) == '.') {
                digits = digits.substring(0, digits.length() - 1);
                display.setText(digits);
                dotReset = true;
            }
            /*if last is a multiplication parenthesis*/
            else if (digits.charAt(digits.length()-1) == '(' && digits.charAt(digits.length()-2) == '*') {
                digits = digits.substring(0, digits.length() - 2);
                display.setText(digits);
                parenthesisCount--;
            }
            /*if last is an opening parenthesis*/
            else if (digits.charAt(digits.length()-1) == '(') {
                digits = digits.substring(0, digits.length() - 1);
                display.setText(digits);
                parenthesisCount--;
            }
            /*if last is an opening parenthesis*/
            else if (digits.charAt(digits.length()-1) == ')') {
                digits = digits.substring(0, digits.length() - 1);
                display.setText(digits);
                parenthesisCount++;
            }
            /*if last is "[0-9*+-/]"*/
            else {
                digits = digits.substring(0, digits.length() - 1);
                display.setText(digits);
            }
        }
    }

    void parenthesis() {
        /*Checks if digits is equal to 0 and replaces it with an opening parenthesis*/
        if (digits.equals("0")) {
            digits = "(";
            display.setText(digits);
            parenthesisCount++;
        }
        /*Checks if the last character isn't a digit and parenthesis count equals 0*/
        else if (parenthesisCount == 0  && !Character.isDigit(digits.charAt(digits.length()-1))) {
            if (digits.charAt(digits.length()-1) == ')') {
                digits += "*(";
                display.setText(digits);
                parenthesisCount++;
            }
            else {
                digits += "(";
                display.setText(digits);
                parenthesisCount++;
            }
        }
        /*Checks if the last character is a digit and parenthesis count doesn't equal 0*/
        else if (parenthesisCount != 0 && Character.isDigit(digits.charAt(digits.length()-1))) {
            digits += ")";
            display.setText(digits);
            parenthesisCount--;
        }
        /*Checks if the las char is a num or parenthesis then adds a *( to the operation*/
        else if (parenthesisCount == 0 &&
                (Character.isDigit(digits.charAt(digits.length()-1)) || digits.charAt(digits.length()-1) == ')')) {
            digits += "*(";
            display.setText(digits);
            parenthesisCount++;
        }
        /*Checks if the last char is ) and if the parenthesis count  != equal 0*/
        else if (parenthesisCount != 0 && digits.charAt(digits.length()-1) == ')') {
            digits += ")";
            display.setText(digits);
            parenthesisCount--;
        }
        /*Checks if the last char is an operand and adds an ( */
        else if (parenthesisCount != 0 && !Character.isDigit(digits.charAt(digits.length()-1))) {
            digits += "(";
            display.setText(digits);
            parenthesisCount++;
        }
    }

    void percentage() {
        resultTemp = postFixProcessor(digits);
        double percent = Double.parseDouble(resultTemp) / 100;
        result.setText(String.valueOf(percent));
    }

    void ans() {
        try {
            result.setText(postFixProcessor("(" + digits + ")"));
            calculationReset = true;

        }
        catch (EmptyStackException a) {
            String e1 = "Can't Process Negative Number";
            Toast.makeText(getApplicationContext(), e1, Toast.LENGTH_LONG).show();
        }
    }

    /*Calculator Logic*/
    /*This will determine the size of the postFix array created in postFixProcessor method*/
    int postFixArraySize(String operation) {
        int size = 0;
        for (int i = 0; i < operation.length(); i++) {
            if (Character.toString(operation.charAt(i)).matches("[+-/*]")) {
                size++;
            }
        }
        return (size * 3) + 1;
    }

    /*The method will recives the operation and convert it to postfix*/
    String postFixProcessor(String operation) {
        Stack<String> symbols = new Stack<>(); //stack for symbols
        String buffer = ""; //buffer for multi digit numbers
        String[] postFix = new String[postFixArraySize(operation)]; //bufer for postfix operation
        int pfPointer = 0;
        String reverse = "";
        /*check for any negative numbers*/
        operation = operation.replace("(-", "(0-");
		/*This for loop will iterate through the string and determine what to do depending
		/*if charAt(i) is an operation symbol or a digit of a multi-dgit expresion*/
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

    /*This method will process the the postFix array wnd give back the result*/
    String postFixEvaluator(String[] postFix) {
        Stack<Double> evaluator = new Stack<Double>();
        String result = "";
        for (int i = 0; i < postFix.length; i++) {
			/*If the element at i is a number*/
            if (!postFix[i].matches("[+-/*]") && !postFix[i].equals("")) {
                evaluator.push(Double.parseDouble(postFix[i]));
            }
			/*If the element at i is an operand*/
            else if (postFix[i].matches("[+-/*]")) {
                double b = evaluator.pop();
                double a = evaluator.pop();
                switch (postFix[i]) {
                    case "*":
                        evaluator.push(a*b);
                        break;
                    case "/":
                        evaluator.push(a/b);
                        break;
                    case "+":
                        evaluator.push(a+b);
                        break;
                    case "-":
                        evaluator.push(a-b);
                        break;
                }
            }
			/*if the end of the array is reached*/
            else {
                break;
            }
        }
        if (evaluator.peek()%1 == 0) {
            result = String.valueOf(evaluator.peek().intValue());
        }
        return result;
    }

    /*This will delete any nulls in the array if any*/
    String[] nullRm(String[] postFix) {
        for (int x = 0; x < postFix.length; x++) {
            if (postFix[x] == null) {
                postFix[x] = "";
            }
        }
        return postFix;
    }
}
