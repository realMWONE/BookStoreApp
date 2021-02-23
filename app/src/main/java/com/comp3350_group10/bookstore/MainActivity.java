package com.comp3350_group10.bookstore;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.comp3350_group10.bookstore.logic.Data_Handler.DataHandler;
import com.comp3350_group10.bookstore.logic.UI_Handler.UIButtonFunctions;
import com.comp3350_group10.bookstore.ui.SearchResultItem;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    private DataHandler DataHandler;
    private UIButtonFunctions UIButtonFunctions;
    private LinearLayout bookListLayout;

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
        bookListLayout = findViewById(R.id.BookListLayout);
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

    public void searchBook(View v)
    {
        EditText Text = (EditText)v;
        UIButtonFunctions.SearchButtonPressed(Text.getText().toString(), bookListLayout);
    }

    public void addRow(View v)
    {
        SearchResultItem item = new SearchResultItem(this.getBaseContext());
        ImageView image = new ImageView(this.getBaseContext());
        image.setImageResource(R.drawable.lotr);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) image.getLayoutParams();
        params.height = 200;
        image.setLayoutParams(params);
        item.setCoverImage(R.drawable.lotr);
        item.setInfoText("This is a test");
        bookListLayout.addView(item);
    }
}