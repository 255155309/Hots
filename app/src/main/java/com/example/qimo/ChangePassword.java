package com.example.qimo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qimo.Db.Task;
import com.example.qimo.bean.Code;

public class ChangePassword extends AppCompatActivity {
    private SharedPreferences spf;
    private EditText old_password,new_password;
    private String oldPassword,newPassword,account;
    private Button ok,cancle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        old_password=findViewById(R.id.old_password);
        new_password=findViewById(R.id.new_password);
        ok=findViewById(R.id.ok);
        cancle=findViewById(R.id.cancle);
        spf= PreferenceManager.getDefaultSharedPreferences(this);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account=spf.getString("account","");
                oldPassword=old_password.getText().toString();
                newPassword=new_password.getText().toString();
                String url="https://www.wiod.cn/changepswd/?account="+account+"&oldpassword="+oldPassword+"&newpassword="+newPassword;
                if (oldPassword.equals("") || newPassword.equals("")){
                    Toast.makeText(ChangePassword.this, "所有项不能为空！", Toast.LENGTH_SHORT).show();
                }else if(oldPassword.equals(newPassword)){
                    Toast.makeText(ChangePassword.this, "新密码不能和原密码相等！", Toast.LENGTH_SHORT).show();
                }else{
                    new Task(new Task.CallBack() {
                        @Override
                        public void getResult(Code result) {
                            String resultmsg=result.getCode();
                            System.out.println(resultmsg);
                            if (resultmsg.equals("0")){
                                Toast.makeText(ChangePassword.this, "原密码输入错误！", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ChangePassword.this, "密码更改成功！", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }).execute(url);
                }
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
