package com.example.hellochart;

import java.util.ArrayList;
import java.util.List;
import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String[] titles = new String[] { "��u1", "��u2" }; // �w�q��u���W��
		List<double[]> x = new ArrayList<double[]>(); // �I��x����
		List<double[]> y = new ArrayList<double[]>(); // �I��y����
		// �ƭ�X,Y���Эȿ�J
		x.add(new double[] { 1, 3, 5, 7, 9, 11 });
		x.add(new double[] { 0, 2, 4, 6, 8, 10 });
		y.add(new double[] { 3, 14, 8, 22, 16, 18 });
		y.add(new double[] { 20, 18, 15, 12, 10, 8 });
		XYMultipleSeriesDataset dataset = buildDatset(titles, x, y); // �x�s�y�Э�

		int[] colors = new int[] { Color.BLUE, Color.GREEN };// ��u���C��
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE,
				PointStyle.DIAMOND }; // ��u�I���Ϊ�
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles, true);

		setChartSettings(renderer, "��u�Ϯi��", "X�b�W��", "Y�b�W��", 0, 12, 0, 25,
				Color.BLACK);// �w�q��u��
		View chart = ChartFactory.getLineChartView(this, dataset, renderer);
		setContentView(chart);
	}

	// �w�q��u�ϦW��
	protected void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor) {
		renderer.setChartTitle(title); // ��u�ϦW��
		renderer.setChartTitleTextSize(24); // ��u�ϦW�٦r�Τj�p
		renderer.setXTitle(xTitle); // X�b�W��
		renderer.setYTitle(yTitle); // Y�b�W��
		renderer.setXAxisMin(xMin); // X�b��̤ܳp��
		renderer.setXAxisMax(xMax); // X�b��̤ܳj��
		renderer.setXLabelsColor(Color.BLACK); // X�b�u�C��
		renderer.setYAxisMin(yMin); // Y�b��̤ܳp��
		renderer.setYAxisMax(yMax); // Y�b��̤ܳj��
		renderer.setAxesColor(axesColor); // �]�w���жb�C��
		renderer.setYLabelsColor(0, Color.BLACK); // Y�b�u�C��
		renderer.setLabelsColor(Color.BLACK); // �]�w�����C��
		renderer.setMarginsColor(Color.WHITE); // �]�w�I���C��
		renderer.setShowGrid(true); // �]�w��u
	}

	// �w�q��u�Ϫ��榡
	private XYMultipleSeriesRenderer buildRenderer(int[] colors,
			PointStyle[] styles, boolean fill) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			r.setFillPoints(fill);
			renderer.addSeriesRenderer(r); // �N�y���ܦ��u�[�J�Ϥ����
		}
		return renderer;
	}

	// ��ƳB�z
	private XYMultipleSeriesDataset buildDatset(String[] titles,
			List<double[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		int length = titles.length; // ��u�ƶq
		for (int i = 0; i < length; i++) {
			// XYseries��H,�Ω󴣨�ø�s���I���X�����
			XYSeries series = new XYSeries(titles[i]); // �̾ڨC���u���W�ٷs�W
			double[] xV = xValues.get(i); // �����i���u�����
			double[] yV = yValues.get(i);
			int seriesLength = xV.length; // ���X���I

			for (int k = 0; k < seriesLength; k++) // �C���u�̦��X���I
			{
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
		return dataset;
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
