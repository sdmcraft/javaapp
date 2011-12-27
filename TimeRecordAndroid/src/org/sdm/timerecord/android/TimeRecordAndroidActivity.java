package org.sdm.timerecord.android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TimeRecordAndroidActivity extends ListActivity {

	private static final int ADD_NEW_LIST_ID = Menu.FIRST;
	private static final int ACTIVITY_ADD_NEW_LIST = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lists_list);
		registerForContextMenu(getListView());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ADD_NEW_LIST_ID, 0, R.string.add_new_list);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case ADD_NEW_LIST_ID:
			addList();
			return true;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	private void addList() {
		Intent i = new Intent(this, ListEdit.class);
		startActivityForResult(i, ACTIVITY_ADD_NEW_LIST);
	}

}