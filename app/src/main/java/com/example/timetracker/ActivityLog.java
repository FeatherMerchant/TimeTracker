package com.example.timetracker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ActivityLog {
    private List<Activity> activityList;

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

    public JsonArray toJson() {
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < activityList.size(); i++) {
            JsonObject activityJson = new JsonObject();
            activityJson.addProperty("name", activityList.get(i).getName());
            activityJson.addProperty("startTime", activityList.get(i).getStartTime());
            activityJson.addProperty("totalTime", activityList.get(i).getTotalTime());
            activityJson.addProperty("isActive", activityList.get(i).isActive());
            jsonArray.add(activityJson);
        }
        return jsonArray;
    }
}
