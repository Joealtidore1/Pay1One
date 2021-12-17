package com.pay1onet.fmca.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pay1onet.fmca.fragments.HistoryFragment;
import com.pay1onet.fmca.fragments.RangeFragment;
import com.pay1onet.fmca.fragments.SearchFragment;

public class ReportPageAdapter extends FragmentPagerAdapter {
    int numOfTabs;
    public ReportPageAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=null;
                fragment= new HistoryFragment();
                break;
            case 1:
                fragment=null;
                fragment = new SearchFragment();
                break;
            /*case 2:
                fragment = new RangeFragment();
                break;*/
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }


}
