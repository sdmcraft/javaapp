package org.sdm.timerecord.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListTestActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListAdapter listAdapter = new ArrayAdapter<String>(this,
				R.layout.list_item);
		((ArrayAdapter<String>) listAdapter).add("Text1");
		((ArrayAdapter<String>) listAdapter).add("Text2");
		setListAdapter(listAdapter);
		ListView lv = getListView();
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> av, View v, int pos,
					long id) {
				Toast.makeText(getApplicationContext(), "Hello!", 3).show();
				return false;
			}

		});

	}
}
