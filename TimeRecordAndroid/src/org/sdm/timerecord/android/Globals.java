package org.sdm.timerecord.android;

import android.database.sqlite.SQLiteDatabase;

public class Globals {

	private static Globals instance;
	private SQLiteDatabase db;

	private Globals() {
	}

	public synchronized static Globals getInstance() {
		if (instance == null)
			instance = new Globals();
		return instance;
	}

	public SQLiteDatabase getDb() {
		return db;
	}

	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}

}
