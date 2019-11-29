//Class to show dialog for asking usage stats permission
//On click it navigates to app list where permission is needed to be granted
//Intent on line number 26 navigates to permission page when positive button is pressed
//For now there is no negative button to deny access and exit app
package com.b.well;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class pdialog extends AppCompatDialogFragment {
    @Override
    @NonNull public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Usage Access Required")
                .setMessage("Please Provide Usage Access Permission")
                .setPositiveButton("Grant Access", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                        startActivity(intent);
                    }
                });

        return builder.create();

    }
}
