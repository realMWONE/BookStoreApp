package com.comp3350_group10.bookstore.logic.UI_Handler;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.comp3350_group10.bookstore.MainActivity;
import com.comp3350_group10.bookstore.data.IBook;

import java.util.List;

public interface IButtonFunctions {

    //function is called when user hits search button to
    // find a book based on ISBN/author/title
    void SearchButtonPressed(String keyword, TableLayout table, Context context, MainActivity main, String order, String searchBy);

    void SwitchToLoginActivity(MainActivity main, Context context);

    //function is called when user logs in with their respective accounts
    void LoginButtonPressed();

    //function is called when user wants to logout
    void LogoutButtonPressed();

    //function is called when a user hits change password button in setting page
    void ChangePasswordPressed(String oldPw, String newPw, String confirmNewPw);

    //function that brings the user to the user setting page
    void UserSettingButtonPressed(Context context, MainActivity main);

    //function is called when user hits the increment stock by 1 button
    void IncrementStock(TextView text);

    //function is called when user hits the decrement stock by 1 button
    void DecrementStock(TextView text);

    //function is called when user presses the restock button
    // to change the quantity of stock available
    void SetStock();
}
