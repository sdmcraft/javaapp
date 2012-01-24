package org.sdm.timerecord.android.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class List {
	public static final String TABLE_NAME = "ts_lists";
	public static final String COL_ID = "_id";
	public static final String COL_NAME = "name";

	private long id;
	private String name;

	public List(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/* TODO all this is too redundant. There must be an abstract class for it */
	public static void create(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_NAME + " (" + COL_ID
				+ " integer primary key autoincrement, " + COL_NAME
				+ "  text not null)");
	}

	public static void drop(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	}

	public static long insert(SQLiteDatabase db, long id, String name) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(COL_ID, id);
		initialValues.put(COL_NAME, name);
		return db.insert(TABLE_NAME, null, initialValues);
	}

	public static boolean delete(SQLiteDatabase db, long rowId) {
		return db.delete(TABLE_NAME, COL_ID + "=" + rowId, null) > 0;
	}

	public static Cursor query(SQLiteDatabase db) {

		return db.query(TABLE_NAME, new String[] { COL_ID, COL_NAME }, null,
				null, null, null, null);
	}

	public static Cursor query(SQLiteDatabase db, long rowId) {

		return db.query(TABLE_NAME, new String[] { COL_ID, COL_NAME }, COL_ID
				+ "=" + rowId, null, null, null, null);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
