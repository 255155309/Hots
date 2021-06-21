package com.example.qimo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
//import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.qimo.Fragment.BbsFragment;
import com.example.qimo.Fragment.BlogFragment;
import com.example.qimo.Fragment.MineFragment;
import com.example.qimo.Fragment.HomeFragment;
import com.example.qimo.Fragment.Oldmine;
import com.google.android.material.navigation.NavigationView;

public class Main2Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
//    private TextView tv_username;
//    private Button bt_setting;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_home,rb_blog;
    private String nav;

    private NavigationView navView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    //Frament Object
    private HomeFragment homeFragment;//首页Fragment
    private BlogFragment blogFragment;//发现Fragment
    private MineFragment mineFragment;//定义我的页面Fragment
    private BbsFragment bbsFragment;//定义热点页面Fragment

    private FragmentManager fManager;//用于对Fragment管理
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));

        toolbar= findViewById(R.id.toolbar);
        drawerLayout= findViewById(R.id.drawerLayout);
        setSupportActionBar(toolbar);
        ActionBar bar=getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeAsUpIndicator(R.drawable.ic_menu);
        navView=findViewById(R.id.navView);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.personal_info:
                        Toast.makeText(Main2Activity.this, "个人信息", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.account_safe:
                        break;
                    case R.id.nav_mail:
                        break;
                    case R.id.nav_about:
                        break;
                }
                drawerLayout.closeDrawers();
                item.setCheckable(false);
                return true;
            }
        });

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
        //System.out.println(nav);
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
                    toolbar.setVisibility(View.VISIBLE);//工具栏

                }else{
                    fTransaction.show(mineFragment);
                    toolbar.setVisibility(View.VISIBLE);//工具栏
                }
                break;
        }
        fTransaction.commit();

    }
//隐藏所有框架
    private void hideAllFragment(FragmentTransaction fTransaction) {
        if(homeFragment!=null)fTransaction.hide(homeFragment);
        if(bbsFragment!=null)fTransaction.hide(bbsFragment);
        if(mineFragment!=null)fTransaction.hide(mineFragment);toolbar.setVisibility(View.GONE);
        if(blogFragment!=null)fTransaction.hide(blogFragment);
    }


//    工具栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.END);
                break;
        }
        return true;
    }
}