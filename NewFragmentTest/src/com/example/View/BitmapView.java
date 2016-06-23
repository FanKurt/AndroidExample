package com.example.View;

import java.io.InputStream;
import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

public class BitmapView extends View{
	private Bitmap bitmap;
	private Context context;
	private int scale=0;
	private WeakReference<Bitmap> WR;
	public BitmapView(Context context) {
		super(context);
		this.context = context;
	}
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
		if(bitmap !=null){
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			Matrix matrix = new Matrix();
			matrix.postScale((float) getWidth() / (float) bitmap.getWidth(),
					(float) getHeight() / (float) bitmap.getHeight());
			canvas.drawBitmap(bitmap, matrix, paint);
		}
	}
	
	public Drawable getBackground() {
		if(bitmap!=null&&!bitmap.isRecycled()){
			bitmap.recycle();
			bitmap = null;
		}
		Log.e("", "---------");
		return super.getBackground();
	}
	
	public void setBitmap(int id){
		bitmap = image(id);
		invalidate();
	}
	public void setBitmap(int id , int scale){
		this.scale = scale;
		bitmap = image(id);
		invalidate();
	}
	public void setBitmap(Bitmap bitmap , int scale){
		this.scale = scale;
		this.bitmap = bitmap;
		invalidate();
	}
	
	private Bitmap image(int id){
		InputStream inputStream = context.getResources()
				.openRawResource(id);
		WR = new WeakReference<Bitmap>( BitmapFactory.decodeStream(inputStream, null,
				getBitmapOptions(scale)));
		return WR.get();
	}
	private BitmapFactory.Options getBitmapOptions(int scale) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPurgeable = true;
		options.inInputShareable = true;
		options.inSampleSize = scale;
		return options;
	}
}
