package com.example.prashant.notificationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        JSONObject reqObj = new JSONObject();

        API req = new API(MainActivity.this) {
            @Override
            public void onResponseCallback(Exception ex, JSONObject resObj) {
                responseHandler(ex, resObj);
            }
        };
        req.setRequestObject(reqObj);
        Log.e("Yes","Lol");
        req.send();
    }

    private void responseHandler(Exception ex, JSONObject resObj) {
        if (ex != null) {
            textView.setText("Error");
            return;
        }

        textView.setText("Success");
    }
}
