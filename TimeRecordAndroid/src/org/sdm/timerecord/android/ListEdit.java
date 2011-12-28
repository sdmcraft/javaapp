package org.sdm.timerecord.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ListEdit extends Activity {

	private EditText mNameText;
	private Long mRowId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_edit);
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.frequency_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		mNameText = (EditText) findViewById(R.id.name);

		Button confirmButton = (Button) findViewById(R.id.save);
		mRowId = null;

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String name = extras.getString(TimeRecordDbAdapter.KEY_NAME);

			mRowId = extras.getLong(TimeRecordDbAdapter.KEY_ROWID);

			if (name != null) {
				mNameText.setText(name);
			}
		}
		confirmButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Bundle bundle = new Bundle();

				bundle.putString(TimeRecordDbAdapter.KEY_NAME, mNameText
						.getText().toString());
				if (mRowId != null) {
					bundle.putLong(TimeRecordDbAdapter.KEY_ROWID, mRowId);
				}
				Intent mIntent = new Intent();
				mIntent.putExtras(bundle);
				setResult(RESULT_OK, mIntent);
				finish();
			}

		});
	}

}
