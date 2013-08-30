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
                context.save(listModel);
            }
        };

    public ListEditView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        this.context = (ListEditActivity) context;
        render();
    }

    public void setListModel(ListModel listModel)
    {
        this.listModel = listModel;

        EditText listName = (EditText) findViewById(R.id.list_name);
        listName.setText(listModel.getName());
    }

    private void render()
    {
        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(saveButtonClickListener);
    }
}
