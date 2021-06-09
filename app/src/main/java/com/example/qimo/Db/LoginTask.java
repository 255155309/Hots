package com.example.qimo.Db;

import android.os.AsyncTask;

import com.example.qimo.Db.UserUtils;
import com.example.qimo.bean.User;
import com.example.qimo.bean.Words;
import com.example.qimo.utils.Hot;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

/**
 * Created by wenshilong on 2021/4/21.
 */

public class LoginTask extends AsyncTask<String,Void,User> {
    CallBack back;

    public LoginTask(CallBack back) {
        this.back = back;
    }

    /**
     *
     * @param strings:可变参数；参数个数不确定，根据调用该方法时所传数据的个数而定
     *               使用可变参数的格式参照数组。
     * @return
     */
    @Override
    protected User doInBackground(String... strings) {
        Response response= UserUtils.execute(strings[0]);
        try {
            String jsonData=response.body().string();
            User result= UserUtils.login(jsonData);
            return  result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        if (back!=null)back.getResult(user);
    }

    public interface CallBack{
        public void getResult(User result);
    }
}




















