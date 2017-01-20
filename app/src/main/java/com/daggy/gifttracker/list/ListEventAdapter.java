package com.daggy.gifttracker.list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.daggy.gifttracker.API;
import com.daggy.gifttracker.R;
import com.daggy.gifttracker.model.ItemEventModel;

import java.util.ArrayList;

/**
 * Created by drkdagron on 2017-01-19.
 */

public class ListEventAdapter extends ArrayAdapter<ItemEventModel> {
    public ListEventAdapter(Context context, ArrayList<ItemEventModel> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ItemEventModel event = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
        }
        // Lookup view for data population
        TextView lbl_title = (TextView) convertView.findViewById(R.id.lbl_list_name);
        // Populate the data into the template view using the data object
        lbl_title.setText(event.name);

        Button viewBtn = (Button) convertView.findViewById(R.id.btn_list_view);
        viewBtn.setTag(event);
        viewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ItemEventModel model = (ItemEventModel)v.getTag();

                API.post_ViewEvent((Activity)getContext(), model);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

}
