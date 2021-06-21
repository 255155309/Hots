package com.example.qimo.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.qimo.DBOpenHelpr;
import com.example.qimo.R;
import com.example.qimo.SearchActivity;
import com.example.qimo.adapter.FriendAdapter;
import com.example.qimo.bean.Friend;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private String content;
    private ListView friend_list;
    private EditText et_find;
    SharedPreferences spf;
//    private SQLiteDatabase db;
//    //创建数据库
    private DBOpenHelpr dbOpener;
    public HomeFragment(String content) {
        this.content=content;
    }//系统栏标题

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        TextView title = view.findViewById(R.id.title);
        //设置顶部导航栏标题
        title.setText(content);

        friend_list=view.findViewById(R.id.friend_list);//好友列表
        et_find=view.findViewById(R.id.et_find);//搜索框

       //设置搜索栏失去焦点
        et_find.setFocusable(false);
        //单击搜索框，跳转到搜索页面
        et_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
            }
        });

        spf= PreferenceManager.getDefaultSharedPreferences(getContext());
        //String account=spf.getString("account","");//获取当前登录的账号
        dbOpener=new DBOpenHelpr(getContext(),"Friend.db",null,1);//用于数据库处理
        dbOpener.open();//打开数据库
        //显示所有好友
        friendAll();

        dbOpener.close();//关闭数据库
        return view;
    }

    //显示所有好友
    private void friendAll() {
        Friend[] friends=dbOpener.friendAll();
        //新建数组存储好友
        ArrayList<Friend> friendList=new ArrayList<Friend>();
        if (friends==null){
            FriendAdapter friendAdapter=new FriendAdapter(getContext(),
                    R.layout.friend_item,friendList);
            friend_list.setAdapter(friendAdapter);
            return;
        }
        //遍历列表
        for(Friend friend:friends){
            //得到名称-》设置名称
            Friend friendOne=new Friend(friend.getName());
            //System.out.println(friend.getName());
            friendList.add(friendOne);//添加到好友列表
        }
        FriendAdapter friendAdapter=new FriendAdapter(getContext(),
                R.layout.friend_item,friendList);
        friend_list.setAdapter(friendAdapter);

    }

}
