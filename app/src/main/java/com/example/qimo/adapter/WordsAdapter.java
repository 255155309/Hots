package com.example.qimo.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qimo.ContentActivity;
import com.example.qimo.R;
import com.example.qimo.bean.Words;

import java.util.List;

/**
 * Created by Administrator on 2021/4/21.
 */

public class WordsAdapter extends ArrayAdapter {
    private int item_layout_id;
    public WordsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
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
        Words words= (Words) getItem(position);
        int num=position+1;
        holder.hot_word.setText(num+"、"+words.getHot_word());
        holder.hot_word_num.setText(" "+words.getHot_word_num());


        final String hot_word_url=words.getHot_word_url();
        //单击热搜时打开
        holder.hot_word.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!TextUtils.isEmpty(hot_word_url)){
                    Intent intent = new Intent(getContext(), ContentActivity.class);
                    intent.putExtra("url",hot_word_url);
                    getContext().startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                }

                
            }
        });

        return view;
    }
    public class ViewHolder{
        TextView hot_word;
        TextView hot_word_num;
        public ViewHolder(View view){
            hot_word=view.findViewById(R.id.hot_word);
            hot_word_num=view.findViewById(R.id.hot_word_num);


        }
    }
}



















