package com.comp3350_group10.bookstore.business.UI_Handler;

import android.widget.ImageView;
import android.widget.TextView;

public interface IBookDetailsFunctions {
    void LoadBookInfo(TextView title, ImageView cover, TextView details);
    void UpdateBookDetails(TextView details);
}
