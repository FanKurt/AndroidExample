package com.example.testhistogram;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class HistogramView extends View {
	private Context context;
	public int i = 0, spacing = 0, count = 0, j;
	private Handler mHandler = new Handler();
	private ArrayList<Integer> percent = new ArrayList<Integer>();
	
	public HistogramView(Context context) {
		super(context);
		this.context = context;
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawRect(150 + setSapcing(count), 960 - i,
				300 + setSapcing(count), 960, getPaint());
		i+=10;
		if (i <= 960) {
			invalidate();
		} else {
			i = 0;
			count++;
			if (count < 4)
				invalidate();
		}
	}
	private Paint getPaint() {
		Paint p = new Paint();
		p.setColor(Color.RED);
		return p;
	}

	private int setSapcing(int j) {
		return spacing + j * 200;
	}

	public void setPercent(int per) {
		percent.add(per);
	}
}
