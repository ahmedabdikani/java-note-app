package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    public static final int TIME_ENTRY_REQUEST_CODE = 1;
    private Button Btn;
    private TextView text;
    TimeTrackerAdapter timeTrackerAdapter;
    private ListView listView;
    private DatabaseOpenHelper database;

    @Override
    public void onCreate(Bundle state) {

        super.onCreate(state);
        setContentView(R.layout.activity_main);

        database = new DatabaseOpenHelper(this);

        listView = findViewById(R.id.list_view);
        timeTrackerAdapter = new TimeTrackerAdapter();
        timeTrackerAdapter.addRecords(database.selectAll());
        listView.setAdapter(timeTrackerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                database.deleteOne(((TimeRecord) parent.getItemAtPosition(position)).getId());
                timeTrackerAdapter.addRecords(database.selectAll());
                Toast.makeText(parent.getContext(),"clicked note: " + position+1,Toast.LENGTH_SHORT).show();
            }
        });

        //text.setOnClickListener(this);

    }



    @Override
    public void  onClick (View v){
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

         if(item.getItemId() == R.id.menu)
         {
             Intent intent = new Intent(this, Main2Activity.class);
//             startActivity(intent);
             startActivityForResult(intent, TIME_ENTRY_REQUEST_CODE);
             return  true;

         }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 1){

            database.create(data.getStringExtra("time"),data.getStringExtra("note"));
            timeTrackerAdapter.addRecords(database.selectAll());
//            timeTrackerAdapter.addRecord(new TimeRecord(data.getStringExtra("time"), data.getStringExtra("notes")));
            timeTrackerAdapter.notifyDataSetChanged();
        }
    }
}
