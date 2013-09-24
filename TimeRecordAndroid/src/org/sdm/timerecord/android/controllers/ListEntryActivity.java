package org.sdm.timerecord.android.controllers;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.sdm.timerecord.android.Globals;
import org.sdm.timerecord.android.R;
import org.sdm.timerecord.android.R.id;
import org.sdm.timerecord.android.R.layout;
import org.sdm.timerecord.android.daos.ListDAO;
import org.sdm.timerecord.android.daos.ListEntryDAO;
import org.sdm.timerecord.android.model.ListEntryModel;

import java.text.SimpleDateFormat;

import java.util.Calendar;


public class ListEntryActivity extends Activity
{
    static final int DATE_DIALOG_ID = 0;

    /*private void render() {
        if (mListName != null) {
            mListTitleTextView = (TextView) findViewById(R.id.listTitle);
            mListTitleTextView.setText(mListName);
        }
    
        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    
        // display the current date (this method is below)
        resetEntryDate();
    }*/

    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                resetEntryDate();
            }
        };

    private Button mPickDate;
    private EditText hoursEntry;
    private EditText minutesEntry;
    private EditText secondsEntry;
    private ListEntryDAO model;
    private TextView mDateDisplay;
    private TextView mListTitleTextView;
    private int mDay;
    private int mMonth;
    private int mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();

        ListEntryModel listEntryModel = null;

        if (extras != null)
        {
            int listEntryId = extras.getInt("ListEntryId");

            if (listEntryId != 0)
            {
                listEntryModel = ListEntryDAO.query(Globals.getInstance().getDb(), listEntryId);
            }
            else
            {
                listEntryModel = new ListEntryModel();
            }
        }
    }

    private void resetEntryDate()
    {
        Calendar c = Calendar.getInstance();
        c.set(mYear, mMonth, mDay);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        mDateDisplay.setText(sdf.format(c.getTime()));
    }

    private void init()
    {
        setContentView(R.layout.list_entry);

        hoursEntry = (EditText) findViewById(R.id.hoursEntry);
        minutesEntry = (EditText) findViewById(R.id.minutesEntry);
        secondsEntry = (EditText) findViewById(R.id.secondsEntry);

        // capture our View elements
        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        mPickDate = (Button) findViewById(R.id.pickDate);

        // add a click listener to the button
        mPickDate.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    showDialog(DATE_DIALOG_ID);
                }
            });
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }

        return null;
    }

    public void saveEntryClickHandler(View view)
    {
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        if ((hoursEntry.getText() != null) && (hoursEntry.getText().length() > 0))
        {
            hours = Integer.parseInt(hoursEntry.getText().toString());
        }

        if ((minutesEntry.getText() != null) && (minutesEntry.getText().length() > 0))
        {
            minutes = Integer.parseInt(minutesEntry.getText().toString());
        }

        if ((secondsEntry.getText() != null) && (secondsEntry.getText().length() > 0))
        {
            seconds = Integer.parseInt(secondsEntry.getText().toString());
        }

        /*ListEntryDAO.insert(Globals.getInstance().getDb(), mListId.longValue(),
                mDateDisplay.getText().toString(), hours * 60 * 60 * 1000
                        + minutes * 60 * 1000 + seconds * 1000);*/
        exitActivity();
    }

    private void exitActivity()
    {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Bundle bundle = new Bundle();
        Intent mIntent = new Intent();
        mIntent.putExtras(bundle);
        setResult(RESULT_OK, mIntent);
        finish();

        // super.onBackPressed();
    }
}
