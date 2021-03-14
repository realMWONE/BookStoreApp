package com.comp3350_group10.bookstore.logic.UI_Handler;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public interface IBookDetailsFunctions {
    void LoadBookInfo(TextView title, ImageView cover, TextView details);
    void UpdateBookDetails(TextView details);
}
