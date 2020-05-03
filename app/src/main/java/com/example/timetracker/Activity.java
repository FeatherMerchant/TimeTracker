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
        totalTime = 0;
        context = setContext;
        card = new MaterialCardView(context);
        card.setOnClickListener(v -> {

        });
    }

    public  Activity(String setName, long setTotalTime, Context setContex) {
        name = setName;
        totalTime = setTotalTime;
        context = setContex;
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

    public void start() {
        isActive = true;
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        isActive = false;
        endTime = System.currentTimeMillis();
        totalTime += this.getElapsedMilli();
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
}
