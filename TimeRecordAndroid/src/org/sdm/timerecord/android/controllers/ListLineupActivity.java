package org.sdm.timerecord.android.controllers;

import org.sdm.timerecord.android.Globals;
import org.sdm.timerecord.android.R;
import org.sdm.timerecord.android.TimeRecordDbAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listlineup_menu, menu);
        return true;
    }
        
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_list:
			addList();
			return true;
		}

		return false;
	}
	
	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

	    // Called when the action mode is created; startActionMode() was called
	    @Override
	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	        // Inflate a menu resource providing context menu items
	        MenuInflater inflater = mode.getMenuInflater();
	        inflater.inflate(R.menu.listlineup_context_menu, menu);
	        return true;
	    }

	    // Called each time the action mode is shown. Always called after onCreateActionMode, but
	    // may be called multiple times if the mode is invalidated.
	    @Override
	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	        return false; // Return false if nothing is done
	    }

	    // Called when the user selects a contextual menu item
	    @Override
	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	        switch (item.getItemId()) {
	            case R.id.edit_list:
	                //shareCurrentItem();
	                mode.finish(); // Action picked, so close the CAB
	                return true;
	            default:
	                return false;
	        }
	    }

	    // Called when the user exits the action mode
	    @Override
	    public void onDestroyActionMode(ActionMode mode) {
	        //mActionMode = null;
	    }
	};
	private void addList() {
		Intent i = new Intent(this, ListEditActivity.class);
		startActivity(i);		
	}


}
