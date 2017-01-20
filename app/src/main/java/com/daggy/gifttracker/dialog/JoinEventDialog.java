package com.daggy.gifttracker.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.daggy.gifttracker.API;
import com.daggy.gifttracker.R;
import com.daggy.gifttracker.model.JoinEventModel;

/**
 * Created by drkdagron on 2017-01-19.
 */

public class JoinEventDialog extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflate = getActivity().getLayoutInflater();
        final View v = inflate.inflate(R.layout.dialog_joinevent, null);

        builder.setView(v)
                .setPositiveButton("Join", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText pin = (EditText)v.findViewById(R.id.edit_join_pin);
                        EditText code = (EditText)v.findViewById(R.id.edit_join_code);
                        JoinEventModel jem = new JoinEventModel(code.getText().toString(), pin.getText().toString());
                        API.post_JoinEvent(getActivity(), jem);
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JoinEventDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
