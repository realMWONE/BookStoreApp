package com.comp3350_group10.bookstore.logic.UI_Handler;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.data.IBook;
import com.comp3350_group10.bookstore.logic.Data_Handler.Data;
import com.comp3350_group10.bookstore.logic.Data_Handler.DataHandler;

import java.text.DecimalFormat;
import java.util.List;

public class UIButtonFunctions implements UIHandler
{
    private int rowHeightDP;
    private Data dataHandler;

    public UIButtonFunctions() {
        dataHandler = new DataHandler();
    }

    public void setHeight(int height) {
        rowHeightDP = height;
    }

    @Override
    public void SearchButtonPressed(String keyword, TableLayout table, Context context) {
        table.removeAllViews();

        float scale = context.getResources().getDisplayMetrics().density;
        int height = (int) (rowHeightDP * scale + 0.5f);

        List<IBook> results = dataHandler.findBooks(keyword);

        if (results != null) {
            for (IBook book : results) {
                TableRow row = CreateTableRow(context);
                TextView text = CreateTextView(context, book);
                ImageView image = CreateImageView(context, book, height);

                row.addView(image);
                row.addView(text);
                table.addView(row);
            }
        }
    }

    private TableRow CreateTableRow(Context context) {
        TableRow row = new TableRow(context);
        row.setPadding(10,10,10,10);
        return row;
    }

    private TextView CreateTextView(Context context, IBook book) {
        TextView text = new TextView(context);

        float price = book.getPrice() / 100f;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        text.setText(book.getBookName() + "\n" + book.getBookAuthor() + "\n" + book.getBookIsbn() + "\n\n$" + df.format(price));

        return text;
    }

    private ImageView CreateImageView(Context context, IBook book, int height) {
        ImageView image = new ImageView(context);
        image.setImageResource(book.getImage());
        image.setLayoutParams(new TableRow.LayoutParams(height, height));
        return image;
    }

    @Override
    public void LoginButtonPressed()
    {

    }

    @Override
    public void LogoutButtonPressed()
    {

    }

    @Override
    public void UserSettingsButtonPressed()
    {

    }

    @Override
    public void IncrementStock()
    {

    }

    @Override
    public void DecrementStock()
    {

    }

    @Override
    public void SetStock()
    {

    }
}
