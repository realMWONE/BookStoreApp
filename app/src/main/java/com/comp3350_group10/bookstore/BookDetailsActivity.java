package com.comp3350_group10.bookstore;

import android.media.Image;
import android.os.Bundle;

import com.comp3350_group10.bookstore.data.IBook;
import com.comp3350_group10.bookstore.logic.Data_Handler.DataHandler;
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
    private final int IMAGE_HEIGHT = 350;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        bookDetailsLayout = findViewById(R.id.bookDetailsLayout);
        setSupportActionBar(toolbar);

        ShowImage();
        CreateSpace();
        ShowText();
    }

    private void ShowImage() {
        int height = ScreenSize.getPixelsFromDP(this, IMAGE_HEIGHT);

        ImageView image = new ImageView(this);
        image.setImageResource(DataHandler.currentBook.getImage());
        ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(height, height);
        image.setLayoutParams(new LinearLayout.LayoutParams(height, height));
        bookDetailsLayout.addView(image);
    }

    private void CreateSpace() {
        int height = ConvertToPixels(25);

        Space space = new Space(this);
        ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(height, height);
        space.setLayoutParams(new LinearLayout.LayoutParams(height, height));
        bookDetailsLayout.addView(space);
    }

    private void ShowText() {
        IBook b = DataHandler.currentBook;
        TextView text = new TextView(this);
        text.setText(b.getBookName() + '\n');
        text.append(b.getBookAuthor() + '\n');
        text.append(b.getBookIsbn() + '\n');
        text.append(b.getStock() + " copies remaining\n");

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        text.append("$" + df.format(b.getPrice()/100f));
        bookDetailsLayout.addView(text);
    }

    private int ConvertToPixels(int dp) {
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}