package com.example.administrator.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public ImageView mIdDelete, mPasswordDelete;
    public EditText mIdEdit, mPasswordEdit;
    public Button mLoginBrn;
    public TextView mToastTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mIdDelete = (ImageView) findViewById(R.id.id_delete);
        mIdDelete.setOnClickListener(this);
        mPasswordDelete = (ImageView) findViewById(R.id.password_delete);
        mPasswordDelete.setOnClickListener(this);

        mLoginBrn = (Button) findViewById(R.id.login_brn);
        mLoginBrn.setOnClickListener(this);

        mIdEdit = (EditText) findViewById(R.id.id_edit);
        mPasswordEdit = (EditText) findViewById(R.id.password_edit);

        mIdEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0)
                    mIdDelete.setImageResource(R.drawable.ic_clear_enabled);
                else
                    mIdDelete.setImageResource(R.drawable.ic_clear_disabled);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0)
                    mPasswordDelete.setImageResource(R.drawable.ic_clear_enabled);
                else
                    mPasswordDelete.setImageResource(R.drawable.ic_clear_disabled);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_brn:
                if (mIdEdit.getText().toString().equals(""))
                    mIdEdit.setError("用户不能为空！");

                if (mPasswordEdit.getText().toString().equals(""))
                    mPasswordEdit.setError("密码不能为空！");

                if (!mIdEdit.getText().toString().equals("admin") && !mPasswordEdit.getText().toString().equals("admin")
                        &&!mIdEdit.getText().toString().equals("") && !mPasswordEdit.getText().toString().equals("")) {
                    LayoutInflater inflater = LayoutInflater.from(this);
                    View view = inflater.inflate(R.layout.layout_toast, null);
                    mToastTxt = (TextView) view.findViewById(R.id.toast_view);
                    Toast toast= new Toast(getApplicationContext());
                    mToastTxt.setText("用户名或密码错误！");

                    toast.setGravity(Gravity.CENTER , 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(view);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 50, 78);
                    toast.show();
                }
                break;

            case R.id.id_delete:
                mIdEdit.setText(null);
                break;
            case R.id.password_delete:
                mPasswordEdit.setText(null);
                break;
        }
    }
}
