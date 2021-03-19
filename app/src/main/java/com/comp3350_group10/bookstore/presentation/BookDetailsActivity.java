package com.comp3350_group10.bookstore.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.business.UI_Handler.BookDetailsFunctions;
import com.comp3350_group10.bookstore.business.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.business.UI_Handler.IBookDetailsFunctions;
import com.comp3350_group10.bookstore.business.UI_Handler.IButtonFunctions;

public class BookDetailsActivity extends AppCompatActivity {

    //private final int IMAGE_HEIGHT = 110;
    IBookDetailsFunctions bookDetailsFunctions;
    IButtonFunctions buttonFunctions;
    private TextView bookTitle;
    private ImageView bookImage;
    private TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bookTitle = findViewById(R.id.bookDetailsTitle);
        bookImage = findViewById(R.id.bookDetailsImage);
        details = findViewById(R.id.bookDetailsText);

        buttonFunctions = new ButtonFunctions();
        bookDetailsFunctions = new BookDetailsFunctions();
        //bookDetailsFunctions.DrawScreen(this, 120, bookDetailsLayout);
        bookDetailsFunctions.LoadBookInfo(bookTitle, bookImage, details);
    }

    public void OnSaleClick(View v) {
        buttonFunctions.DecrementStock(details);
    }

    public void OnReturnClick(View v) {
        buttonFunctions.IncrementStock(details);
    }
}