package com.daggy.gifttracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.daggy.gifttracker.model.AddItemModel;
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
    public static ArrayList<AddItemModel> arrayOfComments;

    public static void UpdateItemList()
    {
        arrayOfComments = AddItemModel.fromJson(event);
    }

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
    public static void DeleteFromCommentList(String id)
    {
        for (int i= 0; i < arrayOfComments.size(); i++)
        {
            if (arrayOfComments.get(i).EventId.equals(id))
            {
                arrayOfComments.remove(i);
                return;
            }
        }
    }

    static ProgressDialog mDialog;

    public static void ShowDialog(Context con, String msg)
    {
        mDialog = new ProgressDialog(con);
        mDialog.setMessage(msg);
        mDialog.setCancelable(false);
        mDialog.show();
    }
    public static void HideDialog()
    {
        mDialog.hide();
    }
}
