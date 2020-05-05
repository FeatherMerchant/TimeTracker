package com.example.timetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;


public class ActivitiesFragment extends Fragment implements View.OnClickListener {
    private ActivityLog activityLog;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private FragmentManager fragmentManager;
    private FloatingActionButton addButton;
    private MaterialCardView inputCard;
    private CoordinatorLayout layout;


    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = this.getActivity();
        //Used for adding activity card fragments
        fragmentManager = getFragmentManager();

        inputCard = view.findViewById(R.id.input_card);
        inputCard.setVisibility(View.INVISIBLE);

        layout = view.findViewById(R.id.coord_layout);

        addButton = view.findViewById(R.id.add_button);
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
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Activity newActivity = activityLog.get(i);
            ActivityCardFragment newFragment = new ActivityCardFragment(newActivity);
            fragmentTransaction.add(R.id.fragmentContainer, newFragment).commit();
        }

        //add button for adding new activities
        addButton.setOnClickListener(this);
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

    public void onClick(View v) {
        inputCard.setVisibility(View.VISIBLE);
        layout.getBackground().setAlpha(100);
    }

}
