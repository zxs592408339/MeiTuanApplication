package com.example.administrator.hot_sale;

import android.text.SpannableString;

public class HotSale {
    private String title, content, appraise;
    private int imageView, imageView1;
    private SpannableString price, price1;

    public HotSale(int i, int i1, String t, String c, SpannableString p, SpannableString p1, String a) {
        title = t;
        content = c;
        price = p;
        price1 = p1;
        appraise = a;
        imageView = i;
        imageView1 = i1;
    }

    public SpannableString getPrice1() {
        return price1;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAppraise() {
        return appraise;
    }

    public int getImageView() {
        return imageView;
    }

    public int getImageView1() {
        return imageView1;
    }

    public SpannableString getPrice() {
        return price;
    }
}
