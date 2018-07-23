package com.example.hafiz.chartpracticev5.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.hafiz.chartpracticev5.Activities.Fragments.ChartFragment;
import com.example.hafiz.chartpracticev5.Activities.Fragments.MainFragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentPageAdapter extends FragmentPagerAdapter {
    private Map<Integer, String> fragmentTags;
    private FragmentManager fragmentManager;
    private Context context;
    public MainFragment mainFragment;
    public ChartFragment chartFragment;

    public FragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        fragmentManager = fm;
        fragmentTags = new HashMap<Integer, String>();//Integer is the position of the fragment
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return mainFragment = new MainFragment();
            case 1: return chartFragment = new ChartFragment();
            default: return mainFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj =  super.instantiateItem(container, position);

        if (obj instanceof Fragment){
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            fragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position){
        String tag = fragmentTags.get(position);
        if (tag == null)
            return null;
        return fragmentManager.findFragmentByTag(tag);
    }
}
