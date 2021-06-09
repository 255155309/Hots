package com.example.qimo.task;

import android.os.AsyncTask;

import com.example.qimo.Db.Utils;
import com.example.qimo.bean.Blog;
import com.example.qimo.utils.Hot;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

/**
 * Created by Administrator on 2021/5/16.
 */

public class BlogTask extends AsyncTask<String,Void,ArrayList<Blog>> {
    CallBack back;

    public BlogTask(CallBack back) {
        this.back = back;
    }

    /**
     *
     * @param strings:可变参数；参数个数不确定，根据调用该方法时所传数据的个数而定
     *               使用可变参数的格式参照数组。
     * @return
     */
    @Override
    protected ArrayList<Blog> doInBackground(String... strings) {
        Response response= Hot.execute(strings[0]);
        try {
            String jsonData=response.body().string();
            ArrayList<Blog> result= Utils.blog(jsonData);
            return  result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Blog> blogs) {
        super.onPostExecute(blogs);
        if (back!=null)back.getResult(blogs);
    }

    public interface CallBack{
        public void getResult(ArrayList<Blog> result);
    }
}




















