package dexeinc.alephcalculator.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileReader;
import java.util.EmptyStackException;
import java.util.LinkedList;

import dexeinc.alephcalculator.Evaluation.Operation;
import dexeinc.alephcalculator.R;

public class Calculator extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Variables to be used by button events.
     */
    private Operation expression;
    private TextView operationDisplay;
    private TextView resultDisplay;

    /**
     * Linked list used for the history implementation.
     */
    private static LinkedList<Operation> history = new LinkedList<>();

    /**
     * Method to be called on launch.
     * {@inheritDoc}
     * @param savedInstanceState System provided variable.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expression = new Operation();
        initNavBar();
        initDisplay();
        initButtons();
    }

    /**
     * {@inheritDoc}
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    /**
     * Reads operations in file to populate history list.
     */
    private void readHistory() {
        //FileReader file = new FileReader();
    }

    /**
     * Initializes navigation bar code.
     */
    public void initNavBar() {
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Initializes buttons and listeners.
     */
    public void initButtons() {
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
        Button equals = (Button) findViewById(R.id.buttonEquals);
        Button dot = (Button) findViewById(R.id.buttonDot);
        Button del = (Button) findViewById(R.id.buttonDel);
        Button parenthesis = (Button) findViewById(R.id.buttonParenthesis);
        Button percent = (Button) findViewById(R.id.buttonPercent);
        Button divide = (Button) findViewById(R.id.divide);
        Button sub = (Button) findViewById(R.id.substract);
        Button add = (Button) findViewById(R.id.add);
        Button mult = (Button) findViewById(R.id.multiply);

        one.setOnClickListener(v -> {
            expression.numberPressed(1);
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        two.setOnClickListener(v -> {
            expression.numberPressed(2);
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        three.setOnClickListener(v -> {
            expression.numberPressed(3);
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        four.setOnClickListener(v -> {
            expression.numberPressed(4);
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        five.setOnClickListener(v -> {
            expression.numberPressed(5);
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        six.setOnClickListener(v -> {
            expression.numberPressed(6);
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        seven.setOnClickListener(v -> {
            expression.numberPressed(7);
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        eight.setOnClickListener(v -> {
            expression.numberPressed(8);
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        nine.setOnClickListener(v -> {
            expression.numberPressed(9);
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        zero.setOnClickListener(v -> {
            expression.numberPressed(0);
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        dot.setOnClickListener(v -> {
            expression.insertDot();
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        del.setOnClickListener(v -> {
            expression.deleteLast();
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        clear.setOnClickListener(v -> {
            expression.deleteOperation();
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        percent.setOnClickListener(v -> {
            expression.getPercentage();
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        parenthesis.setOnClickListener(v -> {
            expression.insertParenthesis();
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        equals.setOnClickListener(v -> {
            try {
                expression.getAnswer();
                operationDisplay.setText(expression.operation);
                resultDisplay.setText(expression.result);
            } catch (Exception a) {
                Toast.makeText(getApplicationContext(), a.toString(), Toast.LENGTH_LONG).show();
            }
        });

        divide.setOnClickListener(v -> {
            expression.insertDivision();
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        mult.setOnClickListener(v -> {
            expression.insertMultiplication();
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        sub.setOnClickListener(v -> {
            expression.insertSubtraction();
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });

        add.setOnClickListener(v -> {
            expression.insertAddition();
            operationDisplay.setText(expression.operation);
            resultDisplay.setText(expression.result);
        });
    }

    /**
     * Initializes text viewers.
     */
    public void initDisplay() {
        operationDisplay = (TextView) findViewById(R.id.operationDisplay);
        resultDisplay = (TextView) findViewById(R.id.resultDisplay);
        operationDisplay.setText(expression.operation);
    }

    /**
     * Overridden method to return to home activity.
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * {@inheritDoc}
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"DxExWxExY@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Aleph Calculator");
                startActivity(email);
                return true;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * {@inheritDoc}
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.history:
                Intent about = new Intent(Calculator.this, History.class);
                startActivity(about);
                return true;
            case R.id.settings:
                Toast.makeText(this,"Coming Soon!", Toast.LENGTH_LONG).show();
                return true;
        }
        return true;
    }
}
