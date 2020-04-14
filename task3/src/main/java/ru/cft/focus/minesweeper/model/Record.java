package ru.cft.focus.minesweeper.model;

import java.io.Serializable;

public class Record implements Serializable, Comparable<Record> {
    String name;
    String time;

    public Record(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    @Override
    public int compareTo(Record record) {

        String[] thisTime = this.time.split(":");
        int thisMinutes = Integer.parseInt(thisTime[0]);
        int thisSeconds = Integer.parseInt(thisTime[1]);

        String[] time = record.time.split(":");
        int minutes = Integer.parseInt(time[0]);
        int seconds = Integer.parseInt(time[1]);

        return (thisMinutes * 60 + thisSeconds) - (minutes * 60 + seconds);
    }
}
