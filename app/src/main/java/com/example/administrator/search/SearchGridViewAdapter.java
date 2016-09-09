package com.example.administrator.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;

public class SearchGridViewAdapter extends BaseAdapter {
    ArrayList list = new ArrayList<>();
    LayoutInflater layoutInflater;

    public SearchGridViewAdapter(Context context, ArrayList list) {
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
        String item = (String)getItem(position);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout_search, null);
            TextView v = (TextView) view.findViewById(R.id.search_txt);
            view.setTag(v);
        }
       TextView v = (TextView) view.getTag();
        v.setText(item);
        return view;
    }
}
