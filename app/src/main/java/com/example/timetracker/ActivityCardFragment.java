package com.example.timetracker;


import android.os.Bundle;
import android.os.Handler;
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
        startStopButton.setText("START");


        //Sets up an onClickListener to stop and start the timer
        startStopButton.setOnClickListener(v -> {
            if (activity.isActive()) {
                activity.stop();
                startStopButton.setText("START");
            } else {
                activity.start();
                startStopButton.setText("STOP");
            }
        });

        handler.post(clockTimer);
    }

    //Runnable thingy, don't really understand how it works, but it runs in parallel to the main
    //program and allows us to update the timer text.
    Runnable clockTimer = new Runnable() {
        @Override
        public void run() {
            if (activity.isActive()) {
                int timeElapsed = (int) activity.getElapsedMilli();
                int seconds = timeElapsed / 1000 % 60;
                int minutes = seconds / 60 % 60;
                int hours = minutes / 60;

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
            //recursive, calls it self in
            handler.postDelayed(clockTimer, 1000);
        }
    };
}
