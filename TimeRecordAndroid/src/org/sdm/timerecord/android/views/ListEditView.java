package org.sdm.timerecord.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.sdm.timerecord.android.R;
import org.sdm.timerecord.android.controllers.ListEditActivity;
import org.sdm.timerecord.android.model.ListModel;


public class ListEditView extends LinearLayout
{
    private ListEditActivity context;
    private ListModel listModel;
    private OnClickListener saveButtonClickListener = new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            	EditText listName = (EditText) findViewById(R.id.list_name);
            	listModel.setName(listName.getText().toString());
            	
            	EditText listDescription = (EditText) findViewById(R.id.description);
            	listModel.setDescription(listDescription.getText().toString());

                context.save(listModel);
            }
        };

    public ListEditView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        this.context = (ListEditActivity) context;
    }

    public void setListModel(ListModel listModel)
    {
        this.listModel = listModel;

        EditText listName = (EditText) findViewById(R.id.list_name);
        listName.setText(listModel.getName());
    }

    public void setListeners()
    {
        Button saveButton = (Button) findViewById(R.id.save1);
        saveButton.setOnClickListener(saveButtonClickListener);
    }
}
