package com.comp3350_group10.bookstore.ui;

import android.content.Context;
import android.net.Uri;
import android.text.Layout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchResultItem extends LinearLayout
{
    private ImageView coverImage;
    private TextView infoText;

    public SearchResultItem(Context context)
    {
        super(context);

        coverImage = new ImageView(context);
        infoText = new TextView(context);

        this.setOrientation(LinearLayout.HORIZONTAL);

        addView(coverImage);
        addView(infoText);
    }

    public void setCoverImage(int imageReference)
    {
        coverImage.setImageResource(imageReference);
        //LayoutParams params = (LayoutParams) coverImage.getLayoutParams();
        //params.height = 200;
        //coverImage.setLayoutParams(params);
    }

    public void setInfoText(String text)
    {
        infoText.setText(text);
        LayoutParams params = (LayoutParams) infoText.getLayoutParams();
        params.weight = 1000;
        infoText.setLayoutParams(params);
    }
}
