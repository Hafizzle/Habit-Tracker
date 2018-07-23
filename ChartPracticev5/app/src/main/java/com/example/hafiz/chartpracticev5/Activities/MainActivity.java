package com.example.hafiz.chartpracticev5.Activities;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.hafiz.chartpracticev5.Activities.Fragments.ChartFragment;
import com.example.hafiz.chartpracticev5.Activities.Fragments.MainFragment;
import com.example.hafiz.chartpracticev5.Adapters.SlideAdapter;
import com.example.hafiz.chartpracticev5.Adapters.FragmentPageAdapter;
import com.example.hafiz.chartpracticev5.R;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    ChartFragment chartFragment;
    private ViewPager viewPager;
    private SlideAdapter slideAdapter;
    FragmentPageAdapter fragmentPageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(),this.getApplicationContext());
        viewPager.setAdapter(fragmentPageAdapter);
        //viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = ((FragmentPageAdapter)viewPager.getAdapter()).getFragment((position));

                if (position == 1 && fragment != null)
                {
                    fragment.onResume();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return mainFragment = new MainFragment();
                default: return chartFragment = new ChartFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
