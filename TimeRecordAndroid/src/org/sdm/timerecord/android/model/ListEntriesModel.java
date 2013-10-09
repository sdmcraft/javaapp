package org.sdm.timerecord.android.model;

import org.sdm.timerecord.android.Globals;
import org.sdm.timerecord.android.daos.ListEntryDAO;

import java.util.ArrayList;
import java.util.List;


public class ListEntriesModel
{
    private List<ListEntryModel> listEntries;

    private ListEntriesModel() {}

    public static ListEntriesModel getInstance(int listId)
    {
        ListEntriesModel listEntriesModel = new ListEntriesModel();
        listEntriesModel.listEntries = ListEntryDAO.queryByListId(Globals.getInstance().getDb(), listId);

        return listEntriesModel;
    }

    public java.util.List<String[]> getItems()
    {
        List<String[]> result = new ArrayList<String[]>();

        for (ListEntryModel listEntryModel : listEntries)
        {
            result.add(new String[] { Long.toString(listEntryModel.getId()), listEntryModel.getEntryTime(), Long.toString(listEntryModel.getValue()) });
        }

        return result;
    }
}
