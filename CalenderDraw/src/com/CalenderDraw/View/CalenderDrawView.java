package com.CalenderDraw.View;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.CalenderDraw.Module.PaintText;
import com.CalenderDraw.Module.WH;

public class CalenderDrawView extends View implements OnTouchListener {
	public interface CalOnClickListener {
		public void CalOnClick(CalenderDrawView CV, int x, int y);
	}

	private Context context;
	private final int column = 7;
	private final int row = 6;

	private int DWidth, DHeight;// 原始寬高
	private ArrayList<String> WeekTexts;
	private PaintText PT;
	private float WeekTextHeight = 0;
	private WH WH;
	private int WeekTextSize = 20;

	private int styleRes = -1;
	private Bitmap DayBGStyle = null;

	private int styleRes2 = -1;
	private Bitmap DayBGStyle2 = null;

	private CalOnClickListener COCL;
	private int oColumn = 0, oRow = 0;
	private boolean FLAG = false;

	public CalenderDrawView(Context context) {
		super(context);
		this.context = context;
		this.setOnTouchListener(this);
		init();
	}

	private void init() {
		{
			WH = new WH(context);
		}
		{
			WeekTexts = new ArrayList<String>();
			WeekTexts.add("日");
			WeekTexts.add("一");
			WeekTexts.add("二");
			WeekTexts.add("三");
			WeekTexts.add("四");
			WeekTexts.add("五");
			WeekTexts.add("六");

			getcalWeekTextHeight();
		}
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.DWidth = getWidth();
		this.DHeight = getHeight();
		// 設定文字(日、一、二...)
		{
			for (int i = 0; i < WeekTexts.size(); i++) {
				float ww = (float) DWidth / (float) column / (float) 2;
				float textHalf = this.PT.getWidth() / 2;
				canvas.drawText(WeekTexts.get(i), getXPointDay(i) + ww
						- textHalf, 0 + this.PT.getCurrent(), this.PT);
			}
		}
		{

			if (styleRes != -1) {
				setBitmap(styleRes);
			}

			if (DayBGStyle != null) {
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < column; j++) {
						canvas.drawBitmap(DayBGStyle, getXPointDay(j),
								getYPointDay(i), new Paint());
					}
				}
			}
		}

		{
			if (styleRes2 != -1) {
				setBitmap2(styleRes2);
			}
			if (FLAG) {
				canvas.drawBitmap(DayBGStyle2, getXPointDay(oColumn),
						getYPointDay(oRow), new Paint());
				FLAG = false;
			}
		}
	}

	private float getXPointDay(int x) {
		return ((float) DWidth / (float) column) * x;
	}

	private float getYPointDay(int y) {
		return ((((float) DHeight - WeekTextHeight) / (float) row) * y)
				+ WeekTextHeight;
	}

	private float getcalWeekTextHeight() {
		if (this.PT == null) {
			this.PT = new PaintText(WeekTexts.get(0));
		}
		this.PT.setTextSize(WH.getTextSize(WeekTextSize));
		return WeekTextHeight = this.PT.getHeight();
	}

	public void setWeekSize(int size) {
		this.WeekTextSize = size;
		getcalWeekTextHeight();
		invalidate();
	}

	private float getDayWidth() {
		return (float) DWidth / (float) column;
	}

	private float getDayHeight() {
		return ((float) DHeight - WeekTextHeight) / (float) row;
	}

	public void setWeekText(ArrayList<String> values) {
		this.WeekTexts = values;
	}

	public void setBitmap(int res) {// 產生圖的方法
		Bitmap obitmap = BitmapFactory.decodeResource(getResources(), res);
		int oBMwidth = obitmap.getWidth();
		int oBMheight = obitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale(getDayWidth() / (float) oBMwidth, getDayHeight()
				/ (float) oBMheight);
		DayBGStyle = Bitmap.createBitmap(obitmap, 0, 0, oBMwidth, oBMheight,
				matrix, true);

		styleRes = -1;
	}

	public void setDayBackgroundStyle1(int res) {
		this.styleRes = res;
		invalidate();
	}

	public void setBitmap2(int res) {// 產生圖的方法
		Bitmap obitmap = BitmapFactory.decodeResource(getResources(), res);
		int oBMwidth = obitmap.getWidth();
		int oBMheight = obitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale(getDayWidth() / (float) oBMwidth, getDayHeight()
				/ (float) oBMheight);
		DayBGStyle2 = Bitmap.createBitmap(obitmap, 0, 0, oBMwidth, oBMheight,
				matrix, true);

		styleRes2 = -1;
	}

	public void setDayBackgroundStyle2(int res) {
		this.styleRes2 = res;
		invalidate();
	}

	public void setCalOnClickListener(CalOnClickListener COCL) {
		this.COCL = COCL;
	}

	public boolean onTouch(View v, MotionEvent event) {
		float rx = event.getX();
		float ry = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int aColumn = (int) (rx / getDayWidth());
			int aRow = (int) (ry / getDayHeight());
			oColumn = aColumn;
			oRow = aRow;
			
			if (this.COCL != null) {
				this.COCL.CalOnClick(this, aColumn, aRow);
				FLAG = true;
				invalidate();
			}
			break;
		case MotionEvent.ACTION_UP:
			int nColumn = (int) (rx / getDayWidth());
			int nRow = (int) (ry / getDayHeight());
			if (nColumn == oColumn && nRow == oRow) {
				FLAG = false;
				invalidate();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		return true;
	}
}
