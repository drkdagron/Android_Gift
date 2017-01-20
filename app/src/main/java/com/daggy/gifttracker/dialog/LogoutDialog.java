package com.daggy.gifttracker.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.daggy.gifttracker.LoginActivity;
import com.daggy.gifttracker.UserData;

/**
 * Created by drkdagron on 2017-01-20.
 */

public class LogoutDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Logout").setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UserData.user = null;
                    UserData.event = null;

                    SharedPreferences pref = getActivity().getSharedPreferences("GiftTracker", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putBoolean("SavedAccount", false);
                    edit.commit();

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LogoutDialog.this.getDialog().cancel();
                }
            });

        return builder.create();
    }
}
