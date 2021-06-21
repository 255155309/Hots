package com.example.qimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qimo.Db.Task;
import com.example.qimo.bean.Code;

public class FindPassword extends AppCompatActivity {
    private EditText find_account,find_code;
    private String account;
    private Button find_ok,find_cancle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        find_account=findViewById(R.id.old_password);
        find_code=findViewById(R.id.new_password);
        find_ok=findViewById(R.id.ok);
        find_cancle=findViewById(R.id.cancle);
        find_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=find_account.getText().toString();//获取账号
                String code=find_cancle.getText().toString();//获取验证码
                String url="https://www.wiod.cn/changepswd/?account="+account+"&oldpassword="+account+"&newpassword="+account;
                if (account.equals("") || code.equals("")){
                    Toast.makeText(FindPassword.this, "所有项不能为空！", Toast.LENGTH_SHORT).show();
                }else if(account.equals(account)){
                    Toast.makeText(FindPassword.this, "新密码不能和原密码相等！", Toast.LENGTH_SHORT).show();
                }else{
                    new Task(new Task.CallBack() {
                        @Override
                        public void getResult(Code result) {
                            String resultmsg=result.getCode();
                            System.out.println(resultmsg);
                            if (resultmsg.equals("0")){
                                Toast.makeText(FindPassword.this, "验证码输入错误！", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(FindPassword.this, "密码修改完成！", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }).execute(url);
                }
            }
        });
        find_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}