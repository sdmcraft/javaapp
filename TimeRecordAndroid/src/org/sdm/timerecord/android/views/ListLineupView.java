package org.sdm.timerecord.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
            textView.setOnLongClickListener(listItemLongClickListener);
            this.addView(textView);
        }
    }
    
    private OnLongClickListener listItemLongClickListener = new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			CharSequence text = "Hello toast!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			return true;
		}
	};
    

}
