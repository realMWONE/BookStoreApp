package com.comp3350_group10.bookstore;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TableLayout;

import com.comp3350_group10.bookstore.logic.UI_Handler.ButtonFunctions;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    private ButtonFunctions uIButtonFunctions;
    private TableLayout bookListTable;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        uIButtonFunctions = new ButtonFunctions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        searchBar = findViewById(R.id.searchBar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        bookListTable = findViewById(R.id.bookListTable);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();

        SetSearchListener(getBaseContext(), this);
    }

    private void SetSearchListener(Context context, MainActivity main) {
        searchBar.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TextView myOutputBox = (TextView) findViewById(R.id.myOutputBox);
                //myOutputBox.setText(s);
                uIButtonFunctions.SearchButtonPressed(s.toString(), bookListTable, context, main);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onLoginButtonClicked(MenuItem item)
    {
        uIButtonFunctions.LoginButtonPressed();
    }

    public void onLogoutButtonClicked(MenuItem item)
    {
        uIButtonFunctions.LogoutButtonPressed();
    }
}