package com.daggy.gifttracker;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Debug;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.daggy.gifttracker.dialog.CreateEventDialog;
import com.daggy.gifttracker.dialog.JoinEventDialog;
import com.daggy.gifttracker.dialog.LogoutDialog;
import com.daggy.gifttracker.list.ListEventAdapter;
import com.daggy.gifttracker.model.CreateEventModel;
import com.daggy.gifttracker.model.ItemEventModel;

import org.json.JSONException;

import java.util.ArrayList;

public class Main extends AppCompatActivity implements API.InterfaceCommunicate{

    public ListEventAdapter adapter;
    public ListView list;

    @Override
    public void sendRequestCode(int i)
    {
        Log.e("INTERFACE", "INTERFACE GOT CALLED!!! OMFG");
        if (i == 1) {
            UserData.UpdateEventList();
            adapter = new ListEventAdapter(this, UserData.arrayOfEvents);
            list.setAdapter(adapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.list_events);
        adapter = new ListEventAdapter(this, UserData.arrayOfEvents);
        if (UserData.arrayOfEvents != null) {
            if (UserData.arrayOfEvents.size() != 0)
                list.setAdapter(adapter);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Toast.makeText(getBaseContext(), "OnActivityResult", Toast.LENGTH_SHORT).show();
    }

    public void createEvent(View v)
    {
        Toast.makeText(this, "CREATE CLICKED", Toast.LENGTH_SHORT);
        DialogFragment newFragment = new CreateEventDialog();
        newFragment.show(getFragmentManager(), "CreateEvent");
    }

    public void joinEvent(View v)
    {
        Toast.makeText(this, "JOIN CLICKED", Toast.LENGTH_SHORT);
        DialogFragment newFragment = new JoinEventDialog();
        newFragment.show(getFragmentManager(), "JoinEvent");
    }

    public void logout(View v)
    {
        DialogFragment frag = new LogoutDialog();
        frag.show(getFragmentManager(), "Logout");
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
