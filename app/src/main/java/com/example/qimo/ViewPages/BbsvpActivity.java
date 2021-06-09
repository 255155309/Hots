package com.example.qimo.ViewPages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qimo.MyPageAdapter;
import com.example.qimo.R;

import java.util.ArrayList;

public class BbsvpActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<View> views=new ArrayList<>();
    private MyPageAdapter adapter;

    private LinearLayout dotsLayout;
    private ImageView[] dots=new ImageView[3];

    private int cur=0;
    private int time=5;
    private boolean flag=true;

    private int[]imgIds=new int[]{R.drawable.demo1,R.drawable.demo2,R.drawable.demo3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbsvp);

        viewPager= findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for(int i=0;i<imgIds.length;i++){
            ImageView view=new ImageView(this);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setImageResource(imgIds[i]);
            views.add(view);//将图片添加到views数组
        }
        adapter=new MyPageAdapter(views);
        viewPager.setAdapter(adapter);

        dotsLayout= (LinearLayout) findViewById(R.id.dotsLayout);
        for(int i=0;i<dots.length;i++){
            dots[i]= (ImageView) dotsLayout.getChildAt(i);
            dots[i].setTag(i);
            dots[i].setEnabled(true);
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index= (int) view.getTag();
                    viewPager.setCurrentItem(index);
                    //setCurDot(index);
                }
            });
        }
        dots[cur].setEnabled(false);
    }
    public void setCurDot(int index){
        dots[index].setEnabled(false);
        dots[cur].setEnabled(true);
        cur=index;
    }
}