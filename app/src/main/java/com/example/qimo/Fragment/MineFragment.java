package com.example.qimo.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import com.example.qimo.ChangePassword;

import com.example.qimo.LoginActivity;
import com.example.qimo.R;



public class MineFragment extends Fragment {

    private String content  ;
    private TextView mine_name;
    private ImageView h_back;
    private CardView bt_login_out,change_password;
    SharedPreferences spf;
    SharedPreferences.Editor editor;
    public MineFragment(String content) {
        this.content=content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine,container,false);
        //动态注册广播接收器

        //TextView title = (TextView) view.findViewById(R.id.title);
        //h_back=view.findViewById(R.id.h_back);
        //title.setText(content);
//        Handler handler=new Handler(){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//
//            }
//        };
//        //设置头像背景磨砂效果
//        Glide.with(MineFragment.this.getActivity()).load(R.drawable.headpo)
//                .bitmapTransform(new BlurTransformation(MineFragment.this.getActivity(), 25), new CenterCrop(MineFragment.this.getActivity()))
//                .into(h_back);
        spf= PreferenceManager.getDefaultSharedPreferences(getContext());
        String account=spf.getString("account","");
        mine_name=view.findViewById(R.id.mine_name);
        mine_name.setText(account);
        bt_login_out=view.findViewById(R.id.personal_login_out);
        bt_login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor=spf.edit();
                editor.putBoolean("loginState",false);
                editor.apply();
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();

            }
        });
        change_password=view.findViewById(R.id.personal_change_password);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ChangePassword.class);
                startActivity(intent);
            }//单击事件
        });



        return view;
    }

}
