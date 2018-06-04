package dexeinc.alephcalculator.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import dexeinc.alephcalculator.Evaluation.Operation;
import dexeinc.alephcalculator.R;

public class History extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Linked list used for the history implementation.
     */
    private static LinkedList<Operation> history;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        try {
            readHistory();
        } catch (IOException e) {
            initRecyclerView();
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    /**
     * Reads operations in file to populate history list.
     */
    private void readHistory() throws IOException {
        history = new LinkedList<>();
        InputStream inputStream = getAssets().open("history.txt");
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        String line = new String(buffer);
        String[] operations = line.split(",");
        for (String operation: operations) {
            String[] expression = operation.split("=");
            history.add(new Operation(expression[0], expression[1]));
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.list_viewer);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(history, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
            case R.id.nav_calc:
                Intent calc = new Intent(History.this, Calculator.class);
                startActivity(calc);
                finish();
                return true;
            case R.id.nav_about:
                Intent about = new Intent(History.this, About.class);
                startActivity(about);
                finish();
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
            case R.id.delete_history:
                // TODO: 6/1/2018 Delete History
                break;
            case R.id.settings:
                Toast.makeText(this,"Coming Soon!", Toast.LENGTH_LONG).show();
                return true;
        }
        return true;
    }
}
