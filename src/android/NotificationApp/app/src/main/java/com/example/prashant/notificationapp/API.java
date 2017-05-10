package com.example.prashant.notificationapp;

/**
 * Created by Prashant on 8/05/2017.
 */

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public abstract class API {
    private final static String API_URL_STRING = "http://192.168.1.2:8080/send-notification";

    private JSONObject _reqObj;
    private Activity _activity;

    // Constructor.
    public API(Activity activity) {
        _activity = activity;
    }

    // Request/Response.
    public void setRequestObject(JSONObject reqObj) {
        _reqObj = reqObj;
    }
    private void raiseResponse(final Exception ex, final JSONArray resObj) {
        _activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onResponseCallback(ex, resObj);
            }
        });
    }
    public abstract void onResponseCallback(Exception ex, JSONArray resObj);

    // Send.
    public void send() {
        AsyncTask.execute(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                commenceRequestSend();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void commenceRequestSend() {
        try {
            URL url = new URL(API_URL_STRING);
            HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();

            httpConnection.setReadTimeout(10000);
            httpConnection.setConnectTimeout(10000);

            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");

            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);

            byte[] rawJSON = _reqObj.toString().getBytes(StandardCharsets.UTF_8);
            httpConnection.getOutputStream().write(rawJSON, 0, rawJSON.length);

            String line;
            String resObjStr = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                resObjStr += line;
            }

            raiseResponse(null, new JSONArray(resObjStr));
        } catch (Exception ex) {
            Log.e("lol", "err", ex);
            raiseResponse(ex, null);
        }
    }
}
