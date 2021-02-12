package com.example.noteapp;

public class TimeRecord {
    private int id;
    private String time;
    private String note;

    public TimeRecord(String time, String note) {
        this.time = time;
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
