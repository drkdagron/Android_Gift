package com.daggy.gifttracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        TextView lbl_title = (TextView)findViewById(R.id.lbl_event_title);
        TextView lbl_pin = (TextView)findViewById(R.id.lbl_event_pin);
        TextView lbl_code = (TextView)findViewById(R.id.lbl_event_id);

        try
        {
            lbl_title.setText(UserData.event.getString("name"));
            lbl_pin.setText("PIN: " + UserData.event.getString("pin"));
            lbl_code.setText("ID: " + UserData.event.getString("ID"));
        }
        catch (JSONException e) { }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.e("Location", "Before Try");
        try
        {
            if (UserData.user.getString("_id").equals(UserData.event.getJSONObject("owner").getString("_id")))
            {
                MenuInflater inflate = getMenuInflater();
                inflate.inflate(R.menu.event_owner_menu, menu);
                return true;
            }
        } catch (JSONException e) { }

        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_eventinfo_delete:
            {
                API.delete_event(this);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void event_backToMenu(View v)
    {
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
