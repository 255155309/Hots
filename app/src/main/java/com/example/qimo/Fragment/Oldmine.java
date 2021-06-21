package com.example.qimo.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.qimo.R;
import com.google.android.material.navigation.NavigationView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class Oldmine extends AppCompatActivity {
    private NavigationView navView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mine);

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
                        Toast.makeText(Oldmine.this, "call", Toast.LENGTH_SHORT).show();
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
        //设置头像背景磨砂效果
        ImageView h_back=findViewById(R.id.h_back);
        Glide.with(this).load(R.drawable.headpo)
                .bitmapTransform(new BlurTransformation(this, 25),
                        new CenterCrop(this))
                .into(h_back);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
//            case android.R.id.home:
//                drawerLayout.openDrawer(GravityCompat.END);
//                break;
//            case R.id.backup:
//                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.delete:
//                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.settings:
//                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
//                break;
        }
        return true;
    }

}
