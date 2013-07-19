package org.sdm.timerecord.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sdm.timerecord.android.model.ListLineupModel;


public class ListLineupView extends LinearLayout
{
    private Context context;
    private ListLineupModel listLineupModel;

    public ListLineupView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        this.listLineupModel = ListLineupModel.getInstance();
        this.context = context;
        render();
    }

    public void render()
    {
        for (String listName : listLineupModel.getItems())
        {
            TextView textView = new TextView(context);
            textView.setText(listName);
            this.addView(textView);
        }
    }
}
