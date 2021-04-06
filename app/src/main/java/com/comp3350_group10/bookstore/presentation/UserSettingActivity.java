package com.comp3350_group10.bookstore.presentation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.comp3350_group10.bookstore.R;

import androidx.appcompat.app.AppCompatActivity;

import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.presentation.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.SwitchActivity;

public class UserSettingActivity extends AppCompatActivity {
    private EditText oldPassword;
    private EditText newPassword;
    private EditText confirmNewPassword;
    private IButtonFunctions uiButtonFunctions;
//    public static TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        uiButtonFunctions = new ButtonFunctions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

//        errorMessage = findViewById(R.id.settingErrorMessage);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmNewPassword = findViewById(R.id.confirmNewPassword);

        //if not manager, don't show create user button
        if(UserDataHandler.currentUser == null || UserDataHandler.currentUser.getUserType() != UserType.Manager)
            findViewById(R.id.create_user).setVisibility(View.GONE);
    }



    public void changePwOnClick(View v){
        if(uiButtonFunctions.ChangePasswordPressed(oldPassword.getText().toString(), newPassword.getText().toString(), confirmNewPassword.getText().toString())) {
            //TODO: pop up to say change was successful, then go back to main activity
            finish();
        }
        else {
            //TODO: pop up to say change failed
            finish();
            startActivity(getIntent());
        }

//        System.out.println("Change pw clicked: "+ oldPassword.getText().toString() +" " + newPassword.getText().toString() +" " + confirmNewPassword.getText().toString());
    }


    public void logoutOnClick(View v){
        uiButtonFunctions.LogoutButtonPressed();
        //TODO: pop up saying logout successful then go back to main activity
        finish();
//        System.out.println("logging out now...");
    }

    public void addEmployeeOnClick(View v)
    {
        SwitchActivity.SwitchTo(CreateUserActivity.class, this);
    }

}