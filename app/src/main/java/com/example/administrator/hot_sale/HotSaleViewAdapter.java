package com.example.administrator.hot_sale;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class HotSaleViewAdapter extends BaseAdapter {
    private List<HotSaleView> list = new ArrayList();
    private LayoutInflater layoutInflater;

    public HotSaleViewAdapter(Context context, List list) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotSaleView hotSaleView;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_mei_tuan_hot_sale, null);
            ImageView iView = (ImageView) convertView.findViewById(R.id.mei_tuan_image_view);
            ImageView i1View = (ImageView) convertView.findViewById(R.id.mei_tuan_image1_view);
            TextView tView = (TextView) convertView.findViewById(R.id.mei_tuan_text_title);
            TextView cView = (TextView) convertView.findViewById(R.id.mei_tuan_text_content);
            TextView pView = (TextView) convertView.findViewById(R.id.mei_tuan_text_price);
            TextView p1View = (TextView) convertView.findViewById(R.id.mei_tuan_text_price1);
            TextView aView = (TextView) convertView.findViewById(R.id.mei_tuan_text_appraise);

            hotSaleView = new HotSaleView();
            hotSaleView.imageView = iView;
            hotSaleView.imageView1 = i1View;
            hotSaleView.title = tView;
            hotSaleView.content = cView;
            hotSaleView.price = pView;
            hotSaleView.price1 = p1View;
            hotSaleView.appraise = aView;
            convertView.setTag(hotSaleView);
        }
        hotSaleView = (HotSaleView) convertView.getTag();
        HotSale item = (HotSale) getItem(position);
        hotSaleView.imageView.setImageResource(item.getImageView());
        hotSaleView.imageView1.setImageResource(item.getImageView1());
        hotSaleView.title.setText(item.getTitle());
        hotSaleView.content.setText(item.getContent());
        hotSaleView.price.setText(item.getPrice());
        hotSaleView.price1.setText(item.getPrice1());
        hotSaleView.appraise.setText(item.getAppraise());
        return convertView;
    }
}
