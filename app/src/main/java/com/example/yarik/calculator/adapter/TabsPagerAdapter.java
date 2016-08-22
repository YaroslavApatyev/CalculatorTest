package com.example.yarik.calculator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.yarik.calculator.fragment.FragmentFactorialDigitSum;
import com.example.yarik.calculator.fragment.FragmentLongestSubList;
import com.example.yarik.calculator.fragment.FragmentPalindrome;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public TabsPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                FragmentPalindrome fragmentPalindrome = new FragmentPalindrome();
                return fragmentPalindrome;
            case 1:
                FragmentFactorialDigitSum fragmentFactorialDigitSum = new FragmentFactorialDigitSum();
                return fragmentFactorialDigitSum;
            case 2:
                FragmentLongestSubList fragmentLongestSubList = new FragmentLongestSubList();
                return fragmentLongestSubList;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}