package com.daggy.gifttracker;

/**
 * Created by stemc on 2017-01-18.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.daggy.gifttracker.model.CreateEventModel;
import com.daggy.gifttracker.model.ItemEventModel;
import com.daggy.gifttracker.model.JoinEventModel;
import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

public class API {

    public static InterfaceCommunicate interfaceCommunicate;
    public static interface InterfaceCommunicate {
        void sendRequestCode(int code);
    }

    private static final String base_url = "http://192.168.0.16:3000/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(Context context, String url, HttpEntity params, AsyncHttpResponseHandler handler) {
        client.post(context, base_url + url, params, "application/json" ,handler);
    }

    public static void post_CreateEvent(final Activity context, CreateEventModel c)
    {
        JSONObject json = new JSONObject();
        StringEntity entity;
        try {
            json.put("name", c.Name);
            json.put("pin", c.Pin);
            json.put("user", UserData.user.getString("username"));
            json.put("owner", UserData.user.getString("_id"));

            entity = new StringEntity(json.toString());

            API.post(context.getBaseContext(), "event/create", entity, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(context, "Event Created", Toast.LENGTH_SHORT).show();

                    post_GetEvents(context);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        Toast.makeText(context, errorResponse.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) { }
                }
            });
        } catch (JSONException e) {} catch (UnsupportedEncodingException e) { }
    }

    public static void post_JoinEvent(final Activity context, JoinEventModel j)
    {
        JSONObject json = new JSONObject();
        StringEntity entity;
        try {
            json.put("pin", j.Pin);
            json.put("code", j.Code);
            json.put("user", UserData.user.getString("_id"));

            entity = new StringEntity(json.toString());

            API.post(context, "event/join", entity, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(context.getBaseContext(), "Event Joined", Toast.LENGTH_SHORT).show();

                    post_GetEvents(context);

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        Toast.makeText(context.getBaseContext(), errorResponse.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) { }
                }
            });
        } catch (JSONException e) {} catch (UnsupportedEncodingException e) { }
    }

    public static void post_ViewEvent(final Activity context, ItemEventModel i)
    {
        JSONObject json = new JSONObject();
        StringEntity entity;
        try {
            json.put("id", i._id);

            entity = new StringEntity(json.toString());

            API.post(context, "event/view", entity, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        UserData.event = response.getJSONObject("data");
                        Intent intent = new Intent(context, Event.class);
                        context.startActivity(intent);
                    } catch (JSONException e) { }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        Toast.makeText(context, errorResponse.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) { }
                }
            });
        } catch (JSONException e) {} catch (UnsupportedEncodingException e) { }
    }

    public static void Login(final Activity context, String user, String pass, final boolean save)
    {
        final String username = user;
        final String password = pass;

        JSONObject json = new JSONObject();
        StringEntity entity;
        try {
            json.put("username", username);
            json.put("password", password);

            entity = new StringEntity(json.toString());

            API.post(context, "account/login", entity, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(context.getBaseContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    try {
                        UserData.user = response.getJSONArray("data").getJSONObject(0);
                        UserData.events = response.getJSONArray("events");
                        UserData.UpdateEventList();

                        SharedPreferences sharedPref = context.getSharedPreferences("GiftTracker", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPref.edit();
                        if (save)
                        {
                            edit.putString("SavedUsername", username);
                            edit.putString("SavedPassword", password);
                            edit.putBoolean("SavedAccount", true);
                            edit.commit();
                        }
                        else {
                            edit.putBoolean("SavedAccount", false);
                            edit.commit();
                        }

                    } catch (JSONException e) { }
                    Intent intent = new Intent(context.getBaseContext(), Main.class);
                    context.startActivity(intent);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        Toast.makeText(context.getBaseContext(), errorResponse.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) { }
                }
            });
        } catch (JSONException e) {} catch (UnsupportedEncodingException e) { }
    }

    public static void post_GetEvents(final Activity context)
    {
        JSONObject json = new JSONObject();
        StringEntity entity;
        try {
            json.put("userID", UserData.user.getString("_id"));

            entity = new StringEntity(json.toString());

            API.post(context, "event/get", entity, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        UserData.events = response.getJSONArray("data");
                        UserData.UpdateEventList();

                        interfaceCommunicate = (InterfaceCommunicate)context;
                        interfaceCommunicate.sendRequestCode(1);

                    } catch (JSONException e) { }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        Toast.makeText(context.getBaseContext(), errorResponse.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) { }
                }
            });
        } catch (JSONException e) {} catch (UnsupportedEncodingException e) { }
    }

    public static void delete_event(final Activity context)
    {
        JSONObject json = new JSONObject();
        StringEntity entity;
        try {
            json.put("_id", UserData.event.getString("_id"));

            entity = new StringEntity(json.toString());

            API.post(context, "event/delete", entity, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        UserData.DeleteFromEventList(UserData.event.getString("_id"));
                    } catch (JSONException e) { };
                    Intent intent = new Intent(context, Main.class);
                    context.startActivity(intent);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        Toast.makeText(context.getBaseContext(), errorResponse.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) { }
                }
            });
        } catch (JSONException e) {} catch (UnsupportedEncodingException e) { }
    }
}
