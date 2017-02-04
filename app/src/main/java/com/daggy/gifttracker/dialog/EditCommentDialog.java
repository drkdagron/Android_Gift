package com.daggy.gifttracker.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.daggy.gifttracker.API;
import com.daggy.gifttracker.R;
import com.daggy.gifttracker.UserData;
import com.daggy.gifttracker.model.CreateEventModel;

/**
 * Created by drkdagron on 2017-02-04.
 */

public class EditCommentDialog extends DialogFragment {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String commentId = getArguments().getString("id");
        final int position = getArguments().getInt("position");

        LayoutInflater inflate = getActivity().getLayoutInflater();
        final View v = inflate.inflate(R.layout.dialog_edit_comment, null);

        final EditText item = (EditText)v.findViewById(R.id.edt_edit_comment_text);
        item.setText(getArguments().getString("comment"));
        Button deleteBtn = (Button)v.findViewById(R.id.btn_edit_comment_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DeleteCommentDialog();
                Bundle bun = new Bundle();
                bun.putString("id", commentId);
                newFragment.setArguments(bun);
                newFragment.show(getActivity().getFragmentManager(), "DeleteComment");
            }
        });

        builder.setView(v)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserData.ShowDialog(getActivity(), "Modifing Item");
                        API.put_EditComment(getActivity(), item.getText().toString(), commentId, position);
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}