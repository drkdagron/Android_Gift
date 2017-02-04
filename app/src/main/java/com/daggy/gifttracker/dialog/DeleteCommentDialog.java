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
import com.daggy.gifttracker.model.CreateEventModel;

/**
 * Created by drkdagron on 2017-02-04.
 */

public class DeleteCommentDialog extends DialogFragment {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    UserData.ShowDialog(getActivity(), "Deleting Item");
                        API.del_DeleteComment(getActivity(), getArguments().getString("id"));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}
