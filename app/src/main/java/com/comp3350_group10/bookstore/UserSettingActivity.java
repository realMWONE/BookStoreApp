package com.comp3350_group10.bookstore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.comp3350_group10.bookstore.logic.UI_Handler.ButtonFunctions;
import com.comp3350_group10.bookstore.logic.UI_Handler.IButtonFunctions;

public class UserSettingActivity extends AppCompatActivity {
    private EditText oldPassword;
    private EditText newPassword;
    private EditText confirmNewPassword;
    private IButtonFunctions logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logic = new ButtonFunctions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.user_settings_layout, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmNewPassword = findViewById(R.id.confirmNewPassword);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    public void changePwOnClick(View v){
        logic.ChangePasswordPressed(oldPassword.getText().toString(), newPassword.getText().toString(), confirmNewPassword.getText().toString());
    }


    public void logoutOnClick(View v){
        logic.LogoutButtonPressed();
    }

}