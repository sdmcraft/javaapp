package org.sdm.timerecord.android;

import org.sdm.timerecord.android.model.List;
import org.sdm.timerecord.android.model.ListEntry;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

public class TimeRecordAndroidActivity extends ListActivity {

	private static final int ADD_NEW_LIST_ID = Menu.FIRST;

	private static final int ACTIVITY_ADD_NEW_LIST = 0;
	private static final int ACTIVITY_LIST_ENTRY = 1;
	private static final int ACTIVITY_VIEW_LIST_ENTRIES = 2;

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
		Globals.getInstance().setDb(mDbHelper.getDB());
		fillData();
		/*http://android.konreu.com/developer-how-to/click-long-press-event-listeners-list-activity/*/
		getListView().setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> av, View v, int pos,
							long id) {
						onListItemClick(v, pos, id);
					}
				});
	}

	protected void onListItemClick(View v, int pos, long id) {
		Log.i("some-tag", "onListItemClick id=" + id);
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
			String name = extras.getString(List.COL_NAME);
			if (name != null && !name.isEmpty()) {
				List.insert(Globals.getInstance().getDb(), name);
				fillData();
			}
			break;
		case ACTIVITY_LIST_ENTRY:
			String entryTime = extras.getString(ListEntry.COL_ENTRY_TIME);
			Long listId = extras.getLong(ListEntry.COL_LIST_ID);
			String value = extras.getString(ListEntry.COL_VALUE);
			if (entryTime != null && value != null && listId >= 0)
				ListEntry.insert(mDbHelper.getDB(), listId, entryTime, value);
			break;
		}
	}

	private void fillData() {
		// Get all of the rows from the database and create the item list
		mListsCursor = List.query(Globals.getInstance().getDb());
		startManagingCursor(mListsCursor);

		// Create an array to specify the fields we want to display in the list
		// (only TITLE)
		String[] from = new String[] { List.COL_NAME };

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
				Button entryButton = (Button) view.findViewById(R.id.entry);
				entryButton.setTag(id);

				Button viewEntriesButton = (Button) view
						.findViewById(R.id.viewEntries);
				viewEntriesButton.setTag(id);

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
		i.putExtra(List.COL_ID, listRowId);
		i.putExtra(
				List.COL_NAME,
				List.query(Globals.getInstance().getDb(), listRowId).getString(
						1));
		startActivityForResult(i, ACTIVITY_LIST_ENTRY);
	}

	public void viewEntriesButtonClickHandler(View view) {
		Button viewEntriesButton = (Button) view;
		// entryButton.setText(entryButton.getTag().toString());
		Intent i = new Intent(this, ViewListEntriesActivity.class);
		Long listRowId = Long.parseLong(viewEntriesButton.getTag().toString());
		i.putExtra(List.COL_ID, listRowId);
		startActivityForResult(i, ACTIVITY_VIEW_LIST_ENTRIES);

	}

}