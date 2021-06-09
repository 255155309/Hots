package com.example.qimo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qimo.DBOpenHelpr;
import com.example.qimo.Msg.ChatActivity;
import com.example.qimo.R;
import com.example.qimo.bean.Blog;
import com.example.qimo.bean.Friend;
import com.example.qimo.bean.Words;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshilong on 2021/5/21.
 */

public class FriendAdapter extends ArrayAdapter {
    private int item_layout_id;
    private DBOpenHelpr dbOpener;
    private ArrayList<Blog> data;
    public FriendAdapter(@NonNull Context context, int resource, List objects) {
        super(context, resource,objects);
        data= (ArrayList<Blog>) objects;
        item_layout_id=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        final FriendAdapter.ViewHolder holder;
        if(convertView==null){
            view= LayoutInflater.from(getContext())
                    .inflate(item_layout_id,parent,false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else{
            view=convertView;
            holder= (ViewHolder) view.getTag();

        }
        Friend friends= (Friend) getItem(position);
        holder.friend_name.setText(friends.getName());//设置列表好友名
        holder.friend_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ChatActivity.class);
                intent.putExtra("friendName",friends.getName());
                getContext().startActivity(intent);
            }
        });
        //单击删除 删除好友
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //链接数据库
                dbOpener=new DBOpenHelpr(getContext(),"Friend",null,1);
                dbOpener.open();
                //获取当前好友名称
                String name=friends.getName();
                System.out.println(name+"====================");
                //执行删除
                long result=dbOpener.delOne(name);
                if (result>0){
                    Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    data.remove(position);
                    notifyDataSetChanged();
                }

                dbOpener.close();
            }
        });
        return view;
    }
    public class ViewHolder{
        TextView friend_name;
        Button del;
        public ViewHolder(View view){
            friend_name=view.findViewById(R.id.friend_name);
            del=view.findViewById(R.id.del);

        }
    }
}
