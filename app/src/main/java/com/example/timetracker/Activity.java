package com.example.timetracker;

public class Activity {
    private String name;
    private long startTime;
    private long endTime;
    private long totalTime;
    boolean isActive;

    public Activity(String setName) {
        name = setName;
        totalTime = 0;
    }

    public  Activity(String setName, long setTotalTime) {
        name = setName;
        totalTime = setTotalTime;
    }

    public void setName(String setName) {
        name = setName;
    }

    public String getName() {
        return name;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
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
