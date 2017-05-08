package com.example.prashant.notificationapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                request();
            }
        });
    }

    // API.
    private void request() {
        JSONArray reqObj = new JSONArray();

        API req = new API(MainActivity.this) {

            @Override
            public void onResponseCallback(Exception ex, JSONArray resObj) {
                responseHandler(ex, resObj);
            }
        };
        req.setRequestObject(reqObj);
        req.send();
    }

    private void responseHandler(Exception ex, JSONArray resObj) {
        if (ex != null) {
            textView.setText("Error");
            return;
        }

        for (int i = 0; i < resObj.length(); i++) {
            try {
                textView.setText(resObj.getJSONObject(i).getString("message"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
