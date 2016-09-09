package com.example.administrator.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;

public class SearchShopActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    public ArrayList<String> searchList = new ArrayList<>();
    public GridView mSearchGrid;
    public EditText mSearchEdit;
    public ImageView mSearchBack, mSearchDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shop);

        mSearchEdit = (EditText) findViewById(R.id.search_edit_tet);

        mSearchDelete = (ImageView) findViewById(R.id.search_delete);
        mSearchDelete.setOnClickListener(this);

        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0)
                    mSearchDelete.setImageResource(R.drawable.takeout_icon_delete_text);
                else
                    mSearchDelete.setImageResource(R.drawable.bg_btn_movie_top_movielist_normal);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSearchGrid = (GridView) findViewById(R.id.search_grid_view);
        mSearchGrid.setOnItemClickListener(this);

        mSearchBack = (ImageView) findViewById(R.id.search_img_back);
        mSearchBack.setOnClickListener(this);

        getSearchData();
        SearchGridViewAdapter adapter = new SearchGridViewAdapter(this, searchList);
        mSearchGrid.setAdapter(adapter);
    }

    public void getSearchData() {
        searchList.add("华莱士");
        searchList.add("德克士");
        searchList.add("乡村基");
        searchList.add("烤匠");
        searchList.add("廖记棒棒鸡");
        searchList.add("麦的多");
        searchList.add("一只酸牛奶");
        searchList.add("赛江南");
        searchList.add("大通冰室");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SearchGridViewAdapter adapter = (SearchGridViewAdapter) parent.getAdapter();
        String s = adapter.getItem(position).toString();
        mSearchEdit.setText(s);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_img_back:
                finish();
                break;
            case R.id.search_delete:
                mSearchEdit.setText("");
                break;
        }

    }
}
