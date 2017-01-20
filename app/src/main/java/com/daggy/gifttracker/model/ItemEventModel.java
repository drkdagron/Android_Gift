package com.daggy.gifttracker.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by drkdagron on 2017-01-19.
 */

public class ItemEventModel {
    public String _id;
    public String name;

    public ItemEventModel(String i, String n)
    {
        this._id = i;
        this.name = n;
    }

    public static ArrayList<ItemEventModel> fromJson(JSONArray jsonObjects)
    {
        ArrayList<ItemEventModel> events = new ArrayList<ItemEventModel>();
        for (int i= 0; i < jsonObjects.length(); i++)
        {
            try {
                events.add(new ItemEventModel(jsonObjects.getJSONObject(i).getString("_id"), jsonObjects.getJSONObject(i).getString("name")));
            } catch (JSONException e) { e.printStackTrace(); }
        }
        return events;
    }
}
