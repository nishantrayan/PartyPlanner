/*
 * Copyright (c) 2010
 */
package com.rayan.partyplanner;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import com.rayan.partyplanner.data.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class AddTaskActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_layout);
        Button saveTask = (Button) findViewById(R.id.save_task);
        final ContentResolver contentResolver = getContentResolver();
        saveTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                String person_name = ((EditText) findViewById(R.id.person_name)).getText().toString();
                String task_title = ((EditText) findViewById(R.id.task_title)).getText().toString();
                DatePicker picker = (DatePicker) findViewById(R.id.task_date);
                GregorianCalendar calendar = new GregorianCalendar(picker.getYear(), picker.getMonth(), picker.getDayOfMonth());
                DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String task_date = iso8601Format.format(calendar.getTime());
                contentValues.put(Task.PERSON_NAME, person_name);
                contentValues.put(Task.TASK_TITLE, task_title);
                contentValues.put(Task.TASK_DATE, task_date);
                contentResolver.insert(Task.URI, contentValues);
                Intent listTasksIntent=new Intent(AddTaskActivity.this,TaskListActivity.class);
                startActivity(listTasksIntent);
            }
        });
    }
}
