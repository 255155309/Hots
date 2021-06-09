package com.example.qimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qimo.adapter.FriendAdapter;
import com.example.qimo.bean.Friend;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private EditText et_search;
    private Button bt_friend_add;
    private DBOpenHelpr dbOpener;
    private ListView Search_list;
    private TextView back;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(SearchActivity.this,Main2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();

        //设置取消按钮，返回主页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });

        //添加好友
        bt_friend_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_search.getText().toString();
                if(!name.equals("")){
                    Friend friend=new Friend();
                    friend.setName(name);
                    long result=dbOpener.insertOne(friend);
                    if (result<0){
                        Toast.makeText(SearchActivity.this, "添加失败！", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SearchActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                        et_search.setText("");
                    }
                }else{
                    Toast.makeText(SearchActivity.this, "请输入信息！", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //搜索框内容改变时查找，实时查找
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name=(et_search.getText().toString()).trim();
                if(!name.equals("")) {
                    //System.out.println(name + "===========search");
                    querry(name);
                }else{
                    querry("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dbOpener.close();

    }
    //初始化
    private void initView() {
        dbOpener=new DBOpenHelpr(SearchActivity.this,"Friend.db",null,1);//用于数据库处理
        et_search=findViewById(R.id.et_search);//编辑框
        back=findViewById(R.id.back);//返回按钮
        bt_friend_add=findViewById(R.id.bt_friend_add);//添加按钮
        Search_list=findViewById(R.id.Search_list);//查找页面的列表，用于显示查找返回的好友
        //输入框获取焦点
        et_search.setFocusable(true);
        et_search.setFocusableInTouchMode(true);
        et_search.requestFocus();
        //弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    //查找好友
    public void querry(String name){
        //执行sql语句 获取返回结果，返回结果为Friend类型
        Friend[] friends=dbOpener.friendFind(name);
        //新建数组存储好友，用于更新setAdapter
        ArrayList<Friend> friendListSearch=new ArrayList<Friend>();
        if (friends==null){
            FriendAdapter friendAdapter=new FriendAdapter(SearchActivity.this,
                    R.layout.friend_item,friendListSearch);
            Search_list.setAdapter(friendAdapter);
            return;
        }
        //遍历列表
        for(Friend friend:friends){
            //得到名称-》设置名称
            Friend friendOne=new Friend(friend.getName());
            //System.out.println(friend.getName());
            friendListSearch.add(friendOne);//添加到好友列表
        }
        FriendAdapter friendAdapter=new FriendAdapter(SearchActivity.this,
                R.layout.friend_item,friendListSearch);
        Search_list.setAdapter(friendAdapter);
    }

}