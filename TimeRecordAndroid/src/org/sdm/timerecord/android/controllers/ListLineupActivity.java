package org.sdm.timerecord.android.controllers;

import org.sdm.timerecord.android.R;

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
        View view = View.inflate(this, R.layout.list_lineup, null);
        setContentView(view);
    }
}
