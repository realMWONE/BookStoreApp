package com.comp3350_group10.bookstore.presentation.UI_Handler;

import android.app.Activity;
import android.content.Intent;

import com.comp3350_group10.bookstore.presentation.MainActivity;

public interface ISwitchActivity
{
    static void SwitchTo(Class destinationClass, Activity CurrentActivity)
    {
        Intent intent = new Intent(CurrentActivity.getBaseContext(), destinationClass);
        CurrentActivity.startActivity(intent);
    }
}
