package com.example.prashant.notificationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    String server = "http://192.168.1.2/notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                RequestQueue rq = Volley.newRequestQueue(MainActivity.this);


            }
        });
    }

    public void sendRequest(View view) {

    }
}
