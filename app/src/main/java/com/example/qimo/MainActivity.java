package com.example.qimo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<View> views=new ArrayList<>();
    private MyPageAdapter adapter;

    private TextView tv_time;
    private LinearLayout dotsLayout;
    private ImageView[] dots=new ImageView[3];

    private int cur=0;
    private int time=5;
    private boolean flag=true;

    SharedPreferences spf;
    SharedPreferences.Editor editor;

    private int[]imgIds=new int[]{R.drawable.demo1,R.drawable.demo2,R.drawable.demo3};

    //异步消息处理机制。1、在主线程创建Handler类的对象
    android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {//子线程发送的消息传送到该方法的参数中
            super.handleMessage(msg);
            //更新UI

            tv_time.setText("倒计时" + time + "s");
            int index = (viewPager.getCurrentItem() + 1) % 3;
            viewPager.setCurrentItem(index);
            if (time==0){
                flag=false;
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        tv_time= (TextView) findViewById(R.id.tv_time);
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

        //第一次打开显示引导图
        spf= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        boolean isFirst=spf.getBoolean("isFirst",true);
        if(isFirst){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(flag){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        time--;
                        Message message=new Message();
                        message.what=1;
                        handler.sendMessage(message);
                    }

                }
            }).start();
            editor=spf.edit();
            editor.putBoolean("isFirst",false);
            editor.apply();
        }else{
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void setCurDot(int index){
        dots[index].setEnabled(false);
        dots[cur].setEnabled(true);
        cur=index;
    }

}
