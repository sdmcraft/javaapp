package org.sdm.timerecord.android.controllers;

import org.sdm.timerecord.android.Globals;
import org.sdm.timerecord.android.R;
import org.sdm.timerecord.android.model.ListDAO;
import org.sdm.timerecord.android.model.ListModel;
import org.sdm.timerecord.android.views.ListEditView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ListEditActivity extends Activity {

//	private String listName;
//	private String listDescription;
//	private Long listId;
//	private Long listEntryId;
//	private EditText listNameEditText;
//	private EditText listDescriptionEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		ListModel listModel = null;
		if (extras != null) {
			String listId = extras.getString(ListDAO.COL_ID);
			listModel = ListDAO.query(Globals.getInstance().getDb(), Integer.parseInt(listId));
		}
		else
		{
			listModel = new ListModel();
		}
		ListEditView listEditView = (ListEditView)View.inflate(this, R.layout.list_edit, null);
		listEditView.setListModel(listModel);
		setContentView(listEditView);
	}

	/*private void render() {
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
			ListDAO.insert(Globals.getInstance().getDb(), listNameEditText
					.getText().toString(), listDescriptionEditText.getText()
					.toString());
		} else {
			ListDAO.update(Globals.getInstance().getDb(), listId, listNameEditText
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
	}*/

}
