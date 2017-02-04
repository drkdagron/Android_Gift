package com.daggy.gifttracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class Register extends AppCompatActivity {

    EditText user;
    EditText pass;
    EditText conf;
    EditText firstName;
    EditText lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = (EditText)findViewById(R.id.edt_ruser);
        pass = (EditText)findViewById(R.id.edt_rpass);
        conf = (EditText)findViewById(R.id.edt_rconf);
        firstName = (EditText)findViewById(R.id.edt_rfirst);
        lastName = (EditText)findViewById(R.id.edt_rlast);
    }

    public void backToMenu(View v)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    protected void registerUser(View v)
    {
        UserData.ShowDialog(this, "Registering User");
        String username = user.getText().toString();
        String password = pass.getText().toString();
        String first = firstName.getText().toString();
        String last = lastName.getText().toString();
        String name = first + " " + last;

        JSONObject json = new JSONObject();
        StringEntity entity;
        try {
            json.put("username", username);
            json.put("password", password);
            json.put("fullname", name);

            entity = new StringEntity(json.toString());

            API.post(this, "account/create", entity, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(getBaseContext(), "Account successfully created. Please sign in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                    UserData.HideDialog();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        Toast.makeText(getBaseContext(), errorResponse.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) { }
                    UserData.HideDialog();
                }
            });
        } catch (JSONException e) {} catch (UnsupportedEncodingException e) { }


    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
