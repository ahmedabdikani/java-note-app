package com.example.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "NOTES";

    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "create table if not exists " +  TABLE_NAME + " ("
        + " id INTEGER PRIMARY KEY AUTOINCREMENT , time TEXT, note TEXT );";

        db.execSQL(sqlQuery);
    }

    public void create(String time, String note){

//        String sqlQuery = "INSERT INTO  " +TABLE_NAME + "(\"time\",\"note\" )  Values (\"" +time+"\",\""+ note+"\" ); ";
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL(sqlQuery);
        ContentValues  cv =  new ContentValues();
        cv.put("time", time);
        cv.put("note", note);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1)
        {
            System.out.println("did not work");
        }
        else {
            System.out.println("created a column");
        }
        db.close();

    }
    public void deleteOne(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"id = "+id , null);
        db.close();

    }


    public ArrayList<TimeRecord> selectAll() {

        ArrayList<TimeRecord> times = new ArrayList<TimeRecord>();
        String sqlQuery = "select *  from " +TABLE_NAME + " ; ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cv = db.rawQuery(sqlQuery, null);

        if (cv.moveToNext()){
            do {

                int id = cv.getInt(0);
                String time = cv.getString(1);
                String note = cv.getString(2);
                TimeRecord record = new TimeRecord(time, note);
                record.setId(id);
                times.add(record);

            }while (cv.moveToNext());
        }

        cv.close();
        db.close();

        return times;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
