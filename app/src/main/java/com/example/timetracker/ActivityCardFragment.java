package com.example.timetracker;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class ActivityCardFragment extends Fragment {
    private TextView activityTitle;
    private TextView timerText;
    private Activity activity;
    private Button startStopButton;
    private Button resetButton;

    private long totalTimeStopped;

    private Handler handler;

    public ActivityCardFragment(Activity setActivity) {
        activity = setActivity;
        handler = new Handler();
    }

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_card, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceBundle) {
        activityTitle = getView().findViewById(R.id.activityTitle);
        activityTitle.setText(activity.getName());

        timerText = getView().findViewById(R.id.timerText);
        startStopButton = getView().findViewById(R.id.startStopButton);
        resetButton = getView().findViewById(R.id.resetButton);

        if (activity.isActive()) {
            startStopButton.setText("STOP");
        } else {
            startStopButton.setText("START");
        }
        totalTimeStopped = 0;
        int timeElapsed = (int) activity.getElapsedMilli();
        int seconds = timeElapsed / 1000 % 60;
        int minutes = timeElapsed / 60000 % 60;
        int hours = timeElapsed / 3600000;


        String secondsString;
        String minutesString;
        String hoursString;

        if (seconds == 0 && !activity.isActive()) {
            timerText.setText("00:00");
        } else {
            if (seconds < 10) {
                secondsString = "0" + seconds;
            } else {
                secondsString = "" + seconds;
            }

            if (minutes == 0) {
                minutesString = "00:";
            } else {
                minutesString = minutes + ":";
            }

            if (hours == 0) {
                hoursString = "";
            } else {
                hoursString = hours + ":";
            }
            timerText.setText(hoursString + minutesString + secondsString);
        }
        //Sets up an onClickListener to stop and start the timer
        startStopButton.setOnClickListener(v -> {
            if (activity.isActive()) {
                activity.stop();
                startStopButton.setText("START");
            } else {
                if (activity.getEndTime() == 0) {
                    activity.resetStart();
                } else {
                    activity.start();
                }
                totalTimeStopped += System.currentTimeMillis() - activity.getEndTime();
                startStopButton.setText("STOP");
            }
        });

        resetButton.setOnClickListener(v -> {
            activity.reset();
            totalTimeStopped = 0;
            timerText.setText("00:00");
            startStopButton.setText("START");
        });

        handler.post(clockTimer);
    }

    //Runnable thingy, don't really understand how it works, but it runs in parallel to the main
    //program and allows us to update the timer text.
    private Runnable clockTimer = new Runnable() {
        @Override
        public void run() {
            int timeElapsed;
            if (activity.isActive()) {
                timeElapsed = (int) (System.currentTimeMillis() - activity.getStartTime() - totalTimeStopped);

                int seconds = timeElapsed / 1000 % 60;
                int minutes = timeElapsed / 60000 % 60;
                int hours = timeElapsed / 3600000;

                String secondsString;
                String minutesString;
                String hoursString;

                if (seconds < 10) {
                    secondsString = "0" + seconds;
                } else {
                    secondsString = "" + seconds;
                }

                if (minutes == 0) {
                    minutesString = "00:";
                } else {
                    minutesString = minutes + ":";
                }

                if (hours == 0) {
                    hoursString = "";
                } else {
                    hoursString = hours + ":";
                }
                timerText.setText(hoursString + minutesString + secondsString);
            }
            //recursive, calls its self
            handler.postDelayed(clockTimer, 250);
        }
    };
}
