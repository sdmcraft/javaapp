package org.sdm.timerecord.android.views;

import org.sdm.timerecord.android.controllers.ListEntryActivity;
import org.sdm.timerecord.android.daos.ListEntryDAO;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.widget.LinearLayout;

public class ListEntryView extends LinearLayout {

	private ListEntryActivity context;
    private ListEntryDAO listEntryModel;
    private ActionMode mActionMode;

    
    public ListEntryView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        this.context = (ListEntryActivity)context;
        //render();
    }

}
