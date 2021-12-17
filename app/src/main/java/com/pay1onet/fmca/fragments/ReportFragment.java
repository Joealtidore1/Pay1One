package com.pay1onet.fmca.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.pay1onet.fmca.R;
import com.pay1onet.fmca.adapters.ReportPageAdapter;


public class ReportFragment extends Fragment {

    private View view;
    private TabLayout tabLayout;
    int i;
    private ViewPager viewPager;
    private int currentPage;

    public ReportFragment(int i) {
        this.i = i;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_report, container, false);
       tabLayout = view.findViewById(R.id.tabLayout);
        FragmentManager fm = getChildFragmentManager();
        ReportPageAdapter reportPageAdapter = new ReportPageAdapter(fm,tabLayout.getTabCount());
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(reportPageAdapter);
        //viewPager.setOffscreenPageLimit(0);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPage = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition(), true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(tab.getPosition());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.setCurrentItem(currentPage, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        currentPage = viewPager.getCurrentItem();
    }
}