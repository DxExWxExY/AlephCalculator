package dexeinc.alephcalculator;

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
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EmptyStackException;

public class Calculator extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Operation userOperation = new Operation();
    TextView operationDisplay;
    TextView resultDisplay;


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
        Button equals = (Button) findViewById(R.id.buttonEquals);
        Button dot = (Button) findViewById(R.id.buttonDot);
        Button del = (Button) findViewById(R.id.buttonDel);
        Button parenthesis = (Button) findViewById(R.id.buttonParenthesis);
        Button percent = (Button) findViewById(R.id.buttonPercent);
        Button divide = (Button) findViewById(R.id.divide);
        Button sub = (Button) findViewById(R.id.substract);
        Button add = (Button) findViewById(R.id.add);
        Button mult = (Button) findViewById(R.id.multiply);

        operationDisplay = (TextView) findViewById(R.id.operationDisplay);
        resultDisplay = (TextView) findViewById(R.id.resultDisplay);

        operationDisplay.setText(userOperation.operation);

        one.setOnClickListener(v -> {
            userOperation.numberPressed(1);
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        two.setOnClickListener(v -> {
            userOperation.numberPressed(2);
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        three.setOnClickListener(v -> {
            userOperation.numberPressed(3);
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        four.setOnClickListener(v -> {
            userOperation.numberPressed(4);
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        five.setOnClickListener(v -> {
            userOperation.numberPressed(5);
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        six.setOnClickListener(v -> {
            userOperation.numberPressed(6);
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        seven.setOnClickListener(v -> {
            userOperation.numberPressed(7);
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        eight.setOnClickListener(v -> {
            userOperation.numberPressed(8);
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        nine.setOnClickListener(v -> {
            userOperation.numberPressed(9);
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        zero.setOnClickListener(v -> {
            userOperation.numberPressed(0);
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        dot.setOnClickListener(v -> {
            userOperation.insertDot();
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        del.setOnClickListener(v -> {
            userOperation.deleteLast();
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        clear.setOnClickListener(v -> {
            userOperation.deleteOperation();
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        percent.setOnClickListener(v -> {
            userOperation.getPercentage();
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        parenthesis.setOnClickListener(v -> {
            userOperation.insertParenthesis();
            operationDisplay.setText(userOperation.operation);
            resultDisplay.setText(userOperation.result);
        });

        equals.setOnClickListener(v -> {
            try {
                userOperation.getAnswer();
                operationDisplay.setText(userOperation.operation);
                resultDisplay.setText(userOperation.result);
            }
            catch (EmptyStackException a) {
                String e1 = "Incomplete Operation";
                Toast.makeText(getApplicationContext(), e1, Toast.LENGTH_LONG).show();
            }
        });

        divide.setOnClickListener(v -> userOperation.insertDivision());

        mult.setOnClickListener(v -> userOperation.insertMultiplication());

        sub.setOnClickListener(v -> userOperation.insertSubtraction());

        add.setOnClickListener(v -> userOperation.insertAddition());
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
                email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"DxExWxExY@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Aleph Calculator");
                startActivity(email);
                return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
