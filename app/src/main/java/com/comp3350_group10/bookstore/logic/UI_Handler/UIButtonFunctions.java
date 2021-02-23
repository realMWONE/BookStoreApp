package com.comp3350_group10.bookstore.logic.UI_Handler;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.logic.Data_Handler.DataHandler;

public class UIButtonFunctions implements UIHandler
{

    @Override
    public void SearchButtonPressed(String keyword, TableLayout table, Context context)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (120 * scale + 0.5f);

        TableRow row = new TableRow(context);
        row.setPadding(10,10,10,10);
        TextView text = new TextView(context);
        text.setText("Lord of the Rings: The Fellowship of the Ring\nJ. R. R.Tolkien\n1954\nFantasy\n\n$17.99");
        ImageView image = new ImageView(context);
        image.setImageResource(R.drawable.lotr);
        image.setLayoutParams(new TableRow.LayoutParams(pixels, pixels));

        row.addView(image);
        row.addView(text);

        table.addView(row);
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
