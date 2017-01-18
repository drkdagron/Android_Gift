package com.daggy.gifttracker;

/**
 * Created by stemc on 2017-01-18.
 */

import android.content.Context;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.HttpEntity;

public class API {

    private static final String base_url = "http://192.168.0.137:3000/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(Context context, String url, HttpEntity params, AsyncHttpResponseHandler handler) {
        client.post(context, base_url + url, params, "application/json" ,handler);

    }

}
