/*
 * Copyright (c) 2010
 */

package com.rayan.partyplanner.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.HashMap;
import java.util.Map;


public class TaskContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.rayan.partyplanner";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);
    private TaskDatabaseHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new TaskDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(Task.TABLE_NAME);
        Map<String, String> projMap = new HashMap();
        projMap.put(Task.TASK_TITLE, Task.TASK_TITLE);
        projMap.put(Task.PERSON_NAME, Task.PERSON_NAME);
        projMap.put(Task.TASK_DATE, Task.TASK_DATE);
        qb.setProjectionMap(projMap);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, Task.TABLE_NAME, 1);
        if (uriMatcher.match(uri) == 1) {
            ContentValues values;
            if (contentValues != null) {
                values = new ContentValues(contentValues);
            } else {
                values = new ContentValues();
            }

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            long rowId = db.insert(Task.TABLE_NAME, null, values);
            if (rowId > 0) {
                Uri taskUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
                getContext().getContentResolver().notifyChange(taskUri, null);
                return taskUri;
            }
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
