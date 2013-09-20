package org.sdm.timerecord.android.daos;

import org.sdm.timerecord.android.model.ListEntryModel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class ListEntryDAO
{
    public static final String TABLE_NAME = "ts_list_entries";
    public static final String COL_ID = "_id";
    public static final String COL_ENTRY_TIME = "entry_time";
    public static final String COL_LIST_ID = "list_id";
    public static final String COL_VALUE = "value";

    public static void update(SQLiteDatabase db, ListEntryModel listEntryModel)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_LIST_ID, listEntryModel.getListId());
        initialValues.put(COL_ENTRY_TIME, listEntryModel.getEntryTime());
        initialValues.put(COL_VALUE, listEntryModel.getValue());

        if (listEntryModel.getId() == 0)
        {
            db.insert(TABLE_NAME, null, initialValues);
        }
        else
        {
            db.update(TABLE_NAME, initialValues, COL_ID + "= ?", new String[] { Long.toString(listEntryModel.getId()) });
        }
    }

    public static void create(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " + COL_ENTRY_TIME + "  text not null, " + COL_LIST_ID + " integer not null, " + COL_VALUE +
            " long, " + "foreign key(" + COL_LIST_ID + ") references ts_lists(_id));");
    }

    public static void drop(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public static boolean delete(SQLiteDatabase db, ListEntryModel listEntryModel)
    {
        return db.delete(TABLE_NAME, COL_ID + "=" + listEntryModel.getId(), null) > 0;
    }

    public static Cursor query(SQLiteDatabase db)
    {
        return db.query(TABLE_NAME, new String[] { COL_ID, COL_LIST_ID, COL_ENTRY_TIME, COL_VALUE }, null, null, null, null, null);
    }

    public static Cursor query(SQLiteDatabase db, ListEntryModel listEntryModel)
    {
        Cursor cursor = db.query(TABLE_NAME, new String[] { COL_ID, COL_LIST_ID, COL_ENTRY_TIME, COL_VALUE }, COL_ID + "=" + listEntryModel.getId(), null, null, null, null);
        cursor.moveToFirst();

        return cursor;
    }

    public static Cursor queryByListId(SQLiteDatabase db, long listId)
    {
        return db.query(TABLE_NAME, new String[] { COL_ID, COL_LIST_ID, COL_ENTRY_TIME, COL_VALUE }, COL_LIST_ID + "=" + listId, null, null, null, null);
    }
}