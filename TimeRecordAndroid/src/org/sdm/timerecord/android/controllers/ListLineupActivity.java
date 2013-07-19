package org.sdm.timerecord.android.controllers;

import org.sdm.timerecord.android.Globals;
import org.sdm.timerecord.android.R;
import org.sdm.timerecord.android.TimeRecordDbAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class ListLineupActivity extends Activity
{
	
	public View view;

	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		TimeRecordDbAdapter dbHelper = new TimeRecordDbAdapter(this);
		dbHelper.open();
		Globals.getInstance().setDb(dbHelper.getDB());
		
        View view = View.inflate(this, R.layout.list_lineup, null);
        setContentView(view);
    }
}
