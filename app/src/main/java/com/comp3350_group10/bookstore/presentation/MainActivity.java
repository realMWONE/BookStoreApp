package com.comp3350_group10.bookstore.presentation;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.business.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.business.UI_Handler.IButtonFunctions;
import com.comp3350_group10.bookstore.business.UI_Handler.TrendingPageFunctions;

public class MainActivity extends AppCompatActivity
{
    private IButtonFunctions uIButtonFunctions;
    private TableLayout bookListTable;
    private EditText searchBar;
    private Spinner dropdown;
    private Button sortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Main.setDBPath("./assets/db/book.script");
        Service.setupBookDatabase();
        uIButtonFunctions = new ButtonFunctions();
        searchBar = findViewById(R.id.searchBar);
        bookListTable = findViewById(R.id.bookListTable);
        dropdown = findViewById(R.id.dropdown);
        sortButton = findViewById(R.id.sortingButton);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        SetSearchListener(getBaseContext(), this);
        try {
            FillTrendingTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FillDropdownList();
    }

    private void FillDropdownList() {
        String[] items = new String[] {"By Title", "By Author", "By Genre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void FillTrendingTable() throws ClassNotFoundException {
        TrendingPageFunctions.FillTrendingPage(bookListTable, this);
    }

    private void SetSearchListener(Context context, MainActivity main) {
        searchBar.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    uIButtonFunctions.SearchButtonPressed(s.toString(), bookListTable, context, main, sortButton.getText().toString(), (String)dropdown.getSelectedItem());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onLoginButtonClicked(MenuItem item) {
        uIButtonFunctions.SwitchToLoginActivity(this, getBaseContext());
    }

    public void onLogoutButtonClicked(MenuItem item)
    {
        uIButtonFunctions.LogoutButtonPressed();
    }

    public void userSettingButtonClicked(MenuItem item){ uIButtonFunctions.UserSettingButtonPressed(getBaseContext(), this); }

    public void onSortButtonClicked(View v) {
        Button b = (Button)v;
        if (b.getText().toString().equals("DESC"))
            b.setText("ASC");
        else b.setText("DESC");
    }
}