package com.example.qimo.Db;

import android.util.Log;

import com.example.qimo.bean.Blog;
import com.example.qimo.bean.Code;
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

public class Utils {
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

    public static Code code(String data){
        Code code=new Code();
        try {
            JSONObject object=new JSONObject(data);
            String code1=object.getString("code");
            code.setCode(code1);
            System.out.println(code1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }
    public static ArrayList<Blog> blog(String data){
        ArrayList<Blog> result=new ArrayList<>();
        try {
            JSONObject object=new JSONObject(data);
            JSONArray array = object.getJSONArray("data");
            for(int i=0;i<array.length();i++) {
                JSONObject objectIn = array.getJSONObject(i);
                String id = objectIn.getString("id");
                String blogName = objectIn.getString("blogName");
                String blogContent = objectIn.getString("blogContent");
                String blogTime = objectIn.getString("blogTime");
                Blog blog=new Blog(id,blogName,blogContent,blogTime);
                result.add(blog);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}



























