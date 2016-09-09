package com.example.administrator.sort;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/20.
 */
public class SortGridViewAdapter  extends BaseAdapter {
    ArrayList list = new ArrayList<>();
    LayoutInflater layoutInflater;

    public SortGridViewAdapter(Context context, ArrayList list) {
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
        SortView holdView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout_image_grid, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.grid_image_view);
            TextView textView = (TextView) view.findViewById(R.id.grid_text_view);
            holdView = new SortView();
            holdView.imageView = imageView;
            holdView.textView = textView;
            view.setTag(holdView);
        }
        holdView = (SortView) view.getTag();
        Sort item = (Sort) getItem(position);

        holdView.imageView.setImageResource(item.getPic());
        holdView.textView.setText(item.getTxt());
        return view;
    }
}
