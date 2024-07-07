package com.example.cs2340_1stproject2;

public class activityThreeGettersandSetters {
    private String examName;
    private String location;
    private String date;
    private String time;

    public activityThreeGettersandSetters(String examName, String location, String date, String time) {
        this.examName = examName;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public String getExamName() {
        return examName;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
