package org.sdm.timerecord.android.views;

import org.sdm.timerecord.android.model.ListLineupModel;
import org.sdm.timerecord.android.model.ListModel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ListEditView extends LinearLayout
{
    private Context context;
    private ListModel listModel;

    public ListEditView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        this.context = context;

    }

	public void setListModel(ListModel listModel) {
		this.listModel = listModel;
	}

}
