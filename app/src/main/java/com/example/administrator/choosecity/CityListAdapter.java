package com.example.administrator.choosecity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CityListAdapter extends BaseAdapter {
    private List<String> list = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public CityListAdapter(Context c, List<String> list) {
        this.context = c;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
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
        String item = (String) getItem(position);
        View v = layoutInflater.inflate(R.layout.layout_list_view, null);
        TextView textView = (TextView) v.findViewById(R.id.text_item_view);
        textView.setText(item);
        return v;
    }
}
