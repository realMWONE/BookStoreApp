package com.comp3350_group10.bookstore.logic.UI_Handler;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.comp3350_group10.bookstore.MainActivity;

public interface IButtonFunctions {

    //function is called when user hits search button to
    // find a book based on ISBN/author/title
    void SearchButtonPressed(String keyword, TableLayout table, Context context, MainActivity main);

    //function is called when user logs in with their respective accounts
    void LoginButtonPressed();

    //function is called when user wants to logout
    void LogoutButtonPressed();

    //function is called when a user hits settings button to change settings
    void UserSettingsButtonPressed();

    //function is called when user hits the increment stock by 1 button
    void IncrementStock();

    //function is called when user hits the decrement stock by 1 button
    void DecrementStock();

    //function is called when user presses the restock button
    // to change the quantity of stock available
    void SetStock();
}
