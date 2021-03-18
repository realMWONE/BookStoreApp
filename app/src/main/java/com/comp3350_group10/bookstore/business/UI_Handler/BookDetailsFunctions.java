package com.comp3350_group10.bookstore.business.UI_Handler;

import android.widget.ImageView;
import android.widget.TextView;

import com.comp3350_group10.bookstore.business.Data_Handler.BookDataHandler;
import com.comp3350_group10.bookstore.persistence.IBook;

import java.text.DecimalFormat;

public class BookDetailsFunctions implements IBookDetailsFunctions{

    @Override
    public void LoadBookInfo(TextView title, ImageView cover, TextView details) {
        title.setText(BookDataHandler.currentBook.getBookName());
        cover.setImageResource(BookDataHandler.currentBook.getImage());
        details.setText(FormatBookDetails());
    }

    private String FormatBookDetails() {
        IBook b = BookDataHandler.currentBook;
        String text = b.getBookAuthor() + '\n' + (b.getBookIsbn() + '\n') + (b.getStock() + " copies remaining\n");

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        text += ("$" + df.format(b.getPrice()/100f));
        return text;
    }

    public void UpdateBookDetails(TextView details) {
        details.setText(FormatBookDetails());
    }
}