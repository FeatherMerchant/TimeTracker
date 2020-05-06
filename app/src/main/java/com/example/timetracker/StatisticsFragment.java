package com.example.timetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {

    private Handler handler = new Handler();

    private View v;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.statistics_tab, container, false);
        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v = view;
        handler.post(updater);
    }


    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            Gson gson = new Gson();
            PieChart pieChart = v.findViewById(R.id.piechart);
            Context context = getActivity();
            //initializing sharedPreferences for data storage
            SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            ActivityLog activityLog;

            //Loads in values from storage and creates a new ActivityLog with those values
            String activityValues = sharedPref.getString(getString(R.string.activity_log_values_key), null);
            if (activityValues != null) {
                JsonArray jsonArray = new Gson().fromJson(activityValues, JsonArray.class);
                activityLog = new ActivityLog(jsonArray);
            } else {
                activityLog = new ActivityLog();
            }

            ArrayList<PieEntry> entries = new ArrayList<>();
            //ActivityLog activityLog = new ActivityLog(); //This is for testing. Only works if you remove code block above.
            int m = activityLog.size();
            for (int i = 0; i < m; i++) {
                Activity tmp = activityLog.get(i);
                //Retrieve and store Activity data in a PieEntry list so it can be used by library.
                //Syntax is PieEntry(time spent, "[Name of Activity]").
                //Unsure of how to get the time spent from the ActivityLog from variables getStartTime and getTotalTime

                //adds data to pie chart in terms of hours
                entries.add(new PieEntry((float) tmp.getTotalTime() / 60000, tmp.getName()));
                Log.i("PieInfo", (tmp.getTotalTime() / 60000) + " minutes done");

            }
            PieDataSet dataset = new PieDataSet(entries, "Hours Done");
            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
//        //Example PieChart
//        ArrayList<PieEntry> entries = new ArrayList<>();
//        entries.add(new PieEntry(30f, "Running")); //Running
//        entries.add(new PieEntry(480f, "Sleeping")); //Sleeping
//        entries.add(new PieEntry(180f, "Studying")); //Studying
//        entries.add(new PieEntry(60f, "Gaming")); //Gaming
//        entries.add(new PieEntry(60f, "Reading")); //Reading
//        PieDataSet dataset = new PieDataSet(entries, "Activities done today");
//        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

            PieData data = new PieData(dataset);
            data.setValueTextSize(40f);
            pieChart.setData(data);
            pieChart.setHoleRadius(35);
            pieChart.setTransparentCircleRadius(40);
            pieChart.setTransparentCircleColor(Color.WHITE);
            pieChart.setTouchEnabled(false);
            handler.postDelayed(updater, 250);
        }
    };
}
