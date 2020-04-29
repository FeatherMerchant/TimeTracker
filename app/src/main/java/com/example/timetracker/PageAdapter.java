package com.example.timetracker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageAdapter extends FragmentStateAdapter {
    private final int NUM_TABS = 3;

    public PageAdapter(Fragment fragment) {
        super(fragment);
    }

    public Fragment createFragment(int count) {
        if (count == 0) {
            return new ActivitiesFragment();
        } else {
            return new CalendarFragment();
        }
    }

    public int getItemCount() {
        return NUM_TABS;
    }
}
