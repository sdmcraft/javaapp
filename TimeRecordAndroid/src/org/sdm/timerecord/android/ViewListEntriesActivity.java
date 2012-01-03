package org.sdm.timerecord.android;

import org.sdm.timerecord.android.model.ListEntriesTable;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;

public class ViewListEntriesActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entries_list);
		registerForContextMenu(getListView());
		fillData();
	}

	private void fillData() {
		// Get all of the rows from the database and create the item list
		Cursor listsCursor = ListEntriesTable.queryByListId(Globals
				.getInstance().getDb(),
				getIntent().getExtras().getLong(ListEntriesTable.COL_LIST_ID));
		startManagingCursor(listsCursor);

		// Create an array to specify the fields we want to display in the list
		// (only TITLE)
		String[] from = new String[] { ListEntriesTable.COL_ENTRY_TIME,
				ListEntriesTable.COL_VALUE };

		// and an array of the fields we want to bind those fields to (in this
		// case just text1)
		int[] to = new int[] { R.id.entryTime, R.id.entryValue };

		// Now create a simple cursor adapter and set it to display
		SimpleCursorAdapter entries = new SimpleCursorAdapter(this,
				R.layout.entry_row, listsCursor, from, to);
		setListAdapter(entries);
	}

	public void backButtonClickHandler(View view) {
		Bundle bundle = new Bundle();

		Intent mIntent = new Intent();
		mIntent.putExtras(bundle);
		setResult(RESULT_OK, mIntent);
		finish();

	}

}
