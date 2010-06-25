/*
 * Copyright (c) 2010
 */
package com.rayan.partyplanner;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import com.rayan.partyplanner.data.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskListActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_task_list);
        String[] columns = {"_id", "PERSON_NAME", "TASK_TITLE"};
        MatrixCursor cursor = new MatrixCursor(columns);
        Cursor cur = managedQuery(Task.URI, null, null, null, null);
        int i = 0;
        Map<String, Integer> taskCount = new HashMap();
        while (cur.moveToNext()) {
            String personName = cur.getString(cur.getColumnIndex(Task.PERSON_NAME));
            String taskTitle = cur.getString(cur.getColumnIndex(Task.TASK_TITLE));
            Integer count = taskCount.get(personName);
            if (count == null) count = 0;
            taskCount.put(personName, count + 1);
        }
        for (String personName : taskCount.keySet()) {
            Integer count = taskCount.get(personName);
            cursor.addRow(new String[]{"" + (i++), personName, "has " + count + " tasks pending"});
        }
        int[] resourceIds = {0, R.id.all_task_person_name, R.id.all_task_task_count};
        setListAdapter(new SimpleCursorAdapter(this, R.layout.all_task_list_item, cursor, columns, resourceIds));
    }
}
