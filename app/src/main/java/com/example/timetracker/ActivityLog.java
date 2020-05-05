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
            Long startTime = activity.get("startTime").getAsLong();
            Long totalTime = activity.get("totalTime").getAsLong();
            Activity newActivity = new Activity(name, startTime, totalTime);
            activityList.add(newActivity);
        }
    }

    //Test Constructor
    public ActivityLog() {
        activityList = new ArrayList<>();
        //TODO Remove this Test
        Activity test = new Activity("Running", System.currentTimeMillis() - 900000, 0);
        Activity test2 = new Activity("Gaming", System.currentTimeMillis() - 9000000, 9000000 + 900000);
        activityList.add(test);
        activityList.add(test2);
    }

    public void add(Activity activity) {
        activityList.add(activity);
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
            activityJson.addProperty("startTime", activityList.get(i).getStartTime());
            activityJson.addProperty("totalTime", activityList.get(i).getTotalTime());
            activityJson.addProperty("isActive", activityList.get(i).isActive());
            jsonArray.add(activityJson);
        }
        return jsonArray.toString();
    }
}
