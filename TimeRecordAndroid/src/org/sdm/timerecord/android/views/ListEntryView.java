package org.sdm.timerecord.android.views;

import org.sdm.timerecord.android.controllers.ListEntryActivity;
import org.sdm.timerecord.android.model.ListEntry;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.widget.LinearLayout;

public class ListEntryView extends LinearLayout {

	private ListEntryActivity context;
    private ListEntry listEntryModel;
    private ActionMode mActionMode;

    
    public ListEntryView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        this.context = (ListEntryActivity)context;
        //render();
    }

}
