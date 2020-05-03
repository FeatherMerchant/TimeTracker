package com.example.timetracker;

import android.content.Context;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Activity {

    private String name;
    private long startTime;
    private long endTime;
    private long totalTime;
    private boolean isActive;

    MaterialCardView card;
    Context context;

    public Activity(String setName, Context setContext) {
        name = setName;
        context = setContext;
        totalTime = 0;
        startTime = 0;
        endTime = 0;
        card = new MaterialCardView(context);
        card.setOnClickListener(v -> {

        });
    }

    public  Activity(String setName, long setTotalTime, Context setContext) {
        name = setName;
        totalTime = setTotalTime;
        context = setContext;
        startTime = 0;
        endTime = 0;
        card = new MaterialCardView(context);
    }

    public Activity(String setName, long setStartTime, long setTotalTime, Context setContext) {
        name = setName;
        startTime = setStartTime;
        totalTime = setTotalTime;
        context = setContext;
        card = new MaterialCardView(context);
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


    private long getElapsedMilli() {
        return endTime - startTime;
    }

    public void start() {
        isActive = true;
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        isActive = false;
        endTime = System.currentTimeMillis();
        totalTime += this.getElapsedMilli();
    }
}
