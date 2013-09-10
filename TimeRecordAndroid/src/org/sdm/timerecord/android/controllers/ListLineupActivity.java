package org.sdm.timerecord.android.controllers;

import org.sdm.timerecord.android.Globals;
import org.sdm.timerecord.android.R;
import org.sdm.timerecord.android.TimeRecordDbAdapter;
import org.sdm.timerecord.android.views.ListLineupView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class ListLineupActivity extends Activity
{
	
	public ListLineupView view;

	public final int ACTIVITY_ADD_OR_EDIT_LIST = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		TimeRecordDbAdapter dbHelper = new TimeRecordDbAdapter(this);
		dbHelper.open();
		Globals.getInstance().setDb(dbHelper.getDB());
		
        view = (ListLineupView)View.inflate(this, R.layout.list_lineup, null);
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
	
	private void addList() {
		Intent i = new Intent(this, ListEditActivity.class);
		startActivity(i);		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		switch (requestCode) {
		case ACTIVITY_ADD_OR_EDIT_LIST:
			view.render();
			break;
		}
	}


}
