package com.example.qimo.utils;

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

public class Weibo {
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
    public static ArrayList<Words> parse(String data){
        ArrayList<Words> result=new ArrayList<>();
        try {
            JSONObject object=new JSONObject(data);
            JSONArray array=object.getJSONArray("data");
            for(int i=0;i<array.length();i++){
                JSONObject objectIn=array.getJSONObject(i);
                String hot_word=objectIn.getString("hot_word");
                String hot_word_num=objectIn.getString("hot_word_num");
                String hot_word_url=objectIn.getString("url");
                Words words=new Words(hot_word,hot_word_num,hot_word_url);
                result.add(words);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}



























