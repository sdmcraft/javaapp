package org.sdm.timerecord.android;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class EntriesListAdapter extends SimpleCursorAdapter {

	private int[] mFrom;
	private int[] mTo;

	public EntriesListAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		mTo = to;
		if (c != null) {
			int i;
			int count = from.length;
			if (mFrom == null || mFrom.length != count) {
				mFrom = new int[count];
			}
			for (i = 0; i < count; i++) {
				mFrom[i] = c.getColumnIndexOrThrow(from[i]);
			}
		} else {
			mFrom = null;
		}
	}

	public void bindView(View view, Context context, Cursor cursor) {
		final ViewBinder binder = getViewBinder();
		final int count = mTo.length;
		final int[] from = mFrom;
		final int[] to = mTo;

		for (int i = 0; i < count; i++) {
			final View v = view.findViewById(to[i]);
			if (v != null) {
				boolean bound = false;
				if (binder != null) {
					bound = binder.setViewValue(v, cursor, from[i]);
				}

				if (!bound) {
					String text = cursor.getString(from[i]);
					if (text == null) {
						text = "";
					} else if (R.id.entryValue == v.getId()) {

						Long milliseconds = cursor.getLong(from[i]);

						text = Utils.millisecondsToHHMMSSms(milliseconds);
					}
					if (v instanceof TextView) {
						setViewText((TextView) v, text);
					} else if (v instanceof ImageView) {
						setViewImage((ImageView) v, text);
					} else {
						throw new IllegalStateException(
								v.getClass().getName()
										+ " is not a "
										+ " view that can be bound by this SimpleCursorAdapter");
					}
				}
			}
		}
	}
}
