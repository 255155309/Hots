package com.example.qimo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.qimo.bean.Friend;

public class DBOpenHelpr extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase db;
    //创建数据库
    private DBOpenHelpr dbOpener;
    public DBOpenHelpr(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }
    //在第一次数据库操作时会调用OnCreate()方法,且只执行一次。
    //在OnCreate()方法中一般放创建数据表的代码
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table friend ("
                +"id integer primary key autoincrement,"
                +"name text)";
//        String sql1="create table chat ("
//                +"id integer primary key autoincrement,"
//                +"account text,"
//                +"account text)";
        db.execSQL(sql);
        //db.execSQL(sql1);

        Toast.makeText(context, "数据表创建成功", Toast.LENGTH_SHORT).show();
    }
    //用于升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    //打开数据库
    public void open()throws SQLiteException {
        dbOpener=new DBOpenHelpr(context,"Friend.db",null,1);//创建数据库
        db=dbOpener.getReadableDatabase();
    }
    //查找所有好友
    public Friend[] friendAll() throws SQLiteException{
        Cursor cursor=db.query("friend",null,null,
                null,null,null,null);
        int resultCount=cursor.getCount();
        //判断返回结果，如果结果为0 返回空
        if (resultCount==0 ||!cursor.moveToFirst()){
            return null;//返回空
        }
        Friend[] friends=new Friend[resultCount];//创建数组存储好友
        for(int i=0;i<resultCount;i++){
            friends[i]=new Friend();
            //获取名称
            friends[i].setName(cursor.getString(cursor.getColumnIndex("name")));
            cursor.moveToNext();//下一条记录
        }
        return friends;//返回好友名称的数组
    }


    //查找一个好友
    public Friend[] friendFind(String name) throws SQLiteException{
        //System.out.println(name+"============db");
        open();
        Cursor cursor=db.query("friend",null,
                "name=?",new String[]{name},
                null,null,null);

        int resultCount=cursor.getCount();
        //判断返回结果，如果结果为0 返回空
        if (resultCount==0 ||!cursor.moveToFirst()){
            return null;//返回空
        }
        Friend[] friends=new Friend[resultCount];//创建数组存储好友
        for(int i=0;i<resultCount;i++){
            friends[i]=new Friend();
            //获取名称
            friends[i].setName(cursor.getString(cursor.getColumnIndex("name")));

            cursor.moveToNext();//下一条记录
        }
        return friends;
    }
    //添加一个好友
    public long insertOne(Friend friend){
        open();
        ContentValues values=new ContentValues();
        values.put("name",friend.getName());//设置信息
        //System.out.println(values+"=============================");
        return db.insert("friend",null,values);
    }
    public long delOne(String name){
        open();
        return db.delete("friend","name=?",new String[]{name});
    }
}
