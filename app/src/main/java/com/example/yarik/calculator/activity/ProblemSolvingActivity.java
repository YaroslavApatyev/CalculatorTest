package com.example.yarik.calculator.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.yarik.calculator.R;
import com.example.yarik.calculator.adapter.TabsPagerAdapter;

public class ProblemSolvingActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_solving);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar!=null) {
            setSupportActionBar(toolbar);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Palindrome"));
        tabLayout.addTab(tabLayout.newTab().setText("FactorialDigitSum"));
        tabLayout.addTab(tabLayout.newTab().setText("LongestSubList"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);

        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {}

    @Override
    public void onTabReselected(TabLayout.Tab tab) {}

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }
}
