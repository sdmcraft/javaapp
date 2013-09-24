package org.sdm.timerecord.android.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.sdm.timerecord.android.model.ListEntryModel;

import java.util.ArrayList;
import java.util.List;


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

    public static List<ListEntryModel> query(SQLiteDatabase db)
    {
        Cursor cursor = db.query(TABLE_NAME, new String[] { COL_ID, COL_LIST_ID, COL_ENTRY_TIME, COL_VALUE }, null, null, null, null, null);
        List<ListEntryModel> list = new ArrayList<ListEntryModel>();

        for (int i = 0; i < cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);

            int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            int listId = cursor.getInt(cursor.getColumnIndex(COL_LIST_ID));
            String entryTime = cursor.getString(cursor.getColumnIndex(COL_ENTRY_TIME));
            long value = cursor.getLong(cursor.getColumnIndex(COL_VALUE));

            ListEntryModel listEntryModel = new ListEntryModel();
            listEntryModel.setId(id);
            listEntryModel.setListId(listId);
            listEntryModel.setEntryTime(entryTime);
            listEntryModel.setValue(value);

            list.add(listEntryModel);
        }

        return list;
    }

    public static ListEntryModel query(SQLiteDatabase db, int id)
    {
        Cursor cursor = db.query(TABLE_NAME, new String[] { COL_ID, COL_LIST_ID, COL_ENTRY_TIME, COL_VALUE }, COL_ID + "=" + id, null, null, null, null);
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex(COL_ID));

        int listId = cursor.getInt(cursor.getColumnIndex(COL_LIST_ID));
        String entryTime = cursor.getString(cursor.getColumnIndex(COL_ENTRY_TIME));
        long value = cursor.getLong(cursor.getColumnIndex(COL_VALUE));

        ListEntryModel listEntryModel = new ListEntryModel();
        listEntryModel.setId(id);
        listEntryModel.setListId(listId);
        listEntryModel.setEntryTime(entryTime);
        listEntryModel.setValue(value);

        return listEntryModel;
    }

    public static ListEntryModel queryByListId(SQLiteDatabase db, int listId)
    {
        Cursor cursor = db.query(TABLE_NAME, new String[] { COL_ID, COL_LIST_ID, COL_ENTRY_TIME, COL_VALUE }, COL_LIST_ID + "=" + listId, null, null, null, null);
        cursor.moveToFirst();

        int id = cursor.getInt(cursor.getColumnIndex(COL_ID));

        listId = cursor.getInt(cursor.getColumnIndex(COL_LIST_ID));

        String entryTime = cursor.getString(cursor.getColumnIndex(COL_ENTRY_TIME));
        long value = cursor.getLong(cursor.getColumnIndex(COL_VALUE));

        ListEntryModel listEntryModel = new ListEntryModel();
        listEntryModel.setId(id);
        listEntryModel.setListId(listId);
        listEntryModel.setEntryTime(entryTime);
        listEntryModel.setValue(value);

        return listEntryModel;
    }
}
