package com.nitkkr.techspardha.drawers.AboutUs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.nitkkr.techspardha.R;

public class AboutUs extends AppCompatActivity {

    public final static int PAGES = 4;
    public final static int LOOPS = 1000;
    public final static int FIRST_PAGE = PAGES * LOOPS / 2;

    public MyPagerAdapter adapter;
    public ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        getSupportActionBar().setTitle("About Us");
        pager = (ViewPager) findViewById(R.id.myviewpager);

        adapter = new MyPagerAdapter(this, this.getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setPageTransformer(false, adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(FIRST_PAGE);

        // Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(4);

        // Set margin for pages as a negative number, so a part of next and
        // previous pages will be showed
        pager.setPageMargin(-100);
    }
}
