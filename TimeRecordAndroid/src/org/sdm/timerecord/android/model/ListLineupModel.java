package org.sdm.timerecord.android.model;

import android.database.Cursor;

import org.sdm.timerecord.android.Globals;
import org.sdm.timerecord.android.daos.ListDAO;

import java.util.ArrayList;


public class ListLineupModel
{
    private static ListLineupModel instance;

    private ListLineupModel() {}

    public static synchronized ListLineupModel getInstance()
    {
        if (instance == null)
        {
            instance = new ListLineupModel();
        }

        return instance;
    }

    public java.util.List<String[]> getItems()
    {
        Cursor cursor = ListDAO.query(Globals.getInstance().getDb());
        java.util.List<String[]> list = new ArrayList<String[]>();

        for (int i = 0; i < cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);

            int id = cursor.getInt(cursor.getColumnIndex(ListDAO.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(ListDAO.COL_NAME));
            list.add(new String[] { Integer.toString(id), name });
        }

        return list;
    }
}
