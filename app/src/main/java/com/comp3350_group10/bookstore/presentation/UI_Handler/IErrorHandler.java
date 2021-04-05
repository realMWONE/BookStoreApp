package com.comp3350_group10.bookstore.presentation.UI_Handler;

import com.comp3350_group10.bookstore.presentation.UserSettingActivity;
import com.comp3350_group10.bookstore.presentation.login.LoginActivity;

public interface IErrorHandler {
     String userNotFound();

    String differentPasswords();

//    static void ShowSettingsErrorMessage(String message) {
//        //UserSettingActivity.errorMessage.setText(message);
//    }
}
