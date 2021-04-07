package com.comp3350_group10.bookstore.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.comp3350_group10.bookstore.R;

import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.presentation.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.SwitchActivity;

public class LoginActivity extends AppCompatActivity
{
    IButtonFunctions buttonFunctions;

    private EditText password;
    private EditText email;
    private Button loginButton;
    public static TextView errorMessage;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        buttonFunctions = new ButtonFunctions();
        errorMessage = findViewById(R.id.loginErrorMessage);
        password = findViewById(R.id.password);
        email = findViewById(R.id.username);
        loginButton = findViewById(R.id.loginButton);

        AddTextChangedListeners();
    }

    private void AddTextChangedListeners() {
        TextWatcher watcher = new TextWatcher() {
            public void afterTextChanged(Editable s) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EnableLoginButton();
            }
        };

        password.addTextChangedListener(watcher);
        email.addTextChangedListener(watcher);
    }

    private void EnableLoginButton()
    {
        loginButton.setEnabled(!password.getText().toString().equals("") && !email.getText().toString().equals(""));
    }

    public void LoginOnClick(View v)
    {
        try{
        buttonFunctions.LoginButtonPressed(this.email.getText().toString(), this.password.getText().toString());
        if (UserDataHandler.currentUser != null)
            Messages.viewPopUp("Login successful",this);
            SwitchActivity.SwitchTo(MainActivity.class, this);
        }
        catch (Exception e){
            Messages.viewPopUp(e.getMessage(),this);
        }
    }
}