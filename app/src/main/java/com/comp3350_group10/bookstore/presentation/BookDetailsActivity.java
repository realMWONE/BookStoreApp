package com.comp3350_group10.bookstore.presentation;

import android.os.Bundle;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.business.UI_Handler.BookDetailsFunctions;
import com.comp3350_group10.bookstore.business.UI_Handler.IBookDetailsFunctions;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.LinearLayout;

public class BookDetailsActivity extends AppCompatActivity {

    private LinearLayout bookDetailsLayout;
    private final int IMAGE_HEIGHT = 110;
    IBookDetailsFunctions bookDetailsFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        bookDetailsLayout = findViewById(R.id.bookDetailsLayout);
        setSupportActionBar(toolbar);

        bookDetailsFunctions = new BookDetailsFunctions();
        bookDetailsFunctions.DrawScreen(this, IMAGE_HEIGHT, bookDetailsLayout);
    }
}