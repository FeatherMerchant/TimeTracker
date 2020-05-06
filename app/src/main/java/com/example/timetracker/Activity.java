package com.example.timetracker;

import android.content.Context;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Activity {

    private String name;
    private long startTime;
    private long endTime;
    private long totalTime;
    private long totalTimeStopped;
    private long sessionTime;
    private boolean isActive;


    public Activity(String setName) {
        isActive = false;
        name = setName;
        totalTime = 0;
        startTime = 0;
        endTime = 0;
        sessionTime = 0;
    }

    public Activity(String setName, long setStartTime, long setEndTime, long setTotalTime, boolean setIsActive) {
        sessionTime = 0;
        isActive = setIsActive;
        name = setName;
        startTime = setStartTime;
        endTime = setEndTime;
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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long setTime) {
        endTime = setTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void addTotalTime(long millis) {
        totalTime += millis;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Activity)) {
            return false;
        }
        Activity toCompare = (Activity) obj;
        return toCompare.totalTime == this.totalTime && toCompare.name.equals(this.name);
    }


    public long getElapsedMilli() {
        return endTime - startTime;
    }

    public void reset() {
        totalTime += sessionTime;
        isActive = false;
        startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
    }

    public void start() {
        isActive = true;
        if (getEndTime() != 0) {
            totalTimeStopped += System.currentTimeMillis() - getEndTime();
        }
    }

    public void stop() {
        isActive = false;
        endTime = System.currentTimeMillis();
        sessionTime = this.getElapsedMilli();
    }
}
