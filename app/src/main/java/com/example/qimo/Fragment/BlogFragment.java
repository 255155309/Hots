package com.example.qimo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.example.qimo.BlogAddActivity;
import com.example.qimo.R;
import com.example.qimo.adapter.BlogAdapter;
import com.example.qimo.bean.Blog;
import com.example.qimo.task.BlogTask;

import java.util.ArrayList;

public class BlogFragment extends Fragment {
    private String content;
    private ListView lv_blog;
    private ArrayList<Blog> list=new ArrayList<>();
    private BlogAdapter adapter;
    boolean isLoading=true,isDown=false;
    private String url="https://www.wiod.cn/blog/blog.php";
    public BlogFragment(String content) {this.content=content; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blog, container, false);
        TextView title = view.findViewById(R.id.title);
        //设置顶部导航栏标题
        title.setText(content);

        lv_blog=view.findViewById(R.id.lv_blog);
        loadData();
        adapter=new BlogAdapter(getActivity(),R.layout.blog_item,list);
        lv_blog.setAdapter(adapter);

        TextView blog_add=view.findViewById(R.id.bolg_add);
        blog_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), BlogAddActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    private void loadData() {
        if(isLoading){
            isLoading=false;
            new BlogTask(new BlogTask.CallBack() {
                @Override
                public void getResult(ArrayList<Blog> result) {
                    //list.addAll(result);
                    for(Blog i:result){
                        list.add(0,i);
                    }//遍历列表，最新发布的在最上面
                    adapter.notifyDataSetChanged();
                }
            }).execute(url);
            //curPage++;
            //isLoading=true;
        }
    }
}