package org.sdm.timerecord.android.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.sdm.timerecord.android.EntriesListAdapter;
import org.sdm.timerecord.android.Globals;
import org.sdm.timerecord.android.R;
import org.sdm.timerecord.android.daos.ListEntryDAO;
import org.sdm.timerecord.android.model.ListEntriesModel;
import org.sdm.timerecord.android.views.ListEntriesView;
import org.sdm.timerecord.android.views.ListLineupView;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.Toast;

public class ListEntriesActivity extends ListActivity {


	private static final int ACTIVITY_EDIT_LIST_ENTRY = 0;

	/*Menu items*/
	private static final int VIEW_LINE_GRAPH_ID = 0;
	private static final int VIEW_BAR_GRAPH_ID = 1;
	private static final int EDIT = 2;
	private static final int DELETE = 3;

	
	private List<ListEntryDAO> entries = new ArrayList<ListEntryDAO>();
	public ListEntriesView view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = (ListEntriesView)View.inflate(this, R.layout.list_entries, null);
		int listId = getIntent().getExtras().getInt(
				org.sdm.timerecord.android.daos.ListDAO.COL_ID);
		view.setModel(ListEntriesModel.getInstance(listId));
		view.render();
		setContentView(view);
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

	public void deleteEntryButtonClickHandler(View view) {
		Button deleteButton = (Button) view;
		long listEntryId = Long.parseLong(deleteButton.getTag().toString());
		ListEntryDAO.delete(Globals.getInstance().getDb(), listEntryId);
		fillData();
	}

