package dexeinc.alephcalculator.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import dexeinc.alephcalculator.Support.HistoryDatabase;
import dexeinc.alephcalculator.Support.OperationBuilder;
import dexeinc.alephcalculator.R;

public class Calculator extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Variables to be used by button events.
     */
    private OperationBuilder expression;
    private TextView operationDisplay;
    private TextView resultDisplay;
    public static HistoryDatabase historyDatabase;

    /**
     * Method to be called on launch.
     * {@inheritDoc}
     * @param savedInstanceState System provided variable.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        historyDatabase = new HistoryDatabase(this);
        initOperation();
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
     * This method detects if the history activity called the calculator with and operation.
     */
    public void initOperation() {
        if (getIntent().hasExtra("hOperation") && getIntent().hasExtra("hResult")) {
            expression = new OperationBuilder(getIntent().getStringExtra("hOperation"), getIntent().getStringExtra("hResult"));
        } else {
            expression = new OperationBuilder();
        }
    }

    /**
     * Initializes navigation bar code.
     */
    public void initNavBar() {
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Initializes buttons and listeners.
     */
    public void initButtons() {
        Button one = findViewById(R.id.button1);
        Button two = findViewById(R.id.button2);
        Button three = findViewById(R.id.button3);
        Button four = findViewById(R.id.button4);
        Button five = findViewById(R.id.button5);
        Button six = findViewById(R.id.button6);
        Button seven = findViewById(R.id.button7);
        Button eight = findViewById(R.id.button8);
        Button nine = findViewById(R.id.button9);
        Button zero = findViewById(R.id.button0);
        Button clear = findViewById(R.id.buttonC);
        Button equals = findViewById(R.id.buttonEquals);
        Button dot = findViewById(R.id.buttonDot);
        Button del = findViewById(R.id.buttonDel);
        Button parenthesis = findViewById(R.id.buttonParenthesis);
        Button percent = findViewById(R.id.buttonPercent);
        Button divide = findViewById(R.id.divide);
        Button sub = findViewById(R.id.subtract);
        Button add = findViewById(R.id.add);
        Button mult = findViewById(R.id.multiply);

        one.setOnClickListener(v -> this.updateOperation(1));

        two.setOnClickListener(v -> this.updateOperation(2));

        three.setOnClickListener(v -> this.updateOperation(3));

        four.setOnClickListener(v -> this.updateOperation(4));

        five.setOnClickListener(v -> this.updateOperation(5));

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
                historyDatabase.addOperation(expression.operation, expression.result);
            } catch (Exception a) {
                Toast.makeText(getApplicationContext(), a.toString(), Toast.LENGTH_LONG).show();
                System.out.println(expression.operation + " " + expression.result);
                a.printStackTrace();
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
        operationDisplay = findViewById(R.id.operationDisplay);
        resultDisplay = findViewById(R.id.resultDisplay);
        operationDisplay.setText(expression.operation);
    }

    /** Updates display on button press*/
    private void updateOperation(int number){
        expression.numberPressed(number);
        operationDisplay.setText(expression.operation);
        resultDisplay.setText(expression.result);
    }

    /**
     * Overridden method to return to home activity.
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
