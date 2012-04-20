package org.sdm.timerecord.android;

import org.sdm.timerecord.android.model.List;
import org.sdm.timerecord.android.model.ListEntry;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;

public class TimeRecordAndroidActivity extends ListActivity {

	private static final int ADD_NEW_LIST_ID = Menu.FIRST;

	private static final int MAKE_ENTRY = 0;
	private static final int VIEW_LIST_ENTRIES = 1;
	private static final int EDIT_LIST = 2;
	private static final int DELETE_LIST = 3;

	private static final int ACTIVITY_ADD_OR_EDIT_LIST = 0;
	private static final int ACTIVITY_LIST_ENTRY = 1;
	private static final int ACTIVITY_VIEW_LIST_ENTRIES = 2;

	private TimeRecordDbAdapter mDbHelper;
	private Cursor mListsCursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.lists_list);
		// registerForContextMenu(getListView());
		mDbHelper = new TimeRecordDbAdapter(this);
		mDbHelper.open();
		Globals.getInstance().setDb(mDbHelper.getDB());
		fillData();
		registerForContextMenu(getListView());
	}

	public void listClickHandler(View v) {
		LinearLayout listRow = (LinearLayout) v;
		Intent i = new Intent(this, ListEditActivity.class);

		Long listRowId = Long.parseLong(listRow.getTag().toString());
		i.putExtra(List.COL_ID, listRowId);
		i.putExtra(
				List.COL_NAME,
				List.query(Globals.getInstance().getDb(), listRowId).getString(
						1));
		startActivityForResult(i, ACTIVITY_ADD_OR_EDIT_LIST);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ADD_NEW_LIST_ID, 0, R.string.add_new_list);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, MAKE_ENTRY, 0, "Make Entry");
		menu.add(0, VIEW_LIST_ENTRIES, 1, "View Entries");
		menu.add(0, EDIT_LIST, 2, "Edit");
		menu.add(0, DELETE_LIST, 3, "Delete");
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

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Log.i("####################","##################");
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case EDIT_LIST:
			listClickHandler(info.targetView);
			return true;
		case VIEW_LIST_ENTRIES:

			return true;
		case DELETE_LIST:

			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	private void addList() {
		Intent i = new Intent(this, ListEditActivity.class);
		startActivityForResult(i, ACTIVITY_ADD_OR_EDIT_LIST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		Bundle extras = intent.getExtras();

		switch (requestCode) {
		case ACTIVITY_ADD_OR_EDIT_LIST:
			String name = extras.getString(List.COL_NAME);
			long listId = extras.getLong(List.COL_ID, -1);

			if (name != null && !name.isEmpty()) {
				if (listId == -1) {
					List.insert(Globals.getInstance().getDb(), name);
				} else {
					List.update(Globals.getInstance().getDb(), listId, name);
				}
				fillData();
			}

			break;
		case ACTIVITY_LIST_ENTRY:
			String entryTime = extras.getString(ListEntry.COL_ENTRY_TIME);
			Long entryListId = extras.getLong(ListEntry.COL_LIST_ID);
			String value = extras.getString(ListEntry.COL_VALUE);
			if (entryTime != null && value != null && entryListId >= 0)
				ListEntry.insert(mDbHelper.getDB(), entryListId, entryTime,
						value);
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
				R.layout.list_row, mListsCursor, from, to) /*
															 * {
															 * 
															 * @Override public
															 * View getView(int
															 * position, View
															 * convertView,
															 * ViewGroup parent)
															 * { View view =
															 * super
															 * .getView(position
															 * , convertView,
															 * parent); long id
															 * =
															 * getItemId(position
															 * );
															 * 
															 * Button
															 * entryButton =
															 * (Button)
															 * view.findViewById
															 * (R.id.entry);
															 * entryButton
															 * .setTag(id);
															 * 
															 * Button
															 * viewEntriesButton
															 * = (Button) view
															 * .findViewById
															 * (R.id
															 * .viewEntries);
															 * viewEntriesButton
															 * .setTag(id);
															 * 
															 * TextView listName
															 * = (TextView)
															 * view.
															 * findViewById(
															 * R.id.name);
															 * listName
															 * .setTag(id);
															 * registerForContextMenu
															 * (listName);
															 * LinearLayout
															 * listRow =
															 * (LinearLayout)
															 * view
															 * .findViewById
															 * (R.id.listRow);
															 * listRow
															 * .setTag(id);
															 * return view; } }
															 */;

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