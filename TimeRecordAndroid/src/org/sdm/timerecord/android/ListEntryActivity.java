package org.sdm.timerecord.android;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.sdm.timerecord.android.model.ListEntry;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class ListEntryActivity extends Activity {
	private Long mListId;
	private String mListName;
	private TextView mListTitleTextView;

	private TextView mDateDisplay;
	private Button mPickDate;
	private int mYear;
	private int mMonth;
	private int mDay;

	private EditText hoursEntry;
	private EditText minutesEntry;
	private EditText secondsEntry;

	static final int DATE_DIALOG_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_entry);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mListName = extras.getString(TimeRecordDbAdapter.KEY_NAME);
			mListId = extras.getLong(TimeRecordDbAdapter.KEY_ROWID);
			if (mListName != null) {
				mListTitleTextView = (TextView) findViewById(R.id.listTitle);
				mListTitleTextView.setText(mListName);
			}
		}

		hoursEntry = (EditText) findViewById(R.id.hoursEntry);
		minutesEntry = (EditText) findViewById(R.id.minutesEntry);
		secondsEntry = (EditText) findViewById(R.id.secondsEntry);

		// capture our View elements
		mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
		mPickDate = (Button) findViewById(R.id.pickDate);

		// add a click listener to the button
		mPickDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		// get the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// display the current date (this method is below)
		updateDisplay();
	}

	private void updateDisplay() {
		Calendar c = Calendar.getInstance();
		c.set(mYear, mMonth, mDay);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		mDateDisplay.setText(sdf.format(c.getTime()));
	}

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	public void saveEntryClickHandler(View view) {
		Bundle bundle = new Bundle();

		bundle.putString(ListEntry.COL_ENTRY_TIME, mDateDisplay.getText()
				.toString());
		bundle.putLong(ListEntry.COL_LIST_ID, mListId);
		bundle.putString(ListEntry.COL_VALUE, hoursEntry.getText().toString()
				+ ":" + minutesEntry.getText().toString() + ":"
				+ secondsEntry.getText().toString());
		Intent mIntent = new Intent();
		mIntent.putExtras(bundle);
		setResult(RESULT_OK, mIntent);
		finish();
	}

	@Override
	public void onBackPressed() {
		Bundle bundle = new Bundle();
		Intent mIntent = new Intent();
		mIntent.putExtras(bundle);
		setResult(RESULT_OK, mIntent);
		finish();
		// super.onBackPressed();
	}
}
