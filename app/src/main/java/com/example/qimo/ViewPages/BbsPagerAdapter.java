package com.example.qimo.ViewPages;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class BbsPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments ;


    public BbsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public BbsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {

        this(fm) ;
        this.fragments = fragments ;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}