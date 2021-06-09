package com.example.qimo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.backup.BackupAgent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qimo.Db.Task;
import com.example.qimo.bean.Blog;
import com.example.qimo.bean.Code;
import com.example.qimo.task.BlogTask;

import java.util.ArrayList;

public class BlogAddActivity extends AppCompatActivity {
    private SharedPreferences spf;
    private EditText blog_content;
    private Button blog_pub;

    //监听返回键，按下返回键触发
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(BlogAddActivity.this,Main2Activity.class);
        intent.putExtra("nav","Blog");
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_add);

        blog_content=findViewById(R.id.blog_content);
        blog_pub=findViewById(R.id.blog_pub);
        spf= PreferenceManager.getDefaultSharedPreferences(BlogAddActivity.this);
        String account=spf.getString("account","");//获取当前登录的账号
        blog_pub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=blog_content.getText().toString();
                String url="https://www.wiod.cn/blog/add.php?account="+account+"&content="+content;
                if (!content.equals("")){
                    new Task(new Task.CallBack() {
                        @Override
                        public void getResult(Code result) {
                            String resultmsg=result.getCode();
                            if (resultmsg.equals("success")){
                                Toast.makeText(BlogAddActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(BlogAddActivity.this,Main2Activity.class);
                                intent.putExtra("nav","Blog");
                                startActivity(intent);
                                finish();
                            }
                        }
                    }).execute(url);
                }else{
                    Toast.makeText(BlogAddActivity.this, "请输入要发表的内容！", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}