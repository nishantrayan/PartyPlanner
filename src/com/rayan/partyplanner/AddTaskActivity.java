/**
 * @Copyright (c) 2010 Amazon.com.  All rights reserved.
 * <p/>
 * @Version: $Id$
 */
package com.rayan.partyplanner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class AddTaskActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task_layout);
    }
}
