package com.daggy.gifttracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;
import org.json.*;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class LoginActivity extends AppCompatActivity {

    EditText user;
    EditText pass;

    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.ent_username);
        pass = (EditText) findViewById(R.id.ent_password);

        check = (CheckBox)findViewById(R.id.check_autologin);

        SharedPreferences sharedPref = getSharedPreferences("GiftTracker", Context.MODE_PRIVATE);
        user.setText(sharedPref.getString("SavedUsername", ""));
        pass.setText(sharedPref.getString("SavedPassword", ""));
        if (sharedPref.getBoolean("SavedAccount", false))
        {
            check.setChecked(sharedPref.getBoolean("SavedAccount", false));
            fastlogin();
        }
    }

    public void register(View v)
    {
        Intent intent = new Intent(v.getContext(), Register.class);
        startActivity(intent);
    }

    public void fastlogin()
    {
        API.Login(this, user.getText().toString(), pass.getText().toString(), check.isChecked());
    }

    public void login(View v)
    {
        UserData.ShowDialog(this, "Logging in");
        API.Login(this, user.getText().toString(), pass.getText().toString(), check.isChecked());

    }
}
