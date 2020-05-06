package com.example.timetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;


public class ActivitiesFragment extends Fragment {
    private ActivityLog activityLog;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private FragmentManager fragmentManager;
    private FloatingActionButton addButton;
    private MaterialCardView inputCard;
    private CoordinatorLayout coordinatorLayout;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = this.getActivity();
        //Used for adding activity card fragments
        fragmentManager = getFragmentManager();



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

        inputCard = view.findViewById(R.id.input_card);
        inputCard.setVisibility(View.INVISIBLE);

        coordinatorLayout = view.findViewById(R.id.coord_layout);
        addButton = view.findViewById(R.id.add_button);
        //add button for adding new activities
        boolean isClicked = false;
        Button cancelbutton = view.findViewById(R.id.cancel_button);
        Button doneButton = view.findViewById(R.id.done_button);
        EditText editText = view.findViewById(R.id.edit_text);
        addButton.setOnClickListener(v -> {
            if (!isClicked) {
                inputCard.setVisibility(View.VISIBLE);
                coordinatorLayout.setAlpha(.3f);
                doneButton.setClickable(true);
                cancelbutton.setClickable(true);
            } else {
                inputCard.setVisibility(View.INVISIBLE);
                coordinatorLayout.setAlpha(1f);
                doneButton.setClickable(false);
                cancelbutton.setClickable(false);
            }
        });

        doneButton.setOnClickListener(v ->{
            Activity newActivity = new Activity(editText.getText().toString());
            ActivityCardFragment toAdd = activityLog.add(newActivity);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentContainer, toAdd).commit();
            inputCard.setVisibility(View.INVISIBLE);
            doneButton.setClickable(false);
            cancelbutton.setClickable(false);
            coordinatorLayout.setAlpha(1f);
        });

        cancelbutton.setOnClickListener(v -> {
            inputCard.setVisibility(View.INVISIBLE);
            doneButton.setClickable(false);
            cancelbutton.setClickable(false);
            coordinatorLayout.setAlpha(1f);
        });
        String values = activityLog.serialize();
        editor.putString(getString(R.string.activity_log_values_key), values);
        editor.commit();
    }

    //Called when fragment is created on the screen.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activities_tab, container, false);
        return rootView;
    }


    //Stores activity data when fragment is left
    @Override
    public void onPause() {
        super.onPause();
        //Commits data to storage before pausing.
        String values = activityLog.serialize();
        editor.putString(getString(R.string.activity_log_values_key), values);
        editor.apply();
    }
}
