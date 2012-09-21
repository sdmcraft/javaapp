package org.sdm.androidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public MyArrayAdapter(Context context, String[] values) {
		super(context, R.layout.demolistrow, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.demolistrow, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.myText);
		textView.setText(values[position]);
		return rowView;
	}
}
