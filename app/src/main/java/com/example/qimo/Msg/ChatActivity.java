package com.example.qimo.Msg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qimo.DBOpenHelpr;
import com.example.qimo.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private ArrayList<Msg> msgList=new ArrayList<Msg>();
    private MsgAdapter adapter=null;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Button send;
    private EditText inputText;
    private TextView contacts_title;
    private DBOpenHelpr dbOpener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initMsg();
        layoutManager =new LinearLayoutManager(ChatActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        if(adapter==null){
            adapter=new MsgAdapter(msgList);
        }
        recyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    Msg msg =new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1); //当有新消息时，刷新RecyclerView中的显示
                    recyclerView.scrollToPosition(msgList.size() - 1);// 将 RecyclerView定位到最后一行
                    inputText.setText("");// 清空输入框中的内容
                }
            }
        });
    }

    private void initMsg() {
        Msg msg1 =new Msg("Hello.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 =new Msg("你好！", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("yup. ", Msg.TYPE_RECEIVED);
        msgList.add(msg3);

        recyclerView=findViewById(R.id.recyclerView);
        send=findViewById(R.id.send);
        inputText=findViewById(R.id.inputText);
        contacts_title=findViewById(R.id.contacts_title);
        Intent intent=getIntent();
        //设置上方联系人名称
        contacts_title.setText(intent.getStringExtra("friendName"));

        dbOpener=new DBOpenHelpr(ChatActivity.this,"Friend.db",null,1);//用于数据库处理
        dbOpener.open();//打开数据库

    }
}