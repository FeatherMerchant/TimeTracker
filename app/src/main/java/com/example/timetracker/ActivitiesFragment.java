package com.example.timetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;


public class ActivitiesFragment extends Fragment {
    ActivityLog activityLog;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this.getActivity();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        String activityValues = sharedPref.getString(getString(R.string.activity_log_values_key), null);
        if (activityValues != null) {
            JsonArray jsonArray = new Gson().fromJson(activityValues, JsonArray.class);
            activityLog = new ActivityLog(jsonArray);
        } else {
            activityLog = new ActivityLog();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activities_tab, container, false);
        return rootView;
    }

    private ActivityLog loadActivityLog() {
        return null;
    }

    @Override
    public void onPause() {
        super.onPause();
        String values = activityLog.toJson().toString();
        editor.putString(getString(R.string.activity_log_values_key), values);
    }

}
