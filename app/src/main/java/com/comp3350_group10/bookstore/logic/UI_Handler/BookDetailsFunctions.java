package com.comp3350_group10.bookstore.logic.UI_Handler;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.comp3350_group10.bookstore.data.IBook;
import com.comp3350_group10.bookstore.logic.Data_Handler.DataHandler;
import com.comp3350_group10.bookstore.ui.ScreenSize;

import java.text.DecimalFormat;

public class BookDetailsFunctions implements IBookDetailsFunctions{
    public void DrawScreen(Context context, int imageHeight, LinearLayout layout) {
        ShowImage(context, imageHeight, layout);
        CreateSpace(context, layout);
        ShowText(context, layout);
    }

    private void ShowImage(Context context, int imageHeight, LinearLayout layout) {
        int height = ScreenSize.getPixelsFromDP(context, ScreenSize.getPixelsFromDP(context, imageHeight));

        ImageView image = new ImageView(context);
        image.setImageResource(DataHandler.currentBook.getImage());
        image.setLayoutParams(new LinearLayout.LayoutParams(height, height));
        layout.addView(image);
    }

    private void CreateSpace(Context context, LinearLayout layout) {
        int height = ScreenSize.getPixelsFromDP(context,25);
        Space space = new Space(context);
        space.setLayoutParams(new LinearLayout.LayoutParams(height, height));
        layout.addView(space);
    }

    private void ShowText(Context context, LinearLayout layout) {
        IBook b = DataHandler.currentBook;
        TextView text = new TextView(context);
        text.setText(b.getBookName() + '\n');
        text.append(b.getBookAuthor() + '\n');
        text.append(b.getBookIsbn() + '\n');
        text.append(b.getStock() + " copies remaining\n");

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        text.append("$" + df.format(b.getPrice()/100f));
        layout.addView(text);
    }
}
