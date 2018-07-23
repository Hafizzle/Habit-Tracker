package com.example.hafiz.chartpracticev5.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hafiz.chartpracticev5.Activities.Fragments.MainFragment;
import com.example.hafiz.chartpracticev5.R;

public class SlideAdapter extends FragmentPagerAdapter {


    public SlideAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MainFragment mainFragment = new MainFragment();
                return mainFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}