	public void editEntryButtonClickHandler(View view) {
		Toast.makeText(getApplicationContext(), "Not yet supported!!", 3)
				.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d("TimeRecordAndroid",
				"Enter ListEntriesActivity --> onCreateOptionsMenu");
		menu.add(0, VIEW_LINE_GRAPH_ID, 0, "View Line Graph");
		menu.add(0, VIEW_BAR_GRAPH_ID, 1, "View Bar Graph");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Log.d("TimeRecordAndroid",
				"Enter ListEntriesActivity --> onMenuItemSelected");
		switch (item.getItemId()) {
		case VIEW_LINE_GRAPH_ID:
			viewGraph("line");
			break;
		case VIEW_BAR_GRAPH_ID:
			viewGraph("bar");
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		Log.d("TimeRecordAndroid",
				"Enter ListEntriesActivity --> onCreateContextMenu");
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, EDIT, 0, "Edit");
		menu.add(0, DELETE, 1, "Delete");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Log.d("TimeRecordAndroid",
				"Enter ListEntriesActivity --> onContextItemSelected");
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case EDIT:
			editEntry(info.id);
			break;
		case DELETE:
			deleteEntry(info.id);
			break;
		}
		return super.onContextItemSelected(item);
	}

	public void editEntry(Long listEntryId) {
		Log.d("TimeRecordAndroid",
				"Enter ListEntriesActivity --> editEntry Args:"
						+ listEntryId);
		Intent i = new Intent(this, ListEntryActivity.class);
		i.putExtra("ListId", mListId);
		i.putExtra("ListEntryId", listEntryId);
		startActivityForResult(i, ACTIVITY_EDIT_LIST_ENTRY);
	}

	public void deleteEntry(Long listId) {
	}

	private void viewGraph(String graphType) {
		String[] titles = new String[] { "Graphical representation of entries" };
		List<Date[]> dates = new ArrayList<Date[]>();
		List<double[]> values = new ArrayList<double[]>();
		Date[] dateValues = new Date[entries.size()];
		double[] valuesDbl = new double[entries.size()];
		double maxVal = 0;
		double minVal = Long.MAX_VALUE;
		for (int i = 0; i < entries.size(); i++) {
			ListEntryDAO entry = entries.get(i);
			SimpleDateFormat entryDateFormat = new SimpleDateFormat(
					"dd-MMM-yyyy");

			Date entryDate = null;
			try {
				entryDate = entryDateFormat.parse(entry.getEntryTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dateValues[i] = entryDate;
			valuesDbl[i] = entry.getValue();
			if (valuesDbl[i] > maxVal)
				maxVal = valuesDbl[i] * 1.1;
			if (valuesDbl[i] < minVal)
				minVal = valuesDbl[i] * 0.9;

		}
		dates.add(dateValues);
		values.add(valuesDbl);

		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		// renderer.setYAxisMin(0);
		// renderer.setYAxisMax(maxVal);
		renderer.setXLabels(0);
		// renderer.setYLabels(0);
		// renderer.clearXTextLabels();
		renderer.setShowCustomTextGrid(true);
		renderer.setXTitle("Entry Dates");
		renderer.setYTitle("Values");
		XYSeriesRenderer seriesRenderer = new XYSeriesRenderer();
		seriesRenderer.setColor(Color.RED);
		renderer.setAxisTitleTextSize(25);
		renderer.setChartTitleTextSize(25);
		renderer.setLabelsTextSize(25);
		renderer.setLegendTextSize(25);
		renderer.setPointSize(10f);
		renderer.setBarSpacing(0.5);
		// renderer.setMargins(new int[] { 20, 30, 15, 20 });
		renderer.addSeriesRenderer(seriesRenderer);

		TimeSeries ts = new TimeSeries("");
		renderer.clearXTextLabels();

		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < valuesDbl.length; i++) {
			calendar.setTime(dateValues[i]);
			ts.add(dateValues[i], valuesDbl[i]);
			renderer.addXTextLabel(dateValues[i].getTime(),
					(calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar
							.get(Calendar.MONTH) + 1)));
			Double d = valuesDbl[i];
			Long l = d.longValue();
			// renderer.addYTextLabel(valuesDbl[i], l.toString());
		}
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(ts);

		if ("line".equalsIgnoreCase(graphType))
			startActivity(ChartFactory.getLineChartIntent(this, dataset,
					renderer));
		else if ("bar".equalsIgnoreCase(graphType))
			startActivity(ChartFactory.getBarChartIntent(this, dataset,
					renderer, Type.DEFAULT));

		/*
		 * int[] colors = new int[] { Color.RED }; PointStyle[] styles = new
		 * PointStyle[] { PointStyle.POINT }; XYMultipleSeriesRenderer renderer
		 * = buildRenderer(colors, styles); setChartSettings(renderer, "Entry",
		 * "Date", "Seconds", dateValues[0].getTime(),
		 * dateValues[dateValues.length - 1].getTime(), 0, maxVal, Color.GRAY,
		 * Color.LTGRAY); renderer.setYLabels(10);
		 * 
		 * startActivity(ChartFactory.getTimeChartIntent(this,
		 * buildDateDataset(titles, dates, values), renderer, "DD-MMM-yyyy"));
		 */

	}

	private void viewGraph1() {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer seriesRenderer = new XYSeriesRenderer();
		renderer.addSeriesRenderer(seriesRenderer);

		XYSeries series = new XYSeries("series");
		series.add(1d, 2d);
		series.add(2d, 4d);
		series.add(3d, 8d);
		series.add(4d, 16d);
		series.add(5d, 32d);
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		TimeSeries ts = new TimeSeries("Dummy Title");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		ts.add(cal.getTime(), 1);

		cal.add(Calendar.DATE, 1);
		ts.add(cal.getTime(), 2);

		cal.add(Calendar.DATE, 1);
		ts.add(cal.getTime(), 4);

		cal.add(Calendar.DATE, 1);
		ts.add(cal.getTime(), 8);

		cal.add(Calendar.DATE, 1);
		ts.add(cal.getTime(), 4);

		cal.add(Calendar.DATE, 1);
		ts.add(cal.getTime(), 2);

		cal.add(Calendar.DATE, 1);
		ts.add(cal.getTime(), 1);

		dataset.addSeries(ts);

		startActivity(ChartFactory.getLineChartIntent(this, dataset, renderer));

	}

	/**
	 * Builds an XY multiple series renderer.
	 * 
	 * @param colors
	 *            the series rendering colors
	 * @param styles
	 *            the series point styles
	 * @return the XY multiple series renderers
	 */
	protected XYMultipleSeriesRenderer buildRenderer(int[] colors,
			PointStyle[] styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		setRenderer(renderer, colors, styles);
		return renderer;
	}

	protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors,
			PointStyle[] styles) {
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setPointSize(5f);
		renderer.setMargins(new int[] { 20, 30, 15, 20 });
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			renderer.addSeriesRenderer(r);
		}
	}

	/**
	 * Sets a few of the series renderer settings.
	 * 
	 * @param renderer
	 *            the renderer to set the properties to
	 * @param title
	 *            the chart title
	 * @param xTitle
	 *            the title for the X axis
	 * @param yTitle
	 *            the title for the Y axis
	 * @param xMin
	 *            the minimum value on the X axis
	 * @param xMax
	 *            the maximum value on the X axis
	 * @param yMin
	 *            the minimum value on the Y axis
	 * @param yMax
	 *            the maximum value on the Y axis
	 * @param axesColor
	 *            the axes color
	 * @param labelsColor
	 *            the labels color
	 */
	protected void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
	}

	/**
	 * Builds an XY multiple time dataset using the provided values.
	 * 
	 * @param titles
	 *            the series titles
	 * @param xValues
	 *            the values for the X axis
	 * @param yValues
	 *            the values for the Y axis
	 * @return the XY multiple time dataset
	 */
	protected XYMultipleSeriesDataset buildDateDataset(String[] titles,
			List<Date[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			TimeSeries series = new TimeSeries(titles[i]);
			Date[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
		return dataset;
	}

}
