package com.comp3350_group10.bookstore.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.presentation.UI_Handler.SwitchActivity;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        TextView info = findViewById(R.id.contact_info_text);

        info.setText(
                "Email: reserve@bookstore.com\n" +
                "Phone: (204)-123-1234"
        );
    }

    public void returnHomeOnClick(View v){
        SwitchActivity.SwitchTo(MainActivity.class,this);
    }

    public void returnToBookOnClick(View v){
        finish();
    }
}