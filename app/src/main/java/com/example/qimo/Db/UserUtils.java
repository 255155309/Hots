package com.example.qimo.Db;

import android.util.Log;

import com.example.qimo.bean.User;
import com.example.qimo.bean.Words;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2021/4/21.
 */

public class UserUtils {
    public static OkHttpClient client=new OkHttpClient();
    /**
     * 使用OKHttp访问网络，返回一个响应
     * @param url：网络地址
     * @return：Response响应
     */
    public static Response execute(String url){
        Request request=new Request.Builder()
                .url(url)
                .build();
        try {
            Response response=client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对json数据进行解析
     * @param data
     * @return  ArrayList<News>
     */
    public static User login(String data){
        User user=new User(null,null,null);
        try {
            JSONObject object=new JSONObject(data);
            String msg=object.getString("msg");
            user.setMsg(msg);
            if (msg.equals("success")){
                JSONArray array=object.getJSONArray("data");
                JSONObject objectIn=array.getJSONObject(0);
                String account=objectIn.getString("account");
                String phone=objectIn.getString("phone");
                user.setAccount(account);
                user.setPhone(phone);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
    public static User reg(String data){
        User user=new User(null,null,null);
        try {
            JSONObject object=new JSONObject(data);
            String msg=object.getString("msg");
            user.setMsg(msg);
            if (msg.equals("success")){
                JSONArray array=object.getJSONArray("data");
                JSONObject objectIn=array.getJSONObject(0);
                String account=objectIn.getString("account");
                String phone=objectIn.getString("phone");
                user.setAccount(account);
                user.setPhone(phone);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}



























