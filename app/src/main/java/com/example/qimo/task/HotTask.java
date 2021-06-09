package com.example.qimo.task;

import android.os.AsyncTask;

import com.example.qimo.bean.Words;
import com.example.qimo.utils.Hot;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

/**
 * Created by Administrator on 2021/4/21.
 */

public class HotTask extends AsyncTask<String,Void,ArrayList<Words>> {
    CallBack back;

    public HotTask(CallBack back) {
        this.back = back;
    }

    /**
     *
     * @param strings:可变参数；参数个数不确定，根据调用该方法时所传数据的个数而定
     *               使用可变参数的格式参照数组。
     * @return
     */
    @Override
    protected ArrayList<Words> doInBackground(String... strings) {
        Response response= Hot.execute(strings[0]);
        try {
            String jsonData=response.body().string();
            ArrayList<Words> result= Hot.parse(jsonData);
            return  result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Words> wordss) {
        super.onPostExecute(wordss);
        if (back!=null)back.getResult(wordss);
    }

    public interface CallBack{
        public void getResult(ArrayList<Words> result);
    }
}




















