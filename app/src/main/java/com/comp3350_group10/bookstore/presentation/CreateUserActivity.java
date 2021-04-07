package com.comp3350_group10.bookstore.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.comp3350_group10.bookstore.Exceptions.CreateUserErrorException;
import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.presentation.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.IButtonFunctions;
import com.comp3350_group10.bookstore.presentation.UI_Handler.SwitchActivity;


public class CreateUserActivity extends AppCompatActivity {

    private IUserDatabase iUserDatabase;
    private EditText name;
    private EditText email;
    private EditText password;
    private Switch isManager;
    private IButtonFunctions uiButtonFunctions;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        uiButtonFunctions = new ButtonFunctions();

        name = findViewById(R.id.new_name_text);
        email = findViewById(R.id.new_email_text);
        password = findViewById(R.id.new_password_text);
        isManager = findViewById(R.id.new_manager_switch);
    }

    //Onclick method for the create user button
    public void createUserOnClick(View v) {
        IUser createdUser;
        try {
            createdUser = uiButtonFunctions.CreateUserButtonPressed(name.getText().toString(), email.getText().toString(), password.getText().toString(), isManager.isChecked());
            //default behaviour of going back to main
            if(createdUser!=null)
                SwitchActivity.SwitchTo(MainActivity.class, this);
        }
        catch(CreateUserErrorException e){
            //popup saying unsuccessful
            Messages.viewPopUp(e.getMessage(), CreateUserActivity.this);
            //reload
            finish();
            startActivity(getIntent());
        }
    }
}