package org.sdm.timerecord.android.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sdm.timerecord.android.R;
import org.sdm.timerecord.android.controllers.ListEditActivity;
import org.sdm.timerecord.android.controllers.ListEntriesActivity;
import org.sdm.timerecord.android.controllers.ListLineupActivity;
import org.sdm.timerecord.android.daos.ListDAO;
import org.sdm.timerecord.android.model.ListEntriesModel;
import org.sdm.timerecord.android.model.ListLineupModel;


public class ListEntriesView extends LinearLayout
{
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback()
        {
            // Called when the action mode is created; startActionMode() was called
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu)
            {
                // Inflate a menu resource providing context menu items
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.listlineup_context_menu, menu);

                return true;
            }

            // Called each time the action mode is shown. Always called after onCreateActionMode, but
            // may be called multiple times if the mode is invalidated.
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu)
            {
                return false; // Return false if nothing is done
            }

            // Called when the user selects a contextual menu item
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item)
            {
                TextView targetView = (TextView) (mode.getTag());
                Intent targetIntent;

                switch (item.getItemId())
                {
                    case R.id.edit_list:
                        mode.finish();
                        mActionMode = null;
                        targetView.setSelected(false);
                        targetIntent = new Intent(context, ListEditActivity.class);
                        targetIntent.putExtra(ListDAO.COL_ID, (String) targetView.getTag());
                        context.startActivityForResult(targetIntent, context.ACTIVITY_ADD_OR_EDIT_LIST);

                        return true;

                    case R.id.add_entry:
                        mode.finish();
                        mActionMode = null;
                        targetView.setSelected(false);
                        targetIntent = new Intent(context, ListEditActivity.class);
                        targetIntent.putExtra(ListDAO.COL_ID, (String) targetView.getTag());
                        context.startActivityForResult(targetIntent, context.ACTIVITY_ADD_OR_EDIT_LIST);

                        return true;

                    default:
                        return false;
                }
            }

            // Called when the user exits the action mode
            @Override
            public void onDestroyActionMode(ActionMode mode)
            {
                //mActionMode = null;
            }
        };

    private ActionMode mActionMode;
    private ListEntriesActivity context;
    private ListEntriesModel listEntriesModel;
    private OnLongClickListener listItemLongClickListener = new OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                if (mActionMode != null)
                {
                    return false;
                }

                // Start the CAB using the ActionMode.Callback defined above
                mActionMode = ((Activity) view.getContext()).startActionMode(mActionModeCallback);
                mActionMode.setTag(view);
                view.setSelected(true);

                return true;
            }
        };

    public ListEntriesView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        this.context = (ListEntriesActivity) context;
    }

    public void setModel(ListEntriesModel listEntriesModel)
    {
        this.listEntriesModel = listEntriesModel;
    }

    public void render()
    {
        this.removeAllViews();

        for (String[] listEntry : listEntriesModel.getItems())
        {
            View entryRow = View.inflate(context, R.layout.entry_row, this);

            for (int i = 0; i < ((ViewGroup) entryRow).getChildCount(); ++i)
            {
                View nextChild = ((ViewGroup) entryRow).getChildAt(i);

                if (nextChild.getId() == R.id.entryTime)
                {
                    ((TextView) nextChild).setText(listEntry[1]);
                }
                else if (nextChild.getId() == R.id.entryValue)
                {
                    ((TextView) nextChild).setText(listEntry[2]);
                }
            }

            entryRow.setTag(listEntry[0]);

            this.addView(entryRow);
        }
    }
}
