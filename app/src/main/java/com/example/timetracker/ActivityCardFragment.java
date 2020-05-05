package com.example.timetracker;

import android.bluetooth.BluetoothClass;
import android.os.Bundle;
import android.os.Handler;
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
    TextView timerText;
    Activity activity;
    Timer clockTimer;
    Button startStopButton;

    public ActivityCardFragment(Activity setActivity) {
        activity = setActivity;
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
        clockTimer = new Timer();
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

    }

    public void updateTime(String time) {
        timerText.setText(time);
    }

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
                    minutesString = "00";
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
            handler.postDelayed(clockTimer, 1000);
        }
    };
}
