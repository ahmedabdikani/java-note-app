package com.example.noteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onCancel(View view) {
        Intent intent = getIntent() ;
        this.setResult(0, null);
        finish();
    }


    public void onSave(View view) {

        Intent intent = getIntent() ;
        EditText notesView = (EditText)findViewById(R.id.edit_notes);
        EditText timeView = (EditText)findViewById(R.id.edit_time);

        intent.putExtra("time", timeView.getText().toString());
        intent.putExtra("note",notesView.getText().toString());

        if (timeView.getText().toString()== "" || notesView.getText().toString()== ""){
            this.setResult(0, intent);
            finish();

        }

        this.setResult(1, intent);

        finish();
    }


}
