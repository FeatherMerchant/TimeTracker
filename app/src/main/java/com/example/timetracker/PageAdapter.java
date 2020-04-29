package com.example.timetracker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageAdapter extends FragmentStateAdapter {
    private final int NUM_TABS = 3;
    public PageAdapter(FragmentActivity fragment) {
        super(fragment);
    }

    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ActivitiesFragment();
        } else if (position == 1){
            return new CalendarFragment();
        } else {
            return new StatisticsFragment();
        }
    }

    public int getItemCount() {
        return 2;
    }
}
