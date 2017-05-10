package com.example.prashant.notificationapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

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

        textView.setText("Success");

        try {
            for (int i = 0; i < resObj.length(); i++) {
                showNotification(resObj.getJSONObject(i).getString("message"), i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showNotification(String text, int id) {
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(text);
        builder.setContentText("");
        builder.setAutoCancel(true);

        Intent intent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(id, builder.build());
    }
}
