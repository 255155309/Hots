package com.example.qimo;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
//处理引导页面
public class MyPageAdapter extends PagerAdapter {
    //1、定义一个属性，并在构造方法中对它初始化
    private ArrayList<View> views;//存放侧滑容器中的视图

    public MyPageAdapter(ArrayList<View> views) {
        this.views = views;
    }
    //2、重写4个方法
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }
}
