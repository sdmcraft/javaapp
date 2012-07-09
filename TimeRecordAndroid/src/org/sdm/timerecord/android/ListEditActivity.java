package org.sdm.timerecord.android;

import org.sdm.timerecord.android.model.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ListEditActivity extends Activity {

	private String listName;
	private String listDescription;
	private Long listId;
	private EditText listNameEditText;
	private EditText listDescriptionEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			listId = extras.getLong(List.COL_ID);
			listName = List.query(Globals.getInstance().getDb(), listId)
					.getString(1);
			listDescription = List.query(Globals.getInstance().getDb(), listId)
					.getString(2);
		}
		render();
	}

	private void render() {
		setContentView(R.layout.list_edit);
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.frequency_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		listNameEditText = (EditText) findViewById(R.id.name);
		if (listName != null) {
			listNameEditText.setText(listName);
		}

		listDescriptionEditText = (EditText) findViewById(R.id.description);
		if (listDescription != null) {
			listDescriptionEditText.setText(listDescription);
		}

		Button confirmButton = (Button) findViewById(R.id.save);
		confirmButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				save();
				exitActivity();
			}
		});
	}

	private void save() {
		if (listId == null || listId == -1) {
			List.insert(Globals.getInstance().getDb(), listNameEditText
					.getText().toString(), listDescriptionEditText.getText()
					.toString());
		} else {
			List.update(Globals.getInstance().getDb(), listId, listNameEditText
					.getText().toString(), listDescriptionEditText.getText()
					.toString());
		}
	}

	private void exitActivity() {
		setResult(RESULT_OK);
		finish();
	}

	@Override
	public void onBackPressed() {
		exitActivity();
	}

}
