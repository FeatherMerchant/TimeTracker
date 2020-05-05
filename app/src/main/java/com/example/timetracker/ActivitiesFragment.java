package com.example.timetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


public class ActivitiesFragment extends Fragment {
    ActivityLog activityLog;
    SharedPreferences sharedPreferences;

    public void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this.getActivity();
        sharedPreferences = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activities_tab, container, false);
        return rootView;
    }

    private ActivityLog loadActivityLog() {
        return null;
    }

}
