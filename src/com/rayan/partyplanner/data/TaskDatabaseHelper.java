/*
 * Copyright (c) 2010
 */
package com.rayan.partyplanner.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "task.db";

    public TaskDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + Task.TABLE_NAME + " (" +
                Task._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Task.TASK_TITLE + " TEXT, " +
                Task.PERSON_NAME + " TEXT, " +
                Task.TASK_DATE + " TEXT);";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //do nothing
    }
}
