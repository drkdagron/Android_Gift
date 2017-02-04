package com.daggy.gifttracker.list;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.daggy.gifttracker.API;
import com.daggy.gifttracker.R;
import com.daggy.gifttracker.UserData;
import com.daggy.gifttracker.dialog.CreateEventDialog;
import com.daggy.gifttracker.dialog.EditCommentDialog;
import com.daggy.gifttracker.model.AddItemModel;
import com.daggy.gifttracker.model.ItemEventModel;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by drkdagron on 2017-01-26.
 */

public class ListCommentAdapter extends ArrayAdapter<AddItemModel> {
    FragmentManager fragment;
    public ListCommentAdapter(Context context, ArrayList<AddItemModel> events, FragmentManager frag) {
        super(context, 0, events);

        fragment = frag;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final AddItemModel comment = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, parent, false);
        }
        // Lookup view for data population
        TextView lbl_comment = (TextView) convertView.findViewById(R.id.lbl_comment_text);
        TextView lbl_owner = (TextView) convertView.findViewById(R.id.lbl_comment_name);
        // Populate the data into the template view using the data object
        lbl_comment.setText(comment.Comment);
        lbl_owner.setText(comment.OwnerName);

        Button viewBtn = (Button) convertView.findViewById(R.id.btn_comment_modify);
        try {
            if (!comment.OwnerId.equals(UserData.user.getString("_id"))) {
                viewBtn.setVisibility(View.INVISIBLE);
            }
        } catch (JSONException e) { }
        viewBtn.setTag(comment);
        viewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new EditCommentDialog();
                Bundle bun = new Bundle();
                bun.putString("comment", comment.Comment);
                bun.putString("id", comment.EventId);
                bun.putInt("position", position);
                newFragment.setArguments(bun);
                newFragment.show(fragment, "EditComment");
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

}