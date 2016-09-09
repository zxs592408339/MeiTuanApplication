package com.example.administrator.quitondoubleclick;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

// TODO: 2016/6/24 继承该能可以实现双击返回退出程序
public class QuitOnDoubleClick extends AppCompatActivity {
    boolean f = false;
    public void onBackPressed() {
        if (f) {
            finish();
            System.exit(0);
        }
        f = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                f = false;
            }
        }).start();
        Toast.makeText(this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
    }
}
