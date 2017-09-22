package com.itashiev.cafeteria.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.itashiev.cafeteria.fragment.PlaceholderFragment;
import com.itashiev.cafeteria.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;


public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private static final int TABS_QUANTITY = 3;
    private static final int FIRST_TAB_INDEX = 0;
    private static final int SECOND_TAB_INDEX = 1;
    private static final int THIRD_TAB_INDEX = 2;

    private Date date = Calendar.getInstance().getTime();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(DateUtil.nextNDaysDate(date, position));
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setFromDate(Date start) {
        this.date = start;
    }

    @Override
    public int getCount() {
        return TABS_QUANTITY;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case FIRST_TAB_INDEX:
                return DateUtil.nextNDaysDateForTab(date, 0);
            case SECOND_TAB_INDEX:
                return DateUtil.nextNDaysDateForTab(date, 1);
            case THIRD_TAB_INDEX:
                return DateUtil.nextNDaysDateForTab(date, 2);
        }
        return null;
    }
}