package com.example.moneytimeapp.model;

import java.time.LocalTime;

public class HourInfo {

    LocalTime time;
    String event;
    int meaninngful;

    public HourInfo(LocalTime time, String event, int meaninngful) {
        this.time = time;
        this.event = event;
        this.meaninngful = meaninngful;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getEvent() {
        return event;
    }

    public int getMeaninngful() {
        return meaninngful;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setMeaninngful(int meaninngful) {
        this.meaninngful = meaninngful;
    }
}
