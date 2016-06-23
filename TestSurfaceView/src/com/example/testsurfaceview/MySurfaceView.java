package com.example.testsurfaceview;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements
		SurfaceHolder.Callback, Runnable {
	private Paint paint = new Paint();
	Handler mHandler = new Handler();
	float sweepAngle = 0;
	float startX, startY, stopX, stopY;
	private float starAngle = 0, Total = 0;
	public int i=0;
	private ArrayList<Float> percent = new ArrayList<Float>();
	public MySurfaceView(Context context) {
		super(context);
		paint.setColor(Color.YELLOW);
		getHolder().addCallback(this);
	}
	public void draw() {
		Canvas canvas = getHolder().lockCanvas();
		if (!percent.isEmpty()) {
			if (i <= percent.size()) {
				for (int j = 0; j < i; j++) {
					drawArc(canvas, j);
				}
			}
		}
		getHolder().unlockCanvasAndPost(canvas);
	}
	public void run() {
		if (sweepAngle >= 0) {
			i++;
			starAngle = 0;
			if (i <= percent.size())
				draw();
			mHandler.postDelayed(this, 1000);
		} else {
			surfaceDestroyed(getHolder());
		}
	}
	public void startHandler() {
		mHandler.post(this);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		startHandler();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// mHandler.removeCallbacks(this);
	}
	private void drawArc(Canvas canvas, int j){
		canvas.drawArc(getRectF(getWidth(), getHeight()), starAngle,
				360 * (percent.get(j) / Total), true, getPaint());
		starAngle = starAngle + (360 * (percent.get(j) / Total));
	}
	private Paint getPaint() {
		Paint p = new Paint();
		Random rnd = new Random();
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),
				rnd.nextInt(256));
		p.setColor(color);
		return p;
	}
	private RectF getRectF(int W, int H) {
		RectF oval2 = new RectF(0, 0, W, H);
		return oval2;
	}
	public void setPercent(Float per) {
		percent.add(per);
		Total = Total + per;
	}
}