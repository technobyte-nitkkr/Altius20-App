package com.nitkkr.techspardha.drawers.AboutUs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentPagerAdapter;
//
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nitkkr.techspardha.R;

public class MyPagerAdapter extends FragmentPagerAdapter implements ViewPager.PageTransformer {
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.9f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

    private MyLinearLayout cur = null;
    private MyLinearLayout next = null;
    private AboutUs context;
    private FragmentManager fm;
    private float scale;

    public MyPagerAdapter(AboutUs context, FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        if (position == AboutUs.FIRST_PAGE)
            scale = BIG_SCALE;
        else
            scale = SMALL_SCALE;

        position = position % AboutUs.PAGES;
        return MyFragment.newInstance(context, position, scale);
    }


    @Override
    public int getCount() {
        return AboutUs.PAGES * AboutUs.LOOPS;
    }

    @Override
    public void transformPage(View page, float position) {
        MyLinearLayout myLinearLayout = (MyLinearLayout) page.findViewById(R.id.root);
        float scale = BIG_SCALE;
        if (position > 0) {
            scale = scale - position * DIFF_SCALE;
        } else {
            scale = scale + position * DIFF_SCALE;
        }
        if (scale < 0) scale = 0;
        myLinearLayout.setScaleBoth(scale);
    }


}