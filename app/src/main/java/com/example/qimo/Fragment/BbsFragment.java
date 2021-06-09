package com.example.qimo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.qimo.R;
import com.example.qimo.ViewPages.BbsPagerAdapter;
import com.example.qimo.ViewPages.WeiboFragment;
import com.example.qimo.ViewPages.WeixinFragment;
import com.example.qimo.ViewPages.ZhihuFragment;
//import com.example.qimo.adapter.NewsAdapter;
//import com.example.qimo.bean.News;
//import com.example.qimo.task.NewsTask;

import java.util.ArrayList;
import java.util.List;


public class BbsFragment extends Fragment {
    private String content;

//ViewPage
//声明ViewPager 和适配器
    private ViewPager viewPager;
    private BbsPagerAdapter pagerAdapter;
    private Fragment WeiboFragment=null;
    private Fragment ZhihuFragment=null;
    private Fragment WeixinFragment=null;
    private LinearLayout dotsLayout;
    private int cur=0;
    private TextView[] dots=new TextView[3];

    public BbsFragment(String content) {
        this.content=content;
    }//系统栏标题

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bbs,container,false);
//        TextView title = (TextView) view.findViewById(R.id.title);
//        title.setText(content);



//        viewPage

        viewPager=view.findViewById(R.id.viewPager);
        initPager();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dotsLayout= view.findViewById(R.id.dotsLayout);
        for(int i=0;i<dots.length;i++){
            dots[i]= (TextView) dotsLayout.getChildAt(i);
            dots[i].setTag(i);
            dots[i].setEnabled(true);
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index= (int) view.getTag();
                    viewPager.setCurrentItem(index);
                    //setCurDot(index);
                }
            });
        }//dots
        dots[cur].setEnabled(false);
        return view;
    }

//数据加载！
//    private void loadData() {
//        if(isLoading){
//            isLoading=false;
//            new NewsTask(new NewsTask.CallBack() {
//                @Override
//                public void getResult(ArrayList<News> result) {
//                    list.addAll(result);
//                    adapter.notifyDataSetChanged();
//                }
//            }).execute(url+curPage);
//            curPage++;
//            isLoading=true;
//        }
//    }
    public void setCurDot(int index){
    dots[index].setEnabled(false);
    dots[cur].setEnabled(true);
    cur=index;
}//viewpage
    public void initPager(){

        viewPager.setCurrentItem(0);//设置第一页默认页
        FragmentManager fm = getFragmentManager() ;
        List<Fragment> fragmentList  = new ArrayList<Fragment>() ;
        if(WeiboFragment==null)
        {
            WeiboFragment =  new WeiboFragment() ;
        }
        if(ZhihuFragment == null )
        {
            ZhihuFragment = new ZhihuFragment() ;
        }
        if(WeixinFragment ==null)
        {
            WeixinFragment = new WeixinFragment() ;
        }
        fragmentList.add(WeiboFragment) ;
        fragmentList.add(ZhihuFragment) ;
        fragmentList.add(WeixinFragment) ;

        pagerAdapter = new BbsPagerAdapter(fm,fragmentList) ;//将数据构造到Adapger中
        viewPager.setAdapter(pagerAdapter); //设置适配器

    }//viewpage

}
