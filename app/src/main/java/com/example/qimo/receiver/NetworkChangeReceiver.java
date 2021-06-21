package com.example.qimo.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * 定义接收器（相当于收音机）类
 * Created by Administrator on 2021/6/2.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    //在接受到广播时，会执行该方法
    @Override
    public void onReceive(final Context context, Intent intent) {
        Toast.makeText(context, "网络状态发生变化！", Toast.LENGTH_SHORT).show();
        //context.getSystemService();调用系统服务
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();

        if (info!=null&&info.isAvailable()) {
            Log.i("网络信息------"+info.getType()+":",info.getTypeName());
            if (info.getType() != ConnectivityManager.TYPE_WIFI) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("网络设置")
                        .setMessage("当前WIFI不可用，是否进行网络设置？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
                                context.startActivity(intent);
                            }
                        });
                builder.create().show();
            }
        }
    }
}
