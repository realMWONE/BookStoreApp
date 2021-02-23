package com.comp3350_group10.bookstore;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.comp3350_group10.bookstore.logic.Data_Handler.DataHandler;
import com.comp3350_group10.bookstore.logic.UI_Handler.UIButtonFunctions;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    private DataHandler DataHandler;
    private UIButtonFunctions UIButtonFunctions;
    private TableLayout bookListTable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        UIButtonFunctions = new UIButtonFunctions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        bookListTable = findViewById(R.id.bookListTable);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
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
        UIButtonFunctions.LoginButtonPressed();
    }

    public void onLogoutButtonClicked(MenuItem item)
    {
        UIButtonFunctions.LogoutButtonPressed();
    }

    public void searchBooks(View v)
    {
        EditText Text = (EditText)v;
        UIButtonFunctions.SearchButtonPressed(Text.getText().toString(), bookListTable, this.getBaseContext());
    }

    public void addRow(View v)
    {
        float scale = this.getBaseContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (120 * scale + 0.5f);

        TableRow row = new TableRow(this.getBaseContext());
        row.setPadding(10,10,10,10);
        TextView text = new TextView(this.getBaseContext());
        text.setText("Lord of the Rings: The Fellowship of the Ring\nJ. R. R.Tolkien\n1954\nFantasy\n\n$17.99");
        text.setGravity(Gravity.CENTER_VERTICAL);
        ImageView image = new ImageView(this.getBaseContext());
        image.setImageResource(R.drawable.lotr);
        image.setLayoutParams(new TableRow.LayoutParams(pixels, pixels));

        row.addView(image);
        row.addView(text);

        bookListTable.addView(row);
    }
}