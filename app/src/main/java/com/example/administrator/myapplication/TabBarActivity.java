package com.example.administrator.myapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class TabBarActivity extends TabActivity {
    public ImageView mImageView;
    public TextView mTextView;
    private TabHost mTabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_bar);
        mTabHost = getTabHost();
        getTabBar();
    }

    public <T> void addData(int img, String string, Class<T> t) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.layout_tab_bar, null);
        mImageView = (ImageView) view.findViewById(R.id.tab_bar_image);
        mTextView = (TextView) view.findViewById(R.id.tab_bar_text);
        mImageView.setImageResource(img);
        mTextView.setText(string);
        TabHost.TabSpec tabSpec = mTabHost.newTabSpec("tab");
        tabSpec.setIndicator(view);
        tabSpec.setContent(new Intent(this, t));
        mTabHost.addTab(tabSpec);
    }

    public void getTabBar() {
        addData(R.drawable.menu_deal, "首页", MeiTuanMainActivity.class);
        addData(R.drawable.menu_poi, "商家", MeiTuanShopActivity.class);
        addData(R.drawable.menu_user, "我的", MeiTuanMySelfActivity.class);
        addData(R.drawable.menu_more, "更多", MeiTuanMoreActivity.class);
    }
}
