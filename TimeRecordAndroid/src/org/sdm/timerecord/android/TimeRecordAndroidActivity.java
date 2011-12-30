package org.sdm.timerecord.android;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class TimeRecordAndroidActivity extends ListActivity {

	private static final int ADD_NEW_LIST_ID = Menu.FIRST;
	private static final int ACTIVITY_ADD_NEW_LIST = 0;
	private static final int ACTIVITY_LIST_ENTRY = 0;
	private TimeRecordDbAdapter mDbHelper;
	private Cursor mListsCursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lists_list);
		registerForContextMenu(getListView());
		mDbHelper = new TimeRecordDbAdapter(this);
		mDbHelper.open();
		fillData();
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
		Intent i = new Intent(this, ListEditActivity.class);
		startActivityForResult(i, ACTIVITY_ADD_NEW_LIST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		Bundle extras = intent.getExtras();

		switch (requestCode) {
		case ACTIVITY_ADD_NEW_LIST:
			String name = extras.getString(TimeRecordDbAdapter.KEY_NAME);

			mDbHelper.createList(name);
			fillData();
			break;
		}
	}

	private void fillData() {
		// Get all of the rows from the database and create the item list
		mListsCursor = mDbHelper.fetchAllLists();
		startManagingCursor(mListsCursor);

		// Create an array to specify the fields we want to display in the list
		// (only TITLE)
		String[] from = new String[] { TimeRecordDbAdapter.KEY_NAME };

		// and an array of the fields we want to bind those fields to (in this
		// case just text1)
		int[] to = new int[] { R.id.name };

		// Now create a simple cursor adapter and set it to display
		SimpleCursorAdapter lists = new SimpleCursorAdapter(this,
				R.layout.list_row, mListsCursor, from, to) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				long id = getItemId(position);
				Button button = (Button) view.findViewById(R.id.entry);
				button.setTag(id);
				return view;
			}
		};
		setListAdapter(lists);
	}

	public void entryButtonClickHandler(View view) {
		Button entryButton = (Button) view;
		// entryButton.setText(entryButton.getTag().toString());
		Intent i = new Intent(this, ListEntryActivity.class);
		Long listRowId = Long.parseLong(entryButton.getTag().toString());
		i.putExtra(TimeRecordDbAdapter.KEY_ROWID, listRowId);
		i.putExtra(TimeRecordDbAdapter.KEY_NAME, mDbHelper.fetchList(listRowId)
				.getString(1));
		startActivityForResult(i, ACTIVITY_LIST_ENTRY);
	}
}