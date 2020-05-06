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
    private TextView totalTimeText;
    private Activity activity;
    private Button startStopButton;
    private Button resetButton;

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
        totalTimeText = getView().findViewById(R.id.totalTime);
        startStopButton = getView().findViewById(R.id.startStopButton);
        resetButton = getView().findViewById(R.id.resetButton);

        if (activity.isActive()) {
            startStopButton.setText("STOP");
            activity.addSessionTime(System.currentTimeMillis() - activity.getEndTime());
            Log.i("ActivityFragment", "Time delta: " + (System.currentTimeMillis() - activity.getEndTime()));
        } else {
            startStopButton.setText("START");
        }
        long timeElapsed = activity.getSessionTime();
        int seconds = (int) timeElapsed / 1000 % 60;
        int minutes = (int) timeElapsed / 60000 % 60;
        int hours = (int) timeElapsed / 3600000;


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


        timeElapsed = activity.getTotalTime();
        seconds = (int) timeElapsed / 1000 % 60;
        minutes = (int) timeElapsed / 60000 % 60;
        hours = (int) timeElapsed / 3600000;


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
        totalTimeText.setText(hoursString + minutesString + secondsString);

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

        resetButton.setOnClickListener(v -> {
            activity.reset();
            timerText.setText("00:00");
            startStopButton.setText("START");
        });

        handler.post(clockTimer);
        handler.post(totalTimer);
    }

    //Runnable thingy, don't really understand how it works, but it runs in parallel to the main
    //program and allows us to update the timer text.
    private Runnable clockTimer = new Runnable() {
        @Override
        public void run() {
            long timeElapsed;
            if (activity.isActive()) {
                timeElapsed = activity.getSessionTime();
                int seconds = (int) timeElapsed / 1000 % 60;
                int minutes = (int) timeElapsed / 60000 % 60;
                int hours = (int) timeElapsed / 3600000;

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

                activity.addSessionTime(1000);
                timerText.setText(hoursString + minutesString + secondsString);
                /*
                Context context = getActivity();
                SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                String activityValues = sharedPref.getString(getString(R.string.activity_log_values_key), null);

                if (activityValues != null) {
                    JsonArray jsonArray = new Gson().fromJson(activityValues, JsonArray.class);
                    ActivityLog activityLog = new ActivityLog(jsonArray);
                    String values = activityLog.serialize();
                    editor.putString(getString(R.string.activity_log_values_key), values);
                    editor.commit();
                }

                 */
            }
            //recursive, calls its self
            activity.setEndTime(System.currentTimeMillis());
            handler.postDelayed(clockTimer, 1000);
        }
    };

    private Runnable totalTimer = new Runnable() {
        @Override
        public void run() {
            long timeElapsed;
            if (activity.isActive()) {
                timeElapsed = activity.getTotalTime();
                int seconds = (int) timeElapsed / 1000 % 60;
                int minutes = (int) timeElapsed / 60000 % 60;
                int hours = (int) timeElapsed / 3600000;

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

                activity.addTotalTime(1000);
                totalTimeText.setText(hoursString + minutesString + secondsString);
                /*
                Context context = getActivity();
                SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                String activityValues = sharedPref.getString(getString(R.string.activity_log_values_key), null);

                if (activityValues != null) {
                    JsonArray jsonArray = new Gson().fromJson(activityValues, JsonArray.class);
                    ActivityLog activityLog = new ActivityLog(jsonArray);
                    String values = activityLog.serialize();
                    editor.putString(getString(R.string.activity_log_values_key), values);
                    editor.commit();
                }

                 */
            }
            //recursive, calls its self
            handler.postDelayed(totalTimer, 1000);
        }
    };

    public void onPause() {
        super.onPause();
        if (activity.isActive()) {
            activity.setEndTime(System.currentTimeMillis());
        }
    }

}
