package com.example.qimo.Db;

import android.os.AsyncTask;

import com.example.qimo.bean.Code;


import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2021/4/21.
 */

public class Task extends AsyncTask<String,Void, Code> {
    CallBack back;

    public Task(CallBack back) {
        this.back = back;
    }

    /**
     *
     * @param strings:可变参数；参数个数不确定，根据调用该方法时所传数据的个数而定
     *               使用可变参数的格式参照数组。
     * @return
     */
    @Override
    protected Code doInBackground(String... strings) {
        Response response= Utils.execute(strings[0]);
        try {
            String jsonData=response.body().string();
            Code result= Utils.code(jsonData);
            return  result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Code code) {
        super.onPostExecute(code);
        if (back!=null)back.getResult(code);
    }

    public interface CallBack{
        public void getResult(Code result);
    }
}




















