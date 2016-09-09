package com.example.administrator.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.administrator.hot_sale.HotSale;
import com.example.administrator.hot_sale.HotSaleViewAdapter;
import com.example.administrator.quitondoubleclick.QuitOnDoubleClick;

import java.util.ArrayList;
import java.util.List;

public class MeiTuanShopActivity extends QuitOnDoubleClick {
    public TabLayout mTabLayout;
    public ViewPager mViewPager;
    public ListView mAllShopListView,mFavorableShopListView;
    public ArrayList<HotSale> hotSaleList = new ArrayList<>();
    public Spinner mSpinner1, mSpinner2, mSpinner3, mSpinner4;
    public ImageView mShopSearch;
    String[] strings1 = {"美食", "火锅", "自助餐", "西餐", "川菜", "湘菜", "蛋糕甜点"};
    String[] strings2 = {"全城", "金牛区", "武侯区", "青羊区", "成华区", "高新区", "锦江区", "青白江区"};
    String[] strings3 = {"自能排序", "好评优先", "离我最近", "人均最低"};
    String[] strings4 = {"筛选", "价格", "销量", "评价"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_tuan_shop);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.tab_view_pager);

        mShopSearch = (ImageView) findViewById(R.id.shop_search);

        List<View> shopLIst = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        View v1 = inflater.inflate(R.layout.layout_mei_tuan_all_shop,null);
        View v2 = inflater.inflate(R.layout.layout_mei_tuan_favorable_shop,null);
        mAllShopListView = (ListView) v1.findViewById(R.id.all_shop_list_view);
        mFavorableShopListView = (ListView) v2.findViewById(R.id.favorable_shop_list_view);
        shopLIst.add(v1);
        shopLIst.add(v2);

        MyPagerViewAdapter adapter = new MyPagerViewAdapter(shopLIst);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        getAdapter();

        getHotSaleData();
        HotSaleViewAdapter hotSaleViewAdapter = new HotSaleViewAdapter(this,hotSaleList);
        mAllShopListView.setAdapter(hotSaleViewAdapter);
        mFavorableShopListView.setAdapter(hotSaleViewAdapter);
    }


    public void getAdapter(){
        adapterData(mSpinner1,R.id.mei_tuan_spinner_one,strings1);
        adapterData(mSpinner2,R.id.mei_tuan_spinner_two,strings2);
        adapterData(mSpinner3,R.id.mei_tuan_spinner_three,strings3);
        adapterData(mSpinner4,R.id.mei_tuan_spinner_four,strings4);
    }

    public void adapterData(Spinner spinner ,int id ,  String[] strings){
        spinner = (Spinner) findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.layout_list_view, strings);
        spinner.setAdapter(adapter);
    }



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

    public void hotSaleDataAaa(int i, int i1, String t, String c, SpannableString p, SpannableString p1, String a) {
        HotSale mainIntentBean = new HotSale(i, i1, t, c, p, p1, a);
        hotSaleList.add(mainIntentBean);
    }

    public class MyPagerViewAdapter extends PagerAdapter {
        String[] strings ={"全部商家","优惠商家"};
        List<View> list = new ArrayList<>();

        public MyPagerViewAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = list.get(position);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = list.get(position);
            container.removeView(v);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return strings[position];
        }
    }

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
}
