package org.sdm.androidapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class DemoListViewActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//This is not necessary when extending ListActivity
		//setContentView(R.layout.demolistview);
		//ListView listView = (ListView) findViewById(R.id.mylist);
		
		ListView listView = getListView();
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };

		// First paramenter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, android.R.id.text1, values);
		MyArrayAdapter myArrayAdapter = new MyArrayAdapter(this, values);

		// Assign adapter to ListView
		listView.setAdapter(myArrayAdapter);

		//This is not necessary when extending ListActivity
//		listView.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				Toast.makeText(getApplicationContext(),
//						"Click ListItem Number " + position, Toast.LENGTH_LONG)
//						.show();
//			}
//		});
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Toast.makeText(getApplicationContext(),
				"Click ListItem Number " + position, Toast.LENGTH_LONG)
				.show();
	}
}
