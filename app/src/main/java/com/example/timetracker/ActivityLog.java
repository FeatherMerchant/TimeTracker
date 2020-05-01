package com.example.timetracker;

import java.util.ArrayList;
import java.util.List;

public class ActivityLog {
    private List<Activity> activityList;

    public ActivityLog() {
        activityList = new ArrayList<>();
    }

    public void add(Activity activity) {
        activityList.add(activity);
    }

    public void remove(Activity activity) {
        activityList.remove(activity);
    }

    public double getPercentage(Activity activity) {
        int totalTime = 0;
        boolean isFound;
        for (int i = 0; i < activityList.size(); i++) {
            if (activityList.get(i).equals(activity)) {
                isFound = true;
            } else {
                totalTime += activityList.get(i).getTotalTime();
            }
        }
        if (!isFound) {
            return -1;
        }
        double percentage = (double) activity.getTotalTime() / totalTime * 100.0;
        return percentage;
    }
}
