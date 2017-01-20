package com.daggy.gifttracker.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by drkdagron on 2017-01-20.
 */

public class EventModel {
    public String Name;
    public String ID;
    public String Pin;

    public EventModel(String name, String pin, String code)
    {
        this.Name = name;
        this.ID = code;
        this.Pin = pin;
    }

    public static EventModel fromObject(JSONObject json)
    {
        return new EventModel("Test", "Test", "Test");
    }
}
