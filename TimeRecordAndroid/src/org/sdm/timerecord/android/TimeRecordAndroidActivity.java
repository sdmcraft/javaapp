package org.sdm.timerecord.android;

import org.sdm.timerecord.android.controllers.ListEditActivity;
import org.sdm.timerecord.android.model.ListDAO;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
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

	static final int DIALOG_DELETE_LIST = 0;

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

	public void editList(Long listId) {
		Intent i = new Intent(this, ListEditActivity.class);
		i.putExtra(ListDAO.COL_ID, listId);
		startActivityForResult(i, ACTIVITY_ADD_OR_EDIT_LIST);
	}

	public void listEntry(Long listId) {
		Intent i = new Intent(this, ListEntryActivity.class);
		i.putExtra(ListDAO.COL_ID, listId);
		startActivityForResult(i, ACTIVITY_LIST_ENTRY);
	}

	public void viewEntries(Long listId) {
		Intent i = new Intent(this, ViewListEntriesActivity.class);
		i.putExtra(ListDAO.COL_ID, listId);
		startActivityForResult(i, ACTIVITY_VIEW_LIST_ENTRIES);
	}

	public void deleteList(Long listId) {
		ListDAO.delete(Globals.getInstance().getDb(), listId);
		removeDialog(DELETE_LIST);
		fillData();
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ADD_NEW_LIST_ID:
			addList();
			return true;
		}
		return false;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case EDIT_LIST:
			editList(info.id);
			return true;
		case MAKE_ENTRY:
			listEntry(info.id);
			return true;
		case VIEW_LIST_ENTRIES:
			viewEntries(info.id);
			return true;
		case DELETE_LIST:
			Bundle args = new Bundle();
			args.putLong("list-id", info.id);
			showDialog(DELETE_LIST, args);			
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	
	@Override
	protected Dialog onCreateDialog(final int id,  final Bundle args) {
		switch (id) {
		case DELETE_LIST:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to delete?")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									deleteList(args.getLong("list-id"));
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();	
			return alert;
		default:
			return super.onCreateDialog(id, args);
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
		// Bundle extras = intent.getExtras();

		switch (requestCode) {
		case ACTIVITY_ADD_OR_EDIT_LIST:
			fillData();
			break;
		case ACTIVITY_LIST_ENTRY:
			/*
			 * String entryTime = extras.getString(ListEntry.COL_ENTRY_TIME);
			 * Long entryListId = extras.getLong(ListEntry.COL_LIST_ID); String
			 * value = extras.getString(ListEntry.COL_VALUE); if (entryTime !=
			 * null && value != null && entryListId >= 0)
			 * ListEntry.insert(mDbHelper.getDB(), entryListId, entryTime,
			 * value);
			 */
			break;
		}
	}

	private void fillData() {
		// Get all of the rows from the database and create the item list
		mListsCursor = ListDAO.query(Globals.getInstance().getDb());
		startManagingCursor(mListsCursor);

		// Create an array to specify the fields we want to display in the list
		// (only TITLE)
		String[] from = new String[] { ListDAO.COL_NAME };

		// and an array of the fields we want to bind those fields to (in this
		// case just text1)
		int[] to = new int[] { R.id.name };

		// Now create a simple cursor adapter and set it to display
		SimpleCursorAdapter lists = new SimpleCursorAdapter(this,
				R.layout.list_row, mListsCursor, from, to);

		setListAdapter(lists);

	}

}