/*
 * Copyright (c) 2010
 */
package com.rayan.partyplanner;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.rayan.partyplanner.data.Task;

public class PersonTaskListActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_task_list);
        final String personName = getIntent().getExtras().getString(Task.PERSON_NAME);
        TextView personNameView = (TextView) findViewById(R.id.list_task_person_name);
        personNameView.setText(personName + "'s Tasks");
        String[] columns = {"_id", "PERSON_NAME", "TASK_TITLE"};
        MatrixCursor cursor = new MatrixCursor(columns);
        Cursor cur = managedQuery(Task.URI, null, null, null, null);
        int i = 0;
        while (cur.moveToNext()) {
            String dataPersonName = cur.getString(cur.getColumnIndex(Task.PERSON_NAME));
            String taskTitle = cur.getString(cur.getColumnIndex(Task.TASK_TITLE));
            String taskDate = cur.getString(cur.getColumnIndex(Task.TASK_DATE));
            if (dataPersonName.equals(personName)) {
                cursor.addRow(new String[]{"" + (i++), taskTitle, "by " + taskDate});
            }
        }
        int[] resourceIds = {0, R.id.person_task_title, R.id.person_task_date};
        ListView taskListView = (ListView) findViewById(R.id.list_tasks_person);
        taskListView.setAdapter(new SimpleCursorAdapter(this, R.layout.person_task_list_item, cursor, columns, resourceIds));
        Button addPersonTask = (Button) findViewById(R.id.add_person_task);
        addPersonTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent addTaskIntent = new Intent(PersonTaskListActivity.this, AddTaskActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Task.PERSON_NAME, personName);
                addTaskIntent.putExtras(bundle);
                startActivity(addTaskIntent);
            }
        });
    }
}
