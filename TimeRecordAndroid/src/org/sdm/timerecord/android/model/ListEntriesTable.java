package org.sdm.timerecord.android.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class ListEntriesTable {

	public void create(SQLiteDatabase db) {
		db.execSQL("create table ts_list_entries (_id integer primary key autoincrement, "
				+ "entry_time text not null, "
				+ "list_id integer not null, "
				+ "value integer, "
				+ "foreign key(list_id) references ts_lists(_id));");
	}

	public void upgrade(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS ts_list_entries");
		create(db);
	}

	public long insert(SQLiteDatabase db, long listId, String entryTime,
			long value) {
		ContentValues initialValues = new ContentValues();
		initialValues.put("list_id", listId);
		initialValues.put("entry_time", entryTime);
		initialValues.put("value", value);
		return db.insert("ts_list_entries", null, initialValues);
	}

}
