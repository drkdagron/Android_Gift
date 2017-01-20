package com.daggy.gifttracker.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.daggy.gifttracker.API;
import com.daggy.gifttracker.Main;
import com.daggy.gifttracker.R;
import com.daggy.gifttracker.UserData;
import com.daggy.gifttracker.list.ListEventAdapter;
import com.daggy.gifttracker.model.CreateEventModel;
import com.daggy.gifttracker.model.ItemEventModel;

import java.util.ArrayList;

/**
 * Created by drkdagron on 2017-01-19.
 */

public class CreateEventDialog extends DialogFragment {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflate = getActivity().getLayoutInflater();
        final View v = inflate.inflate(R.layout.dialog_createevent, null);
        builder.setView(v)
            .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(getActivity().getBaseContext(), "CREATE CLICKED", Toast.LENGTH_SHORT).show();
                    EditText name = (EditText)v.findViewById(R.id.edit_create_name);
                    EditText pin = (EditText)v.findViewById(R.id.edit_create_pin);

                    API.post_CreateEvent(getActivity(), new CreateEventModel(name.getText().toString(), pin.getText().toString()));
                }
            })
            .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //testToast.makeText(getActivity().getBaseContext(), "BACK CLICKED", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

        return builder.create();
    }
}
