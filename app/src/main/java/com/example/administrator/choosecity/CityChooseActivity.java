package com.example.administrator.choosecity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CityChooseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    public ListView mCityChooseList;
    public TextView mSelectedCity;
    public ImageView mChooseCityBack;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);
        mCityChooseList = (ListView) findViewById(R.id.city_choose_list);

        mChooseCityBack = (ImageView) findViewById(R.id.city_choose_back);
        mChooseCityBack.setOnClickListener(this);

        mSelectedCity = (TextView) findViewById(R.id.selected_city);

        addCtiy();

        CityListAdapter adapter = new CityListAdapter(this, list);
        mCityChooseList.setAdapter(adapter);

        mCityChooseList.setOnItemClickListener(this);
        selectedCity();
    }

    // TODO: 2016/6/23 城市数据源
    public void addCtiy() {
        list.add("成都");
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("深圳");
        list.add("天津");
        list.add("郑州");
        list.add("哈尔滨");
        list.add("长春");
        list.add("沈阳");
        list.add("黑龙江");
        list.add("太原");
        list.add("西藏");
        list.add("呼和浩特");
        list.add("乌鲁木齐");
        list.add("兰州");
    }

    // TODO: 2016/6/23 将上一个Ativity传过来的值给已选择城市的View
    public void selectedCity() {
        Intent intent = getIntent();
        String city = intent.getStringExtra("CITY");
        if (city.equals("城市"))
            mSelectedCity.setText("");
        else
            mSelectedCity.setText(city);
    }

    // TODO: 2016/6/23 将点击的List的值给已选城市的View
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CityListAdapter adapter = (CityListAdapter) parent.getAdapter();
        String s = (String) adapter.getItem(position);
        mSelectedCity.setText(s);
        postBackData();
    }

    @Override
    public void onClick(View v) {
        postBackData();
    }

    // TODO: 2016/6/23  回传值
    public void postBackData() {
        int resultCode = 201;
        Intent data = getIntent();
        String city = mSelectedCity.getText().toString();
        if (city.equals(""))
            data.putExtra("MESSAGE", "城市");
        else
            data.putExtra("MESSAGE", city);
        setResult(resultCode, data);
        finish();
    }

    // TODO: 2016/6/23 监听手机自带返回键
    public void onBackPressed() {
        postBackData();
    }
}
