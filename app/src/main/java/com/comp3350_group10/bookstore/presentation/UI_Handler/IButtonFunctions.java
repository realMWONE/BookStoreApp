package com.comp3350_group10.bookstore.presentation.UI_Handler;

import android.app.Activity;
import android.content.Context;
import android.widget.TableLayout;
import android.widget.TextView;

import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.presentation.MainActivity;
import com.comp3350_group10.bookstore.presentation.UserSettingActivity;

public interface IButtonFunctions {

    //function is called when user hits search button to
    // find a book based on ISBN/author/title
    void SearchButtonPressed(String keyword, TableLayout table, Context context, MainActivity main, String order, String searchBy) throws ClassNotFoundException;

    void SwitchToLoginActivity(MainActivity main, Context context);

    //function is called when user logs in with their respective accounts
    void LoginButtonPressed(String email, String password) throws ClassNotFoundException;

    //function is called when user wants to logout
    void LogoutButtonPressed();

    //function is called when a user hits change password button in setting page
    void ChangePasswordPressed(String oldPw, String newPw, String confirmNewPw);

    //function that brings the user to the user setting page
    void SwitchToUserSettingActivity(Context context, MainActivity main);

    //function is called when user hits the increment stock by 1 button
    void IncrementStock(TextView text);

    //function is called when user hits the decrement stock by 1 button
    void DecrementStock(TextView text);

    //function is called when user presses the restock button
    // to change the quantity of stock available
    void SetStock(int newStock) throws ClassNotFoundException;

    void SetPrice(int newPrice) throws ClassNotFoundException;

    void SwitchToCreateUserActivity(Context context, UserSettingActivity activity);

    //Creates the user with given parameters
    //Show popup and return to main activity if successful
    //Show popup and return to createUser activity if failed
    IUser CreateUserButtonPressed(String name, String email, String password, boolean isManager) throws ClassNotFoundException;

    //return to main activity
    void SwitchToMainActivity(Context context, Activity activity);
}
