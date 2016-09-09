package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.login.LoginActivity;
import com.example.administrator.quitondoubleclick.QuitOnDoubleClick;

public class MeiTuanMySelfActivity extends QuitOnDoubleClick implements View.OnClickListener{
    public ImageView mRemindBrn;
    public RelativeLayout mIdLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_self);
        mRemindBrn = (ImageView) findViewById(R.id.remind_brn);
        mIdLogin = (RelativeLayout) findViewById(R.id.my_id_login);
        mIdLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
