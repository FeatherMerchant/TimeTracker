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

import com.google.gson.Gson;
import com.google.gson.JsonArray;


public class ActivitiesFragment extends Fragment {
    private ActivityLog activityLog;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    //Called before onCreateView
    public void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this.getActivity();
        //Used for adding activity card fragments
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        //initializing sharedPreferences for data storage
        sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        //Loads in values from storage and creates a new ActivityLog with those values
        String activityValues = sharedPref.getString(getString(R.string.activity_log_values_key), null);
        if (activityValues != null) {
            JsonArray jsonArray = new Gson().fromJson(activityValues, JsonArray.class);
            if (jsonArray != null) {
                activityLog = new ActivityLog(jsonArray);
            }
        } else {
            activityLog = new ActivityLog();
        }

        //Populates activities tab with activity cards
        for (int i = 0; i < activityLog.size(); i++) {
            Activity newActivity = activityLog.get(i);
            ActivityCardFragment newFragment = new ActivityCardFragment(newActivity);
            fragmentTransaction.add(R.id.fragmentContainer, newFragment);
        }
        fragmentTransaction.commit();
    }

    //Called when fragment is created on the screen.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activities_tab, container, false);
        return rootView;
    }


    //Stores activity data when fragment is left
    @Override
    public void onPause() {
        super.onPause();
        //Commits data to storage before pausing.
        String values = activityLog.serialize();
        editor.putString(getString(R.string.activity_log_values_key), values);
        editor.commit();
    }

}
