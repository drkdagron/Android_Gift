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
import com.daggy.gifttracker.UserData;
import com.daggy.gifttracker.model.AddItemModel;

/**
 * Created by drkdagron on 2017-01-26.
 */

public class AddItemDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflate = getActivity().getLayoutInflater();
        final View v = inflate.inflate(R.layout.dialog_createcomment, null);
        builder.setView(v)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserData.ShowDialog(getActivity(), "Adding Message");
                        //Toast.makeText(getActivity().getBaseContext(), "CREATE CLICKED", Toast.LENGTH_SHORT).show();
                        EditText item = (EditText)v.findViewById(R.id.edt_comment_create_name);

                        API.post_CreateComment(getActivity(), new AddItemModel(item.getText().toString()));
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
