package com.comp3350_group10.bookstore.presentation;

import android.os.Bundle;
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

    private TextView changePrice;
    private TextView changeStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bookTitle = findViewById(R.id.bookDetailsTitle);
        bookImage = findViewById(R.id.bookDetailsImage);
        details = findViewById(R.id.bookDetailsText);
        changePrice = findViewById(R.id.change_price_text);
        changeStock = findViewById(R.id.change_stock_text);

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

    public void SetStockOnClick(View v) throws ClassNotFoundException {
        int value = validateNumber(changeStock.getText().toString());
        if(value > 0) {
            buttonFunctions.SetStock(value);
            System.out.println("Stock was changed");
            bookDetailsFunctions.UpdateBookDetails(details);
        }
        else {
            System.out.println("not a valid number");   //change to alert
        }
    }

    public void SetPriceOnClick(View v) throws ClassNotFoundException {
        int value = validateNumber(changePrice.getText().toString());
        if(value > 0) {
            buttonFunctions.SetPrice(value);
            System.out.println("price was changed");
            bookDetailsFunctions.UpdateBookDetails(details);

        }
        else {
            System.out.println("not a valid number");   //change to alert
        }
    }

    private int validateNumber(String input) {
        int n = -1;                        //inititalize to invalid data as input should not be -'ve
        if(input.matches("\\d+"))   //check if only digits. Could also be text.matches("[0-9]+")
            n = Integer.parseInt(input);
        return n;
    }
}