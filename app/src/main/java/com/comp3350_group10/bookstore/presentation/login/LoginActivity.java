package com.comp3350_group10.bookstore.presentation.login;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.business.Data_Handler.DataHandler;
import com.comp3350_group10.bookstore.business.Data_Handler.IDataHandler;

public class LoginActivity extends AppCompatActivity
{
    IDataHandler dataHandler;

    private LoginViewModel loginViewModel;
    private EditText password;
    private EditText email;
    private Button loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        dataHandler = new DataHandler();

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

    private  void EnableLoginButton()
    {
        loginButton.setEnabled(!password.getText().toString().equals("") && !email.getText().toString().equals(""));
    }

    public void LoginOnClick(View v) {
        //Check if user exists
    }
}