package com.comp3350_group10.bookstore.presentation.UI_Handler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.business.IBookDataHandler;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.hsqldb.ImageReferences;
import com.comp3350_group10.bookstore.presentation.BookDetailsActivity;
import com.comp3350_group10.bookstore.presentation.ScreenSize;

import java.util.List;

public class TrendingPageFunctions {
    private static int imageHeight = 0;
    private static int spacerHeight = 0;
    private static int dividerHeight = 0;

    public static void FillTrendingPage(TableLayout table, Context context) throws ClassNotFoundException {
        setHeights(context);
        AddSpacer(context, table);
        AddTrendingRow(table, context, "By Stephanie Meyer", "Stephenie Meyer");
        AddTrendingRow(table, context, "Harry Potter series", "Harry Potter");
        AddTrendingRow(table, context, "By Jeff Kinney", "Jeff Kinney");
        AddTrendingRow(table, context, "The Lord of the Rings", "Lord");
        AddTrendingRow(table, context, "By J. R. R. Tolkien", "Tolkien");
    }

    private static void setHeights(Context context) {
        imageHeight = ScreenSize.getPixelsFromDP(context, 120);
        spacerHeight = ScreenSize.getPixelsFromDP(context, 10);
        dividerHeight = ScreenSize.getPixelsFromDP(context, 3);
    }

    private static void AddTrendingRow(TableLayout table, Context context, String categoryName, String searchTerm) throws ClassNotFoundException {
        AddCategoryName(table, context, categoryName);
        AddSpacer(context, table);
        AddRowContent(table, context, searchTerm);
        AddDivider(context, table);
    }

    private static void AddSpacer(Context context, TableLayout table) {
        Space space = new Space(context);
        space.setLayoutParams(new LinearLayout.LayoutParams(spacerHeight, spacerHeight));
        //TODO: Warning:(50, 61) 'spacerHeight' should probably not be passed as parameter 'width'
        table.addView(space);
    }

    private static void AddDivider(Context context, TableLayout table) {
        AddSpacer(context, table);
        ImageView image = new ImageView(context);
        image.setLayoutParams(new TableRow.LayoutParams(dividerHeight, dividerHeight));
        image.setImageResource(R.drawable.divider);
        table.addView(image);
        AddSpacer(context, table);
    }

    private static void AddCategoryName(TableLayout table, Context context, String categoryName) {
        TableRow row = new TableRow(context);
        TextView categoryText = new TextView(context);
        categoryText.setText(categoryName);
        categoryText.setPadding(30,0,0,0);
        row.addView(categoryText);
        table.addView(row);
    }

    private static void AddRowContent(TableLayout table, Context context, String searchTerm) throws ClassNotFoundException {
        TableRow row = new TableRow(context);
        HorizontalScrollView scrollView = new HorizontalScrollView(context);
        LinearLayout layout = new LinearLayout(context);

        scrollView.addView(layout);
        row.addView(scrollView);
        table.addView(row);

        AddImagesToRow(context, layout, searchTerm);

        scrollView.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
    }

    @SuppressLint("ResourceType")
    private static void AddImagesToRow(Context context, LinearLayout layout, String searchTerm) throws ClassNotFoundException {
        IBookDataHandler bookHandler = new BookDataHandler();
        List<IBook> books = bookHandler.findBooks(searchTerm);

        for (IBook book : books) {
            ImageView image = new ImageView(context);
            image.setClickable(true);
            image.setLayoutParams(new TableRow.LayoutParams(imageHeight, imageHeight));

            ImageReferences.FillDictionary();
            System.out.println(book.getImage());
            image.setImageResource(ImageReferences.Get(book.getImage()));

            layout.addView(image);
            image.setOnClickListener(v -> {
                BookDataHandler.currentBook = book;
                SwitchToBookDetailsActivity(context);
            });
        }
    }

    @DrawableRes
    private static int GetID(IBook book)
    {
        return book.getImage();
    }

    private static void SwitchToBookDetailsActivity(Context context) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        context.startActivity(intent);
    }
}