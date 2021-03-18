package com.comp3350_group10.bookstore.logic.UI_Handler;

import android.content.Context;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.logic.Data_Handler.DataHandler;
import com.comp3350_group10.bookstore.logic.Data_Handler.IDataHandler;
import com.comp3350_group10.bookstore.persistence.Book.IBook;
import com.comp3350_group10.bookstore.ui.ScreenSize;

import java.util.List;

public class TrendingPageFuctions {
    private static int imageHeight = 0;
    private static int spacerHeight = 0;
    private static int dividerHeight = 0;

    public static void FillTrendingPage(TableLayout table, Context context) {
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

    private static void AddTrendingRow(TableLayout table, Context context, String categoryName, String searchTerm) {
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

    private static void AddRowContent(TableLayout table, Context context, String searchTerm) {
        TableRow row = new TableRow(context);
        HorizontalScrollView scrollView = new HorizontalScrollView(context);
        LinearLayout layout = new LinearLayout(context);

        scrollView.addView(layout);
        row.addView(scrollView);
        table.addView(row);

        AddImagesToRow(context, layout, searchTerm);

        scrollView.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
    }

    private static void AddImagesToRow(Context context, LinearLayout layout, String searchTerm) {
        IDataHandler dataHandler = new DataHandler();
        List<IBook> books = dataHandler.findBooks(searchTerm);

        for (IBook book : books) {
            ImageView image = new ImageView(context);
            image.setLayoutParams(new TableRow.LayoutParams(imageHeight, imageHeight));
            image.setImageResource(book.getImage());
            layout.addView(image);
        }
    }
}
