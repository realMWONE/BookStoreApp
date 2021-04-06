package com.comp3350_group10.bookstore.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import com.comp3350_group10.bookstore.R;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class Messages {
    public static void fatalError(final Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.fatalError));
        alertDialog.setMessage(message);
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                owner.finish();
            }
        });

        alertDialog.show();
    }

    public static void warning(Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.warning));
        alertDialog.setMessage(message);

        alertDialog.show();
    }

    public static void viewPopUp(String message, Context context){
        Toast.makeText(context, message, LENGTH_LONG).show();
    }
}
