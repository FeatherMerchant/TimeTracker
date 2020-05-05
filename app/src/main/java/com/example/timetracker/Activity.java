package com.example.timetracker;

import android.content.Context;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Activity {

    private String name;
    private long startTime;
    private long totalTime;
    private boolean isActive;

    MaterialCardView card;
    Context context;

    public Activity(String setName) {
        name = setName;
        totalTime = 0;
        startTime = 0;
    }

    public  Activity(String setName, long setTotalTime) {
        name = setName;
        totalTime = setTotalTime;
        startTime = 0;
    }

    public Activity(String setName, long setStartTime, long setTotalTime) {
        name = setName;
        startTime = setStartTime;
        totalTime = setTotalTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setName(String setName) {
        name = setName;
    }

    public String getName() {
        return name;
    }

    public void setStartTime(long setStartTime) {
        startTime = setStartTime;
    }

    public long getStartTime() {
        return  startTime;
    }

    public void setTotalTime(long totalTime) {
        totalTime = totalTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Activity)) {
            return false;
        }
        Activity toCompare = (Activity) obj;
        return toCompare.totalTime == this.totalTime && toCompare.name.equals(this.name);
    }


    public long getElapsedMilli() {
        return System.currentTimeMillis() - startTime;
    }

    public void start() {
        isActive = true;
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        isActive = false;
        totalTime += this.getElapsedMilli();
    }
}
