package com.comp3350_group10.bookstore.presentation;

import android.os.Bundle;

import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.business.Notify;
import com.comp3350_group10.bookstore.presentation.UI_Handler.BookDetailsFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IBookDetailsFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IButtonFunctions;
import com.comp3350_group10.bookstore.R;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetailsActivity extends AppCompatActivity {

    //private final int IMAGE_HEIGHT = 110;
    IBookDetailsFunctions bookDetailsFunctions;
    IButtonFunctions buttonFunctions;
    private TextView bookTitle;
    private ImageView bookImage;
    private TextView details;
    Notify notify= new Notify();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notify.checkAndroidVersion(BookDetailsActivity.this);
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
        //only called/notified when stock is low
        if(BookDataHandler.currentBook.getStock()<10)
            notify.lowStockNotification(BookDetailsActivity.this);
    }

    public void OnReturnClick(View v) {
        buttonFunctions.IncrementStock(details);
        //only call/remove from list when stock is high
        if(BookDataHandler.currentBook.getStock()>10)
            notify.removeFromLowStockList(BookDataHandler.currentBook);
    }
}