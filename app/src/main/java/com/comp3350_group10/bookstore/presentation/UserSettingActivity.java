package com.comp3350_group10.bookstore.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.comp3350_group10.bookstore.R;

import androidx.appcompat.app.AppCompatActivity;
import com.comp3350_group10.bookstore.business.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.business.UI_Handler.IButtonFunctions;

public class UserSettingActivity extends AppCompatActivity {
    private EditText oldPassword;
    private EditText newPassword;
    private EditText confirmNewPassword;
    private IButtonFunctions logic;
    public static TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logic = new ButtonFunctions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        errorMessage = findViewById(R.id.settingErrorMessage);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmNewPassword = findViewById(R.id.confirmNewPassword);
    }



    public void changePwOnClick(View v){
        logic.ChangePasswordPressed(oldPassword.getText().toString(), newPassword.getText().toString(), confirmNewPassword.getText().toString());
        System.out.println("Change pw clicked: "+ oldPassword.getText().toString() +" " + newPassword.getText().toString() +" " + confirmNewPassword.getText().toString());
    }


    public void logoutOnClick(View v){
        logic.LogoutButtonPressed();
        System.out.println("logging out now...");
    }
}