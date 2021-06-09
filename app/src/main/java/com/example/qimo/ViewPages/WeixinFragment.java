package com.example.qimo.ViewPages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.qimo.R;
import com.example.qimo.adapter.WordsAdapter;
import com.example.qimo.bean.Words;
import com.example.qimo.task.HotTask;

import java.util.ArrayList;

public class WeixinFragment extends Fragment {
    ListView lv_hotword;
    ArrayList<Words> list=new ArrayList<>();
    WordsAdapter adapter;
    boolean isLoading=true,isDown=false;
    String url="https://v2.alapi.cn/api/tophub/get?token=QpMk5woCZ7AD9Nho&type=weixin";
    int curPage=1;//末尾页数
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_weixin, container, false);

        lv_hotword=view.findViewById(R.id.lv_hotword);
        loadData();
        adapter=new WordsAdapter(getActivity(),R.layout.weibo,list);
        lv_hotword.setAdapter(adapter);
        //lv_hotword.setOnClickListener();//整个列表的点击事件
        //lv_hotword.setOnItemClickListener();//列表项的点击事件
//        lv_hotword.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int i) {
//                if(isDown&&i==SCROLL_STATE_IDLE){
//                    //loadData();
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int i, int i1, int i2) {
//                if(i+i1==i2){
//                    isDown=true;
//                }else{
//                    isDown=false;
//                }
//            }
//        });//列表的滑动事件
//        lv_news=view.findViewById(R.id.lv_news);
//        loadData();
//        adapter=new NewsAdapter(getActivity(),R.layout.item,list);
//        lv_news.setAdapter(adapter);
//        //lv_news.setOnClickListener();//整个列表的点击事件
//        //lv_news.setOnItemClickListener();//列表项的点击事件
//        lv_news.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int i) {
//                if(isDown&&i==SCROLL_STATE_IDLE){
//                    loadData();
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int i, int i1, int i2) {
//                if(i+i1==i2){
//                    isDown=true;
//                }else{
//                    isDown=false;
//                }
//            }
//        });//列表的滑动事件

        return view;
    }
    private void loadData() {
        if(isLoading){
            isLoading=false;
            new HotTask(new HotTask.CallBack() {
                @Override
                public void getResult(ArrayList<Words> result) {
                    list.addAll(result);
                    adapter.notifyDataSetChanged();
                }
            }).execute(url);//url+curPage
            curPage++;
            //isLoading=true;
        }
    }//热搜加载数据
}
