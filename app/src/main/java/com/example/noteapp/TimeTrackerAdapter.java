package com.example.noteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class TimeTrackerAdapter extends BaseAdapter {

    private ArrayList<TimeRecord> times = new ArrayList<>();

    public TimeTrackerAdapter() {

    }

    @Override
    public int getCount() {
        return this.times.size();
    }

    public void addRecord(TimeRecord record) {
        this.times.add(record);
    }

    @Override
    public TimeRecord getItem(int position) {
        return  times.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addRecords(ArrayList<TimeRecord> ts){
        this.times = ts;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.llist_item,parent,false);
        }

        TimeRecord time = times.get(position);
        TextView timeTextView = (TextView) convertView.findViewById(R.id.time_view);
        TextView notesTextView = (TextView) convertView.findViewById(R.id.note_view);

        timeTextView.setText(time.getTime());
        notesTextView.setText(time.getNote());




        return convertView;
    }
}
