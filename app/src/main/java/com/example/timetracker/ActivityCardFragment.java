package com.example.timetracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityCardFragment extends Fragment {
    TextView activityTitle;
    Activity activity;
    Timer clockTimer;
    Button startStopButton;

    public ActivityCardFragment(Activity setActivity) {
        activity = setActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        activityTitle = this.getView().findViewById(R.id.activityTitle);
        clockTimer = new Timer();
        startStopButton = this.getView().findViewById(R.id.startStopButton);
        ClockTimerTask task = new ClockTimerTask();
        clockTimer.scheduleAtFixedRate(task, 0, 1000);
        startStopButton.setOnClickListener(v -> {
            if (activity.isActive()) {
                activity.stop();
                startStopButton.setText("START");
            } else {
                activity.start();
                startStopButton.setText("STOP");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calendar_tab, container, false);
    }

    public class ClockTimerTask extends TimerTask {
        @Override
        public void run() {
            if (!activity.isActive()) {
                cancel();
            }

            int timeElapsed = (int) activity.getElapsedMilli();
            int seconds = timeElapsed / 1000;
            int minutes = seconds / 60;
            int hours = minutes / 60;

            if (seconds == 0) {
                activityTitle.setText("00:00");
            } else if (minutes == 0) {
                activityTitle.setText("00:" + seconds % 60);
            } else if (hours == 0) {
                activityTitle.setText(minutes % 60 + ":" + seconds % 60);
            } else {
                activityTitle.setText(hours + ":" + minutes % 60 + ":" + seconds % 60);
            }
        }
    }
}
