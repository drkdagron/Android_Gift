package com.daggy.gifttracker;

import android.util.Log;

import com.daggy.gifttracker.model.ItemEventModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by drkdagron on 2017-01-19.
 */

public class UserData {
    public static JSONObject user;
    public static JSONArray events;
    public static JSONObject event;
    public static ArrayList<ItemEventModel> arrayOfEvents;

    public static void UpdateEventList()
    {
        arrayOfEvents = ItemEventModel.fromJson(events);
    }
    public static void DeleteFromEventList(String id)
    {
        Log.e("DELETE", "INSIDE DELETE");
        for(int i= 0; i < arrayOfEvents.size(); i++)
        {
            if (arrayOfEvents.get(i)._id.equals(id)) {
                arrayOfEvents.remove(i);
                Log.e("DELETE", "index: " + i);
                return;
            }
        }
        return;
    }
}
