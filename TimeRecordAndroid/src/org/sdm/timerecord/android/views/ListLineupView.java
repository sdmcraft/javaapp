package org.sdm.timerecord.android.views;

import org.sdm.timerecord.android.model.ListLineupModel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ListLineupView extends LinearLayout {

	ListLineupModel listLineupModel;
	
	public ListLineupView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		this.listLineupModel = ListLineupModel.getInstance();
	}

}
