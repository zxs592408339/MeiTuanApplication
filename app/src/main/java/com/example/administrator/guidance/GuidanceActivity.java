package com.example.administrator.guidance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.TabBarActivity;
import com.example.administrator.sort.SortViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuidanceActivity extends AppCompatActivity {
    public List<View> list = new ArrayList<>();
    public ViewPager mGuidanceViewPager;
    LayoutInflater inflater;
    public ImageView mGuidanceImg, mGuidanceImageViewOne,mGuidanceImageViewTwo,mGuidanceImageViewThree;

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);
        mGuidanceViewPager = (ViewPager) findViewById(R.id.mei_tuan_guidance);

        SortViewAdapter adapter = new SortViewAdapter(list);
        inflater = LayoutInflater.from(this);
        getView();
        View v = inflater.inflate(R.layout.layout_load,null);
        list.add(v);
        mGuidanceViewPager.setAdapter(adapter);

        mGuidanceImageViewOne = (ImageView) findViewById(R.id.guidance_one_brn);
        mGuidanceImageViewOne.setImageResource(R.drawable.wedding_icon_default_dot_pressed);
        mGuidanceImageViewTwo = (ImageView) findViewById(R.id.guidance_two_brn);
        mGuidanceImageViewThree = (ImageView) findViewById(R.id.guidance_three_brn);

        mGuidanceViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        mGuidanceImageViewOne.setImageResource(R.drawable.wedding_icon_default_dot_pressed);
                        mGuidanceImageViewTwo.setImageResource(R.drawable.wedding_icon_default_dot);
                        mGuidanceImageViewThree.setImageResource(R.drawable.wedding_icon_default_dot);
                        break;
                    case 1:
                        mGuidanceImageViewOne.setImageResource(R.drawable.wedding_icon_default_dot);
                        mGuidanceImageViewTwo.setImageResource(R.drawable.wedding_icon_default_dot_pressed);
                        mGuidanceImageViewThree.setImageResource(R.drawable.wedding_icon_default_dot);
                        break;
                    case 2:
                        mGuidanceImageViewOne.setImageResource(R.drawable.wedding_icon_default_dot);
                        mGuidanceImageViewTwo.setImageResource(R.drawable.wedding_icon_default_dot);
                        mGuidanceImageViewThree.setImageResource(R.drawable.wedding_icon_default_dot_pressed);
                        break;
                }

                if (position == 3 ){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(GuidanceActivity.this, TabBarActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },2000);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void gridView(int id) {
        View v = inflater.inflate(R.layout.layout_guidance, null);
        mGuidanceImg = (ImageView) v.findViewById(R.id.guidance_img);
        mGuidanceImg.setImageResource(id);
        list.add(v);
    }

    public void getView(){
        gridView(R.mipmap.mei1);
        gridView(R.mipmap.mei2);
        gridView(R.mipmap.mei3);
    }
}
