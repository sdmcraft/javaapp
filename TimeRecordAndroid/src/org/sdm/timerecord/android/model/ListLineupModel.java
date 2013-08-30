package org.sdm.timerecord.android.model;

import android.database.Cursor;

import org.sdm.timerecord.android.Globals;

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

            /*TODO: Test code*/
            //ListDAO.delete(Globals.getInstance().getDb());
            //ListDAO.insert(Globals.getInstance().getDb(), "List1", "List1 descripton");
            //ListDAO.insert(Globals.getInstance().getDb(), "List2", "List2 descripton");
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
