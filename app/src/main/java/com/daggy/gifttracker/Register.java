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
        String username = user.getText().toString();
        String password = pass.getText().toString();

        JSONObject json = new JSONObject();
        StringEntity entity;
        try {
            json.put("username", username);
            json.put("password", password);
            json.put("fullname", firstName.getText().toString() + " " + lastName.getText().toString());

            entity = new StringEntity(json.toString());

            API.post(this, "account/create", entity, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(getBaseContext(), "1" + String.valueOf(statusCode) + response.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    Toast.makeText(getBaseContext(), "2" + String.valueOf(statusCode) + response.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Toast.makeText(getBaseContext(), throwable.toString(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Toast.makeText(getBaseContext(), errorResponse.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (JSONException e) {} catch (UnsupportedEncodingException e) { }


    }
}
