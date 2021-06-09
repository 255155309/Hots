package com.example.qimo;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
//import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.qimo.Fragment.BbsFragment;
import com.example.qimo.Fragment.BlogFragment;
import com.example.qimo.Fragment.MineFragment;
import com.example.qimo.Fragment.HomeFragment;

public class Main2Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
//    private TextView tv_username;
//    private Button bt_setting;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_home,rb_blog;
    private String nav;

    //Frament Object
    private HomeFragment homeFragment;//首页Fragment
    private BlogFragment blogFragment;//发现Fragment
    private MineFragment mineFragment;//定义我的页面Fragment
    private BbsFragment bbsFragment;//定义热点页面Fragment
    private View viewHome;//首页布局
    private View viewBbs;//社区布局
    private View viewMine;//我的布局
    private FragmentManager fManager;//用于对Fragment管理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        nav=intent.getStringExtra("nav");
        fManager=getSupportFragmentManager();//
        //获取单选按钮组，设置监听选中事件
        rg_tab_bar=findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，设置为选中状态
        rb_home=findViewById(R.id.rb_home);
        rb_blog=findViewById(R.id.rb_blog);
//        rb_mine=findViewById(R.id.rb_mein);
        System.out.println(nav);
        if (nav==null){
            rb_home.setChecked(true);

        }else if (nav.equals("Blog")){
            rb_blog.setChecked(true);
        }
//        tv_username=findViewById(R.id.tv_username);
//        tv_username.setText("用户名："+intent.getStringExtra("userName"));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //开启一个Fragment事务---每次都必须开启一次
        FragmentTransaction fTransaction=fManager.beginTransaction();
        //隐藏所有的Fragment
        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.rb_home:
                if(homeFragment==null){
                    //如果Fragment为空则创建一个并添加到页面的Fragment中
                    homeFragment=new HomeFragment("Hots");
                    fTransaction.add(R.id.ly_content,homeFragment);
                }else{
                    fTransaction.show(homeFragment);
                }
                break;
            case R.id.rb_blog:
                if(blogFragment==null){
                    blogFragment=new BlogFragment("社区");
                    fTransaction.add(R.id.ly_content,blogFragment);
                }else{
                    fTransaction.show(blogFragment);
                }
                break;
            case R.id.rb_bbs:
                if(bbsFragment==null){
                    bbsFragment=new BbsFragment("热点");
                    fTransaction.add(R.id.ly_content,bbsFragment);
                }else{
                    fTransaction.show(bbsFragment);
                }
                break;
            case R.id.rb_mine:
                if(mineFragment==null){
                    mineFragment=new MineFragment("我的");
                    fTransaction.add(R.id.ly_content,mineFragment);
                }else{
                    fTransaction.show(mineFragment);
                }
                break;
        }
        fTransaction.commit();

    }
//隐藏所有框架
    private void hideAllFragment(FragmentTransaction fTransaction) {
        if(homeFragment!=null)fTransaction.hide(homeFragment);
        if(bbsFragment!=null)fTransaction.hide(bbsFragment);
        if(mineFragment!=null)fTransaction.hide(mineFragment);
        if(blogFragment!=null)fTransaction.hide(blogFragment);
    }
}