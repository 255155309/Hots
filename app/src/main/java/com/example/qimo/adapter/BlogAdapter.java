package com.example.qimo.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qimo.BlogAddActivity;
import com.example.qimo.ContentActivity;
import com.example.qimo.Db.Task;
import com.example.qimo.R;
import com.example.qimo.bean.Blog;
import com.example.qimo.bean.Code;
import com.example.qimo.bean.Words;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshilong on 2021/5/21.
 */

public class BlogAdapter extends ArrayAdapter {
    private int item_layout_id;
    private String account;
    private SharedPreferences spf;
    private ArrayList<Blog> data;
    public BlogAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        data= (ArrayList<Blog>) objects;
        item_layout_id=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        final ViewHolder holder;
        if(convertView==null){
            view= LayoutInflater.from(getContext())
                    .inflate(item_layout_id,parent,false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else{
            view=convertView;
            holder= (ViewHolder) view.getTag();

        }
        Blog blogs= (Blog) getItem(position);
        holder.blog_name.setText(blogs.getBlogName());
        holder.blog_content.setText(blogs.getBlogContent());
        holder.blog_time.setText(blogs.getBlogTime());
        //如果是自己发的就显示删除按钮
        if (blogs.getBlogName().equals(account)){
            holder.del.setVisibility(view.VISIBLE);
        }
        //删除blog
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.wiod.cn/blog/del.php?id="+blogs.getId();
                new Task(new Task.CallBack() {
                    @Override
                    public void getResult(Code result) {
                        Toast.makeText(getContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                        data.remove(position);
                        notifyDataSetChanged();
                    }
                }).execute(url);
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "您点击了评论", Toast.LENGTH_SHORT).show();
            }
        });
        //final String hot_word_url=words.getHot_word_url();
        //单击热搜时打开
//        holder.blog_content.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                if(!TextUtils.isEmpty(hot_word_url)){
//                    Intent intent = new Intent(getContext(), ContentActivity.class);
//                    intent.putExtra("url",hot_word_url);
//                    getContext().startActivity(intent);
//                }else{
//                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });

        return view;
    }
    public class ViewHolder{
        TextView blog_name,blog_content,comment,del,blog_time;
        public ViewHolder(View view){
            blog_name=view.findViewById(R.id.blog_name);
            blog_content=view.findViewById(R.id.blog_content);
            comment=view.findViewById(R.id.comment);
            del=view.findViewById(R.id.del);
            blog_time=view.findViewById(R.id.blog_time);
            spf= PreferenceManager.getDefaultSharedPreferences(getContext());
            account=spf.getString("account","");//获取当前登录的账号
        }
    }
}



















