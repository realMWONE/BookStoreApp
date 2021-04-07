package com.comp3350_group10.bookstore.presentation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.comp3350_group10.bookstore.Exceptions.ChangePasswordException;
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
    private Button changePwButton;
    private IButtonFunctions uiButtonFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        uiButtonFunctions = new ButtonFunctions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmNewPassword = findViewById(R.id.confirmNewPassword);
        changePwButton = findViewById(R.id.user_change_password);
        //if not manager, don't show create user button
        if(UserDataHandler.currentUser == null || UserDataHandler.currentUser.getUserType() != UserType.Manager)
            findViewById(R.id.create_user).setVisibility(View.GONE);

        AddTextChangedListeners();
    }

    public void changePwOnClick(View v){
        try{
            //normal behaviour
            //Shows confirmation message and return to previous activity
            if(uiButtonFunctions.ChangePasswordPressed(oldPassword.getText().toString(), newPassword.getText().toString(), confirmNewPassword.getText().toString())) {
                Messages.viewPopUp("Password successfully changed",this);
                finish();
            }
        }

        //Change pw failed, show error message
        catch (ChangePasswordException e){
            Messages.viewPopUp(e.getMessage(),this);
        }
    }


    public void logoutOnClick(View v){
        uiButtonFunctions.LogoutButtonPressed();
        //TODO: pop up saying logout successful then go back to main activity
        finish();
    }

    public void addEmployeeOnClick(View v)
    {
        SwitchActivity.SwitchTo(CreateUserActivity.class, this);
    }
    private void EnableChangePwButton(){ changePwButton.setEnabled(!oldPassword.getText().toString().equals("") &&
            !newPassword.getText().toString().equals("") && !confirmNewPassword.getText().toString().equals("")); }

    private void AddTextChangedListeners() {
        TextWatcher watcher = new TextWatcher() {
            public void afterTextChanged(Editable s) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EnableChangePwButton();
            }
        };

        oldPassword.addTextChangedListener(watcher);
        newPassword.addTextChangedListener(watcher);
        confirmNewPassword.addTextChangedListener(watcher);
    }

}