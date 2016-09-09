package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.guidance.GuidanceActivity;

public class StartActivity extends AppCompatActivity {
    public TextView mTimeTxt;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mTimeTxt = (TextView) findViewById(R.id.start_time_text);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this, GuidanceActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);

        new Thread(new Runnable() {
            int i;
            @Override
            public void run() {
                for (i = 4; i >= 1; i--) {
                    SystemClock.sleep(1000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTimeTxt.setText(""+i);
                        }
                    });
                }
            }
        }).start();
    }

}
