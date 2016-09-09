package com.example.administrator.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.hot.Hot;
import com.example.administrator.hot.HotGridViewAdapter;
import com.example.administrator.hot_sale.HotSale;
import com.example.administrator.hot_sale.HotSaleViewAdapter;
import com.example.administrator.login.LoginActivity;
import com.example.administrator.quitondoubleclick.QuitOnDoubleClick;
import com.example.administrator.search.SearchShopActivity;
import com.example.administrator.sort.Sort;
import com.example.administrator.sort.SortGridViewAdapter;
import com.example.administrator.sort.SortViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MeiTuanMainActivity extends QuitOnDoubleClick {
    public PopupWindow popupWindow;//弹窗
    public ViewPager mSortViewPager;//将Activity加载到水平滑动布局
    public ImageView mImageViewOne, mImageViewTwo, mImageViewThree,mActionRemind,
             mActionScan;//分别是与水平滑动关联的三个图片指示器、扫描图片按钮、搜索
    public GridView mSortGridView, mHotGridView;//分类网格布局和热门频道网格布局
    public ArrayList<Sort> sortList;//存储分类数据
    public ArrayList<Hot> hotList = new ArrayList<>();//存储热门频道数据
    public ArrayList<HotSale> hotSaleList = new ArrayList<>();//存储热卖数据
    public TextView mHour, mMinute, mSecond, mChooseCity;//分别是:时、分、秒、城市
    public Handler handler = new Handler();//线程之间传值，实现计时
    public List<View> list = new ArrayList<>();//存储分类布局
    public ListView mHotSaleView;//热卖listView
    public LinearLayout mChooseCityLayout,mActionSearch;//选择城市布局按钮,搜索按钮
    int count = 0;   //统计添加数据的个数

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_tuan_mian);

        // TODO: 2016/6/22 选择城市，并传参给城市Activity的当前
        mChooseCity = (TextView) findViewById(R.id.city_choose);
        mChooseCityLayout = (LinearLayout) findViewById(R.id.action_choose_city);
        mChooseCityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = mChooseCity.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("CITY", city);
                intent.setAction("it.intent.action.SENDE");
                startActivityForResult(intent, 101);
            }
        });


        // TODO: 2016/6/22 优惠倒计时器
        mHour = (TextView) findViewById(R.id.hour);
        mMinute = (TextView) findViewById(R.id.minute);
        mSecond = (TextView) findViewById(R.id.second);
        new Thread(new Runnable() {
            int second, minute = 0, hour = 4;

            @Override
            public void run() {
                for (second = 5; second >= 0; second--) {
                    SystemClock.sleep(1000);
                    if (second == 0) {
                        second = 60;
                        minute--;
                    }
                    if (minute == -1) {
                        minute = 59;
                        hour--;
                    }
                    if (hour == -1)
                        hour = 23;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (second < 10)
                                mSecond.setText("0" + second);
                            else
                                mSecond.setText("" + second);

                            if (minute < 10)
                                mMinute.setText("0" + minute);
                            else
                                mMinute.setText("" + minute);

                            if (hour < 10)
                                mHour.setText("0" + hour);
                            else
                                mHour.setText("" + hour);
                        }
                    });
                }
            }
        }).start();


        // TODO: 2016/6/22 分类水平滑动布局
        mImageViewOne = (ImageView) findViewById(R.id.pager_one_brn);
        mImageViewOne.setImageResource(R.drawable.index_category_indicator_selected);
        mImageViewTwo = (ImageView) findViewById(R.id.pager_two_brn);
        mImageViewThree = (ImageView) findViewById(R.id.pager_three_brn);
        mSortViewPager = (ViewPager) findViewById(R.id.pager_view_brn);
        getSortData();
        SortViewAdapter viewAdapter = new SortViewAdapter(list);
        mSortViewPager.setAdapter(viewAdapter);


        // TODO: 2016/6/22  监听水平滑动布局的变动，使图片指示器与其关联
        mSortViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mImageViewOne.setImageResource(R.drawable.index_category_indicator_selected);
                        mImageViewTwo.setImageResource(R.drawable.index_category_indicator_nor);
                        mImageViewThree.setImageResource(R.drawable.index_category_indicator_nor);
                        break;
                    case 1:
                        mImageViewOne.setImageResource(R.drawable.index_category_indicator_nor);
                        mImageViewTwo.setImageResource(R.drawable.index_category_indicator_selected);
                        mImageViewThree.setImageResource(R.drawable.index_category_indicator_nor);
                        break;
                    case 2:
                        mImageViewOne.setImageResource(R.drawable.index_category_indicator_nor);
                        mImageViewTwo.setImageResource(R.drawable.index_category_indicator_nor);
                        mImageViewThree.setImageResource(R.drawable.index_category_indicator_selected);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        // TODO: 2016/6/22  加载热门频道网格布局
        mHotGridView = (GridView) findViewById(R.id.hot_grid_view);
        getHotData();
        HotGridViewAdapter hotGridViewAdapter = new HotGridViewAdapter(this, hotList);
        mHotGridView.setAdapter(hotGridViewAdapter);


        // TODO: 2016/6/22 加载热卖(猜你喜欢)网格布局
        mHotSaleView = (ListView) findViewById(R.id.hot_sale_list_view);
        getHotSaleData();
        HotSaleViewAdapter hotSaleViewAdapter = new HotSaleViewAdapter(this, hotSaleList);
        mHotSaleView.setAdapter(hotSaleViewAdapter);


        // TODO: 2016/6/22 扫描的弹窗
        mActionScan = (ImageView) findViewById(R.id.action_scan);
        popupWindow = new PopupWindow(this);
        getScanWindow();
        mActionScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAsDropDown(v);
                }
            }
        });

        // TODO: 2016/6/23  监听提醒按钮图片并转跳登录界面
        mActionRemind = (ImageView) findViewById(R.id.action_remind);
        mActionRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeiTuanMainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // TODO: 2016/6/24 搜索线型布局按钮
        mActionSearch = (LinearLayout) findViewById(R.id.action_search);
        mActionSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeiTuanMainActivity.this, SearchShopActivity.class);
                startActivity(intent);
            }
        });
    }

    // TODO: 2016/6/23  解析分类布局并在布局上添加数据
    public void sortGridView() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.layout_indicate_one, null);
        list.add(view);
        mSortGridView = (GridView) view.findViewById(R.id.widget_grid_view);
    }

    // TODO: 2016/6/23  解析并加载分类布局到适配器
    public void sortAdapters() {
        SortGridViewAdapter sortGridViewAdapter = new SortGridViewAdapter(MeiTuanMainActivity.this, sortList);
        mSortGridView.setAdapter(sortGridViewAdapter);
    }

    // TODO: 2016/6/23 将分类数据每十个添加到一个网格布局上，并将该布局添加到水平滑动布局上
    public void sortDataAdd(int i, String s) {
        if ((count == 0) || (count % 10 == 0)) {    //每添加十个sort对象，实例化一个新的sortList列表数组
            sortList = new ArrayList<>();
            sortGridView();
        }
        count += 1;

        if (count % 10 == 1)    //每添满个数据，实例化新的sortGridViewAdapter适配器，将sort对象添加该适配器上
            sortAdapters();

        Sort sort = new Sort(i, s);
        sortList.add(sort);
    }

    // TODO: 2016/6/23 分类数据源
    public void getSortData() {
        sortDataAdd(R.drawable.ic_category_0, "美食");
        sortDataAdd(R.drawable.ic_category_1, "电影");
        sortDataAdd(R.drawable.ic_category_82, "酒店");
        sortDataAdd(R.drawable.ic_category_10, "休闲娱乐");
        sortDataAdd(R.drawable.ic_category_34, "外卖");
        sortDataAdd(R.drawable.ic_category_36, "自助餐");
        sortDataAdd(R.drawable.ic_category_3, "KTV");
        sortDataAdd(R.drawable.ic_category_13, "度假出行");
        sortDataAdd(R.drawable.ic_category_11, "丽人");
        sortDataAdd(R.drawable.ic_category_25, "火车票");
        sortDataAdd(R.drawable.ic_category_12, "足疗按摩");
        sortDataAdd(R.drawable.ic_category_6, "周边游");
        sortDataAdd(R.drawable.ic_category_29, "美发");
        sortDataAdd(R.drawable.ic_category_26, "火锅");
        sortDataAdd(R.drawable.ic_category_7, "小吃快餐");
        sortDataAdd(R.drawable.ic_category_33, "甜点饮品");
        sortDataAdd(R.drawable.ic_category_4, "今日新单");
        sortDataAdd(R.drawable.ic_category_16, "景点");
        sortDataAdd(R.drawable.ic_category_2, "洗浴/汗蒸");
        sortDataAdd(R.drawable.ic_category_31, "汽车服务");
        sortDataAdd(R.drawable.ic_category_32, "生日蛋糕");
        sortDataAdd(R.drawable.ic_category_23, "美甲美睫");
        sortDataAdd(R.drawable.ic_category_30, "面部保养");
        sortDataAdd(R.drawable.ic_category_22, "学习培训");
        sortDataAdd(R.drawable.ic_category_27, "家装");
        sortDataAdd(R.drawable.ic_category_37, "母婴亲子");
        sortDataAdd(R.drawable.ic_category_28, "结婚");
        sortDataAdd(R.drawable.ic_category_14, "购物");
        sortDataAdd(R.drawable.ic_category_5, "优惠买单");
        sortDataAdd(R.drawable.ic_category_15, "全部分类");
    }

    // TODO: 2016/6/23 将热门频道添加到hotList中
    public void hotDataAdd(String one_txt, String two_txt, int img) {
        Hot hot = new Hot(one_txt, two_txt, img);
        hotList.add(hot);
    }

    // TODO: 2016/6/23 热门频道数据源
    public void getHotData() {
        hotDataAdd("演出赛事", "精彩不容错过", R.drawable.hot1);
        hotDataAdd("运动健身", "游泳9.9元起", R.drawable.hot2);
        hotDataAdd("装修设计", "全网最低价", R.drawable.hot3);
        hotDataAdd("周边游", "海量景点玩乐", R.drawable.hot4);
        hotDataAdd("结婚", "领百元红包", R.drawable.hot5);
        hotDataAdd("学习培训", "领50元红包", R.drawable.hot6);
        hotDataAdd("儿童摄影", "摄影1折起", R.drawable.hot7);
        hotDataAdd("运动健身", "不一样的自己", R.drawable.hot8);
    }

    // TODO: 2016/6/23 猜你喜欢数据源
    public void getHotSaleData() {
        hotSaleDataAaa(R.mipmap.meituan_image7, R.mipmap.ic_label_nobooking, "港台美食荟", "【天府广场】雪花绵绵冰4选1，提供免费WiFi", changesText0ne("9.9"), changesTextTwo("20元"), "4.9分(68)");
        hotSaleDataAaa(R.mipmap.meituan_image5, R.mipmap.ic_label_nobooking, "芭菲盛宴环球美食", "【孵化园】单人自助午餐，流年忘返美食", changesText0ne("118"), changesTextTwo("148元"), "4.5分(8054)");
        hotSaleDataAaa(R.mipmap.meituan_image2, R.mipmap.ic_label_nobooking, "新石器烤肉", "【春熙路等】代金券1张，全场通用，可叠加使用", changesText0ne("88"), changesTextTwo("多优惠+"), "4.7分(24536)");
        hotSaleDataAaa(R.mipmap.meituan_image1, R.mipmap.ic_label_nobooking, "水果捞", "【春熙路】水果捞特色饮品3选1，提供免费WiFi", changesText0ne("1"), changesTextTwo("28元"), "4.7分(57)");
        hotSaleDataAaa(R.mipmap.meituan_image3, R.mipmap.ic_label_nobooking, "天府火锅", "【天府广场】特色自助火锅，5人以上8折优惠", changesText0ne("30"), changesTextTwo("48元"), "4.6分(2104)");
        hotSaleDataAaa(R.mipmap.meituan_image4, R.mipmap.ic_label_nobooking, "港台美食荟", "【天府广场】雪花绵绵冰4选1，提供免费WiFi", changesText0ne("9.9"), changesTextTwo("20元"), "4.9分(68)");
        hotSaleDataAaa(R.mipmap.meituan_image6, R.mipmap.ic_label_nobooking, "芭菲盛宴环球美食", "【孵化园】单人自助午餐，流年忘返美食", changesText0ne("118"), changesTextTwo("148元"), "4.5分(8054)");
        hotSaleDataAaa(R.mipmap.meituan_image8, R.mipmap.ic_label_nobooking, "新石器烤肉", "【春熙路等】代金券1张，全场通用，可叠加使用", changesText0ne("88"), changesTextTwo("多优惠+"), "4.7分(24536)");
        hotSaleDataAaa(R.mipmap.meituan_image1, R.mipmap.ic_label_nobooking, "水果捞", "【春熙路】水果捞特色饮品3选1，提供免费WiFi", changesText0ne("1"), changesTextTwo("28元"), "4.7分(57)");
        hotSaleDataAaa(R.mipmap.meituan_image3, R.mipmap.ic_label_nobooking, "天府火锅", "【天府广场】特色自助火锅，5人以上8折优惠", changesText0ne("30"), changesTextTwo("48元"), "4.6分(2104)");
    }

    // TODO: 2016/6/23 将猜你喜欢对象加到hotSaleList中
    public void hotSaleDataAaa(int i, int i1, String t, String c, SpannableString p, SpannableString p1, String a) {
        HotSale hotSale = new HotSale(i, i1, t, c, p, p1, a);
        hotSaleList.add(hotSale);
    }

    // TODO: 2016/6/23 修改TextView类型
    public SpannableString changesText0ne(String s) {
        int n = s.length();
        SpannableString spannableString = new SpannableString(s + "元");
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.common_mei_tuan)),
                0, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(25, true),
                0, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),
                0, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
    public SpannableString changesTextTwo(String s) {
        int n = s.length();
        SpannableString spannableString = new SpannableString(s);
        if (s.equals("多优惠+")) {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.common_mei_tuan_p1)),
                    0, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            spannableString.setSpan(new StrikethroughSpan(),
                    0, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    // TODO: 2016/6/23 弹窗
    public void getScanWindow() {
        LayoutInflater inflater = LayoutInflater.from(this);

        // TODO: 2016/6/23  将适配器和数据关联
        View popupContentView = inflater.inflate(R.layout.layout_scan_window, null);

        // TODO: 2016/6/23 弹出适配器中的类容
        popupWindow.setContentView(popupContentView);
        popupWindow.setWidth(380);
        popupWindow.setHeight(350);

        // TODO: 2016/6/23  点击其他位置取消跳窗
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
    }

    // TODO: 2016/6/23 获取选择城市的返回值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String msg = data.getStringExtra("MESSAGE");
        mChooseCity.setText(msg);
    }

}