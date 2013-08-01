package org.sdm.timerecord.android.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ListDAO {
	public static final String TABLE_NAME = "ts_lists";
	public static final String COL_ID = "_id";
	public static final String COL_NAME = "name";
	public static final String COL_DESCRIPTION = "description";



	/* TODO all this is too redundant. There must be an abstract class for it */
	public static void create(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_NAME + " (" + COL_ID
				+ " integer primary key autoincrement, " + COL_NAME
				+ "  text not null, " + COL_DESCRIPTION + " text)");
	}

	public static void drop(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	}

	public static long insert(SQLiteDatabase db, String name, String description) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(COL_NAME, name);
		initialValues.put(COL_DESCRIPTION, description);
		return db.insert(TABLE_NAME, null, initialValues);
	}

	public static boolean delete(SQLiteDatabase db, long rowId) {
		return db.delete(TABLE_NAME, COL_ID + "=" + rowId, null) > 0;
	}

	public static boolean delete(SQLiteDatabase db) {
		return db.delete(TABLE_NAME, null, null) > 0;
	}

	public static Cursor query(SQLiteDatabase db) {

		return db.query(TABLE_NAME, new String[] { COL_ID, COL_NAME,
				COL_DESCRIPTION }, null, null, null, null, null);
	}

	public static ListModel query(SQLiteDatabase db, long rowId) {

		Cursor cursor = db
				.query(TABLE_NAME, new String[] { COL_ID, COL_NAME,
						COL_DESCRIPTION }, COL_ID + "=" + rowId, null, null,
						null, null);
		cursor.moveToFirst();
		ListModel listModel = new ListModel(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
		return listModel;
	}

	public static boolean update(SQLiteDatabase db, long rowId, String name,
			String description) {
		ContentValues args = new ContentValues();
		args.put(COL_NAME, name);
		args.put(COL_DESCRIPTION, description);
		return db.update(TABLE_NAME, args, COL_ID + "=" + rowId, null) > 0;
	}


}
