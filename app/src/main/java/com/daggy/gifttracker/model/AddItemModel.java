package com.daggy.gifttracker.model;

import android.util.Log;

import com.daggy.gifttracker.UserData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by drkdagron on 2017-01-26.
 */

public class AddItemModel {
    public String Comment;
    public String OwnerName;
    public String OwnerId;
    public String EventId;

    public AddItemModel(String c)
    {
        this.Comment = c;
        try {
            OwnerName = UserData.user.getString("fullname");
            OwnerId = UserData.user.getString("_id");
        }
        catch (JSONException e) { }
    }
    public AddItemModel(String c, String on, String oid, String eid)
    {
        this.Comment = c;
        this.OwnerName = on;
        this.OwnerId = oid;
        this.EventId = eid;
    }

    public static ArrayList<AddItemModel> fromJson(JSONObject jsonObjects)
    {
        ArrayList<AddItemModel> comments = new ArrayList<AddItemModel>();
        try {
            for (int i = 0; i < jsonObjects.getJSONArray("items").length(); i++) {
                try {
                    comments.add(new AddItemModel(jsonObjects.getJSONArray("items").getJSONObject(i).getString("comment"), jsonObjects.getJSONArray("items").getJSONObject(i).getString("ownername"), jsonObjects.getJSONArray("items").getJSONObject(i).getString("owner"), jsonObjects.getJSONArray("items").getJSONObject(i).getString("_id")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) { }
        return comments;
    }
}

