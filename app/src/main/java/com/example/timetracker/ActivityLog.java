package com.example.timetracker;


import java.util.ArrayList;
import java.util.List;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ActivityLog {
    private List<Activity> activityList;

    //Takes JSON array and populates list with activities
    public ActivityLog(JsonArray values) {
        activityList = new ArrayList<>();
        for (JsonElement v : values) {
            JsonObject activity = (JsonObject) v;
            String name = activity.get("name").getAsString();
            long sessionTime = activity.get("sessionTime").getAsLong();
            long endTime = activity.get("endTime").getAsLong();
            long totalTime = activity.get("totalTime").getAsLong();
            boolean isActive = activity.get("isActive").getAsBoolean();
            Activity newActivity = new Activity(name, sessionTime, endTime, totalTime, isActive);
            activityList.add(newActivity);
        }
    }

    public ActivityLog() {
        activityList = new ArrayList<>();
    }

    public ActivityCardFragment add(Activity activity) {
        activityList.add(activity);
        return new ActivityCardFragment(activity);
    }

    public void remove(Activity activity) {
        activityList.remove(activity);
    }

    public int size() {
        return activityList.size();
    }

    public Activity get(int i) {
        return activityList.get(i);
    }

    //Returns percentage of time spent on activity
    public double getPercentage(Activity activity) {
        int totalTime = 0;
        boolean isFound = false;
        for (int i = 0; i < activityList.size(); i++) {
            if (activityList.get(i).equals(activity)) {
                isFound = true;
                totalTime += activityList.get(i).getTotalTime();
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

    //Serializes data into JSON as a string to store data
    public String serialize() {
        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < activityList.size(); i++) {
            JsonObject activityJson = new JsonObject();
            activityJson.addProperty("name", activityList.get(i).getName());
            activityJson.addProperty("sessionTime", activityList.get(i).getSessionTime());
            activityJson.addProperty("endTime", activityList.get(i).getEndTime());
            activityJson.addProperty("totalTime", activityList.get(i).getTotalTime());
            activityJson.addProperty("isActive", activityList.get(i).isActive());
            jsonArray.add(activityJson);
        }
        return jsonArray.toString();
    }
}
