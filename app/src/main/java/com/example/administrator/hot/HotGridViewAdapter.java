package com.example.administrator.hot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;

public class HotGridViewAdapter extends BaseAdapter {
    ArrayList list = new ArrayList<>();
    LayoutInflater layoutInflater;

    public HotGridViewAdapter(Context context, ArrayList list) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

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
    public View getView(int position, View view, ViewGroup viewGroup) {
        HotView hotView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout_hot, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.hot_grid_image_view);
            TextView textOneView = (TextView) view.findViewById(R.id.grid_hot_text_one_view);
            TextView textTwoView = (TextView) view.findViewById(R.id.grid_hot_text_two_view);
            hotView = new HotView();
            hotView.imageView = imageView;
            hotView.text_one_View = textOneView;
            hotView.text_two_View = textTwoView;
            view.setTag(hotView);
        }
        hotView = (HotView) view.getTag();
        Hot item = (Hot) getItem(position);

        hotView.imageView.setImageResource(item.getImg());
        hotView.text_one_View.setText(item.getOne_txt());
        hotView.text_two_View.setText(item.getTwo_txt());
        return view;
    }
}
