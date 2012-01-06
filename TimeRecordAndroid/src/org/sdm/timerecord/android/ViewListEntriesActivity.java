package org.sdm.timerecord.android;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.sdm.timerecord.android.model.ListEntry;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

public class ViewListEntriesActivity extends ListActivity {

	private static final int VIEW_GRAPH_ID = Menu.FIRST;
	private List<ListEntry> entries = new ArrayList<ListEntry>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entries_list);
		registerForContextMenu(getListView());
		fillData();
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

	private void fillData() {
		// Get all of the rows from the database and create the item list
		Cursor listsCursor = ListEntry.queryByListId(Globals.getInstance()
				.getDb(),
				getIntent().getExtras().getLong(TimeRecordDbAdapter.KEY_ROWID));
		while (listsCursor.moveToNext()) {
			Long id = listsCursor.getLong(listsCursor
					.getColumnIndex(ListEntry.COL_ID));
			String entryTime = listsCursor.getString(listsCursor
					.getColumnIndex(ListEntry.COL_ENTRY_TIME));
			Long listId = listsCursor.getLong(listsCursor
					.getColumnIndex(ListEntry.COL_LIST_ID));
			String value = listsCursor.getString(listsCursor
					.getColumnIndex(ListEntry.COL_VALUE));

			entries.add(new ListEntry(id, entryTime, listId, value));
		}
		listsCursor.moveToFirst();
		startManagingCursor(listsCursor);

		// Create an array to specify the fields we want to display in the list
		// (only TITLE)
		String[] from = new String[] { ListEntry.COL_ENTRY_TIME,
				ListEntry.COL_VALUE };

		// and an array of the fields we want to bind those fields to (in this
		// case just text1)
		int[] to = new int[] { R.id.entryTime, R.id.entryValue };

		// Now create a simple cursor adapter and set it to display
		SimpleCursorAdapter entries = new SimpleCursorAdapter(this,
				R.layout.entry_row, listsCursor, from, to);
		setListAdapter(entries);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, VIEW_GRAPH_ID, 0, "View Graph");
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case VIEW_GRAPH_ID:
			viewGraph();
			return true;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	private void viewGraph() {
		String[] titles = new String[] { "Sales growth January 1995 to December 2000" };
		List<Date[]> dates = new ArrayList<Date[]>();
		List<double[]> values = new ArrayList<double[]>();
		Date[] dateValues = new Date[entries.size()];
		double[] valuesDbl = new double[entries.size()];
		for (int i = 0; i < entries.size(); i++) {
			ListEntry entry = entries.get(i);
			int month = Integer.parseInt(entry.getEntryTime().split("-")[0]
					.trim()) - 1;
			int day = Integer.parseInt(entry.getEntryTime().split("-")[1]
					.trim());
			int year = Integer.parseInt(entry.getEntryTime().split("-")[2]
					.trim()) - 1900;
			dateValues[i] = new Date(year, month, day);
			valuesDbl[i] = Integer.parseInt(entry.getValue().split(":")[0]
					.trim())
					* 3600
					+ Integer.parseInt(entry.getValue().split(":")[1].trim())
					* 60
					+ Integer.parseInt(entry.getValue().split(":")[2].trim());
		}
		dates.add(dateValues);
		values.add(valuesDbl);

		int[] colors = new int[] { Color.RED };
		PointStyle[] styles = new PointStyle[] { PointStyle.POINT };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		setChartSettings(renderer, "Sales growth", "Date", "%",
				dateValues[0].getTime(),
				dateValues[dateValues.length - 1].getTime(), 0, 1000000,
				Color.GRAY, Color.LTGRAY);
		renderer.setYLabels(10);

		startActivity(ChartFactory.getTimeChartIntent(this,
				buildDateDataset(titles, dates, values), renderer, "MMM yyyy"));

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
