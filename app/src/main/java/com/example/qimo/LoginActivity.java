package com.example.qimo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.qimo.Db.LoginTask;
import com.example.qimo.Fragment.MineFragment;
import com.example.qimo.bean.Loginer;
import com.example.qimo.bean.User;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private String resultmsg;
    private EditText et_account,et_password;
    private CheckBox cb_rember;
    private TextView tv_register;
    private Button bt_login;
    SharedPreferences spf;
    SharedPreferences.Editor editor;
    private DBOpenHelpr dbOpener=new DBOpenHelpr(LoginActivity.this,"Booking.db",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_account= (EditText) findViewById(R.id.et_account);
        et_password= (EditText) findViewById(R.id.et_password);
        cb_rember= (CheckBox) findViewById(R.id.cb_rember);
        tv_register= (TextView) findViewById(R.id.tv_register);
        bt_login= (Button) findViewById(R.id.bt_login);

        spf= PreferenceManager.getDefaultSharedPreferences(this);
        //获取登录状态如果是登录则直接进入软件
        boolean loginState=spf.getBoolean("loginState",false);
        if (loginState){
            Intent intent=new Intent(LoginActivity.this, Main2Activity.class);
            intent.putExtra("userName",spf.getString("account",""));
            startActivity(intent);
            finish();
        }//判断登录状态，如果是 则直接进入，无需登录

        Intent intent=getIntent();
        et_account.setText(intent.getStringExtra("account"));

        boolean is_remember=spf.getBoolean("is_remember",false);
        String account=spf.getString("account","");
        et_account.setText(account);
        if(is_remember){
            String password=spf.getString("password","");

            et_password.setText(password);
            cb_rember.setChecked(is_remember);

        }
        tv_register.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        //头部背景磨砂
        ImageView login_head_bg=findViewById(R.id.login_head_bg);
        Glide.with(LoginActivity.this).load(R.drawable.headpo)
                .bitmapTransform(new BlurTransformation(LoginActivity.this, 25), new CenterCrop(LoginActivity.this))
                .into(login_head_bg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                String account=et_account.getText().toString();
                String password=et_password.getText().toString();
                String url="https://www.wiod.cn/?account="+account+"&password="+password;
                boolean is_remember=cb_rember.isChecked();
                //判断账号密码是否为空
                if(TextUtils.isEmpty(account)|| TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "账号或密码为空！", Toast.LENGTH_SHORT).show();
                }else{
//                    SQLiteDatabase db=dbOpener.getReadableDatabase();
//                    String sql="select * from user where account=? and password=?";
//                    Cursor cursor=db.rawQuery(sql,new String[]{account,password});
                    new LoginTask(new LoginTask.CallBack() {
                        @Override
                        public void getResult(User result) {
                            resultmsg=result.getMsg();
                            if(resultmsg.equals("success")){
                                editor=spf.edit();
                                if (is_remember){
                                    editor.putString("password",password);
                                    editor.putBoolean("is_remember",is_remember);
                                    editor.putBoolean("loginState",true);
                                }else{
                                    editor.remove("is_remember");
                                    editor.remove("password");
                                }
                                editor.putString("account",account);
                                editor.apply();
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                Loginer loginer=new Loginer(account,password);
                                Intent intent=new Intent(LoginActivity.this, Main2Activity.class);
                                intent.putExtra("userName",account);
                                startActivity(intent);
                                finish();

                            }else{
                                //editor.commit();
                                //editor.applt();
                                Toast.makeText(LoginActivity.this, "账号或或密码错误！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).execute(url);

                }
                break;
            case R.id.tv_register:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}