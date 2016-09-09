package com.example.administrator.hot;

public class Hot{
    private int img;
    private String one_txt,two_txt;
    public Hot(String one_txt,String two_txt, int img){
        this.img=img;
        this.one_txt=one_txt;
        this.two_txt=two_txt;
    }
    public int getImg(){
        return img;
    }

    public String getOne_txt(){
        return one_txt;
    }

    public String getTwo_txt(){
        return two_txt;
    }
}
