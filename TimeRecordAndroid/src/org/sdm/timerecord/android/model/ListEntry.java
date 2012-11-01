package org.sdm.timerecord.android.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ListEntry {

	public static final String TABLE_NAME = "ts_list_entries";
	public static final String COL_ID = "_id";
	public static final String COL_ENTRY_TIME = "entry_time";
	public static final String COL_LIST_ID = "list_id";
	public static final String COL_VALUE = "value";
	
	private long id;
	private String entryTime;
	private long listId;
	private long value;

	public ListEntry(long id, String entryTime, long listId, long value) {
		super();
		this.id = id;
		this.entryTime = entryTime;
		this.listId = listId;
		this.value = value;
	}

	public static void create(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_NAME + " (" + COL_ID
				+ " integer primary key autoincrement, " + COL_ENTRY_TIME
				+ "  text not null, " + COL_LIST_ID + " integer not null, "
				+ COL_VALUE + " long, " + "foreign key(" + COL_LIST_ID
				+ ") references ts_lists(_id));");
	}

	public static void drop(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	}

	public static long insert(SQLiteDatabase db, long listId, String entryTime,
			long value) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(COL_LIST_ID, listId);
		initialValues.put(COL_ENTRY_TIME, entryTime);
		initialValues.put(COL_VALUE, value);
		return db.insert(TABLE_NAME, null, initialValues);
	}

	public static boolean delete(SQLiteDatabase db, long rowId) {
		return db.delete(TABLE_NAME, COL_ID + "=" + rowId, null) > 0;
	}

	public static Cursor query(SQLiteDatabase db) {

		return db.query(TABLE_NAME, new String[] { COL_ID, COL_LIST_ID,
				COL_ENTRY_TIME, COL_VALUE }, null, null, null, null, null);
	}

	public static Cursor query(SQLiteDatabase db, long rowId) {

		return db.query(TABLE_NAME, new String[] { COL_ID, COL_LIST_ID,
				COL_ENTRY_TIME, COL_VALUE }, COL_ID + "=" + rowId, null, null,
				null, null);
	}

	public static Cursor queryByListId(SQLiteDatabase db, long listId) {

		return db.query(TABLE_NAME, new String[] { COL_ID, COL_LIST_ID,
				COL_ENTRY_TIME, COL_VALUE }, COL_LIST_ID + "=" + listId, null,
				null, null, null);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public long getListId() {
		return listId;
	}

	public void setListId(long listId) {
		this.listId = listId;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	/*
	 * public boolean update(long rowId, String name) { ContentValues args = new
	 * ContentValues(); args.put(KEY_NAME, name); return
	 * mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0; }
	 */

}
