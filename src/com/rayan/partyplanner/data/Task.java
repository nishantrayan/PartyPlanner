/*
 * Copyright (c) 2010
 */
package com.rayan.partyplanner.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class Task implements BaseColumns {
    public static String TASK_TITLE="task_title";
    public static String PERSON_NAME="person_name";
    public static String TASK_DATE="task_date";
    public static String TABLE_NAME = "Task";
    public static Uri URI= Uri.parse(TaskContentProvider.CONTENT_URI+"/"+TABLE_NAME);

}
