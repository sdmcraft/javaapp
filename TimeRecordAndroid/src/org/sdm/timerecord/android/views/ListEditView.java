package org.sdm.timerecord.android.views;

import org.sdm.timerecord.android.R;
import org.sdm.timerecord.android.model.ListModel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


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
		EditText listName = (EditText)findViewById(R.id.list_name);
		listName.setText(listModel.getName());
	}
	

}
