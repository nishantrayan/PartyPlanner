/*
 * Copyright (c) 2010
 */
package com.rayan.partyplanner;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class ListCursorAdapter extends SimpleCursorAdapter{
    public ListCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
    }

}
