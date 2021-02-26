package com.comp3350_group10.bookstore;

import android.media.Image;
import android.os.Bundle;

import com.comp3350_group10.bookstore.data.IBook;
import com.comp3350_group10.bookstore.logic.Data_Handler.DataHandler;
import com.comp3350_group10.bookstore.logic.UI_Handler.BookDetailsFunctions;
import com.comp3350_group10.bookstore.logic.UI_Handler.IBookDetailsFunctions;
import com.comp3350_group10.bookstore.ui.ScreenSize;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;

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