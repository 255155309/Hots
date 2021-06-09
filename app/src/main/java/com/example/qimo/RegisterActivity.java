package com.example.qimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qimo.Db.RegTask;
import com.example.qimo.Db.Task;
import com.example.qimo.bean.Code;
import com.example.qimo.bean.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private String resultmsg;
    private String resultcode,phone,url;
    private EditText et_account,et_password;
    private Button bt_submit;
    private Button bt_code;
    private EditText et_phone;
    private EditText et_code;

    //private  DBOpenHelpr dbOpener=new DBOpenHelpr(RegisterActivity.this,"Booking.db",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_account= (EditText) findViewById(R.id.et_account);
        et_password= (EditText) findViewById(R.id.et_password);
        bt_submit= (Button) findViewById(R.id.bt_submit);
        et_phone=findViewById(R.id.et_phone);
        et_code=findViewById(R.id.et_code);
        bt_code=findViewById(R.id.bt_code);//验证码按钮
        bt_code.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        //dbOpener.close();
        //finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_code:
                bt_code.setEnabled(false);
                phone=et_phone.getText().toString();
                url="https://www.wiod.cn/sms/send?phone="+phone;
                Toast.makeText(RegisterActivity.this, "验证码已发送！", Toast.LENGTH_SHORT).show();
                new Task(new Task.CallBack() {
                    @Override
                    public void getResult(Code result) {
                        resultcode=result.getCode();
                        System.out.println(resultcode+"-------------------------------");
                    }
                }).execute(url);
                break;
            case R.id.bt_submit:
                String account=et_account.getText().toString();
                String password=et_password.getText().toString();
                String code=et_code.getText().toString();
                phone=et_phone.getText().toString();
                url="https://www.wiod.cn/reg.php?account="+account+"&password="+password+"&phone="+phone;
                if(TextUtils.isEmpty(account)||TextUtils.isEmpty(password)
                        ||TextUtils.isEmpty(code)||TextUtils.isEmpty(phone)){
                    Toast.makeText(RegisterActivity.this, "输入内容不合法！", Toast.LENGTH_SHORT).show();
                }else if(!code.equals(resultcode)){
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }else{
//                    SQLiteDatabase db=dbOpener.getReadableDatabase();
//                    String sql1="select * from user where account = ?";
//                    Cursor cursor=db.rawQuery(sql1,new String[]{account});
                    //cursor:游标，指向结果集第一条数据的前面（上方 -1）
                    new RegTask(new RegTask.CallBack() {
                        @Override
                        public void getResult(User result) {
                            resultmsg=result.getMsg();
                            //Log.i("msg",resultmsg);
                            if(resultmsg.equals("success")){
//                                String sql2="insert into user(account,password) values(?,?)";
//                                db.execSQL(sql2,new String[]{account,password});
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                intent.putExtra("account",account);//传输账号
                                intent.putExtra("password","");//传输账号
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this,resultmsg, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).execute(url);

                    //db.close();
                }
        }
    }
}
