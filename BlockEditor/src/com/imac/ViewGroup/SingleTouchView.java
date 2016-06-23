package com.imac.ViewGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.blockeditor.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class SingleTouchView extends View {
	public static final float MAX_SCALE = 10.0f;
	public static final float MIN_SCALE = 0.3f;
	
	public static final int LEFT_TOP = 0;
	public static final int RIGHT_TOP = 1;
	public static final int RIGHT_BOTTOM = 2;
	public static final int LEFT_BOTTOM = 3;
	
	public static final int DEFAULT_FRAME_PADDING = 8;
	public static final int DEFAULT_FRAME_WIDTH = 2;
	public static final int DEFAULT_FRAME_COLOR = Color.WHITE;
	public static final float DEFAULT_SCALE = 1.0f;
	public static final float DEFAULT_DEGREE = 0;
	public static final int DEFAULT_CONTROL_LOCATION = RIGHT_TOP;
	public static final boolean DEFAULT_EDITABLE = true;
	
	private int start_x;
	private int start_y;
	private int stop_x;
	private int stop_y;
	
	private Bitmap mBitmap;
	
	private PointF mCenterPoint = new PointF();
	
	private int mViewWidth, mViewHeight;
	
	private float mDegree = DEFAULT_DEGREE;
	
	private float mScale = DEFAULT_SCALE;
	
	private Matrix matrix = new Matrix();
	
	private int mViewPaddingLeft;
	
	private int mViewPaddingTop;
	
	private Point mLTPoint;
	private Point mRTPoint;
	private Point mRBPoint;
	private Point mLBPoint;
	
	private Point mControlPoint = new Point();
	
	private Drawable controlDrawable;
	
	private int mDrawableWidth, mDrawableHeight;
	
	private Path mPath = new Path();
	
	private Paint mPaint ;
	
	public static final int STATUS_INIT = 0;
	
	public static final int STATUS_DRAG = 1;
	
	public static final int STATUS_ROTATE_ZOOM = 2; 
	
	private int mStatus = STATUS_INIT;
	
	private int framePadding = DEFAULT_FRAME_PADDING;
	
	private int frameColor = DEFAULT_FRAME_COLOR;
	private int frameWidth = DEFAULT_FRAME_WIDTH;
	
	private boolean isEditable = DEFAULT_EDITABLE;
	
	private DisplayMetrics metrics;
	
	
	private PointF mPreMovePointF = new PointF();
	private PointF mCurMovePointF = new PointF();
	
	private int offsetX;
	private int offsetY;
	
	private int controlLocation = DEFAULT_CONTROL_LOCATION;

	
	public SingleTouchView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SingleTouchView(Context context) {
		this(context, null);
	}

	public SingleTouchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		obtainStyledAttributes(attrs);
		init();
	}
	
	private void obtainStyledAttributes(AttributeSet attrs){
		metrics = getContext().getResources().getDisplayMetrics();
		framePadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_FRAME_PADDING, metrics);
		frameWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_FRAME_WIDTH, metrics);
		
		TypedArray mTypedArray = getContext().obtainStyledAttributes(attrs,
				R.styleable.SingleTouchView);
		
		Drawable srcDrawble = mTypedArray.getDrawable(R.styleable.SingleTouchView_src);
		if(srcDrawble instanceof BitmapDrawable){
			BitmapDrawable bd = (BitmapDrawable) srcDrawble;
			this.mBitmap = bd.getBitmap();
		}
		
		framePadding = mTypedArray.getDimensionPixelSize(R.styleable.SingleTouchView_framePadding, framePadding);
		frameWidth = mTypedArray.getDimensionPixelSize(R.styleable.SingleTouchView_frameWidth, frameWidth);
		frameColor = mTypedArray.getColor(R.styleable.SingleTouchView_frameColor, DEFAULT_FRAME_COLOR);
		mScale = mTypedArray.getFloat(R.styleable.SingleTouchView_scale, DEFAULT_SCALE);
		mDegree = mTypedArray.getFloat(R.styleable.SingleTouchView_degree, DEFAULT_DEGREE);
		controlDrawable = mTypedArray.getDrawable(R.styleable.SingleTouchView_controlDrawable);
		controlLocation = mTypedArray.getInt(R.styleable.SingleTouchView_controlLocation, DEFAULT_CONTROL_LOCATION);
		isEditable = mTypedArray.getBoolean(R.styleable.SingleTouchView_editable, DEFAULT_EDITABLE);
		
		mTypedArray.recycle();
		
	}
	
	
	private void init(){
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(frameColor);
		mPaint.setStrokeWidth(frameWidth);
		mPaint.setStyle(Style.STROKE);
		
		if(controlDrawable == null){
			controlDrawable = getContext().getResources().getDrawable(R.drawable.st_rotate_icon);
		}
		
		mDrawableWidth = controlDrawable.getIntrinsicWidth();
		mDrawableHeight = controlDrawable.getIntrinsicHeight();
	}
	
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		ViewGroup mViewGroup = (ViewGroup) getParent();
		if(null != mViewGroup){
			int parentWidth = mViewGroup.getWidth();
			int parentHeight = mViewGroup.getHeight();
			mCenterPoint.set(parentWidth/2, parentHeight/2);
		}
	}
	
	private void adjustLayout(){
		int actualWidth = mViewWidth + mDrawableWidth;
		int actualHeight = mViewHeight + mDrawableHeight;
		int newPaddingLeft = (int) (mCenterPoint.x - actualWidth /2);
		int newPaddingTop = (int) (mCenterPoint.y - actualHeight/2);
		
		if(mViewPaddingLeft != newPaddingLeft || mViewPaddingTop != newPaddingTop){
			mViewPaddingLeft = newPaddingLeft;
			mViewPaddingTop = newPaddingTop;
			
			RelativeLayout.LayoutParams LP = new RelativeLayout.LayoutParams(actualWidth , actualHeight);
			LP.leftMargin =  stop_x - start_x;
			LP.topMargin = stop_y - start_y;
			this.setLayoutParams(LP);
			
//			layout(newPaddingLeft, newPaddingTop, newPaddingLeft + actualWidth, newPaddingTop + actualHeight);
		}
		
	
	}
	
	
	public void setImageBitamp(Bitmap bitmap){
		this.mBitmap = bitmap;
		transformDraw();
	}
	
	
	public void setImageDrawable(Drawable drawable){
		if(drawable instanceof BitmapDrawable){
			BitmapDrawable bd = (BitmapDrawable) drawable;
			this.mBitmap = bd.getBitmap();
			
			transformDraw();
		}else{
			throw new NotSupportedException("SingleTouchView not support this Drawable " + drawable);
		}
	}
	
	public void setImageResource(int resId){
		Drawable drawable = getContext().getResources().getDrawable(resId);
		setImageDrawable(drawable);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		adjustLayout();
		
		super.onDraw(canvas);
		
		if(mBitmap == null) return;
		canvas.drawBitmap(mBitmap, matrix, null);
		
		if(isEditable){
			mPath.reset();
			mPath.moveTo(mLTPoint.x, mLTPoint.y);
			mPath.lineTo(mRTPoint.x, mRTPoint.y);
			mPath.lineTo(mRBPoint.x, mRBPoint.y);
			mPath.lineTo(mLBPoint.x, mLBPoint.y);
			mPath.lineTo(mLTPoint.x, mLTPoint.y);
			mPath.lineTo(mRTPoint.x, mRTPoint.y);
			canvas.drawPath(mPath, mPaint);
			
			controlDrawable.setBounds(mControlPoint.x - mDrawableWidth / 2,
					mControlPoint.y - mDrawableHeight / 2, mControlPoint.x + mDrawableWidth
							/ 2, mControlPoint.y + mDrawableHeight / 2);
			controlDrawable.draw(canvas);
		}
	}
	
	
	private void transformDraw(){
		int bitmapWidth = (int)(mBitmap.getWidth() * mScale);
		int bitmapHeight = (int)(mBitmap.getHeight()* mScale);
		computeRect(-framePadding, -framePadding, bitmapWidth + framePadding, bitmapHeight + framePadding, mDegree);
		
		matrix.setScale(mScale, mScale);
//		matrix.postRotate(mDegree % 360, bitmapWidth/2, bitmapHeight/2);
		
		matrix.postTranslate(offsetX + mDrawableWidth/2, offsetY + mDrawableHeight/2);
		
		invalidate();
	}
	
	
	public boolean onTouchEvent(MotionEvent event) {
		if(!isEditable){
			return super.onTouchEvent(event);
		}
		switch (event.getAction() ) {
		case MotionEvent.ACTION_DOWN:
			
			stop_x = (int) event.getRawX();
			stop_y = (int) event.getRawY();
			start_x = (int) event.getX();
			start_y = stop_y - this.getTop();
			mPreMovePointF.set(event.getX() + mViewPaddingLeft, event.getY() + mViewPaddingTop);
			
			mStatus = JudgeStatus(event.getX(), event.getY());

			break;
		case MotionEvent.ACTION_UP:
			mStatus = STATUS_INIT;
			break;
		case MotionEvent.ACTION_MOVE:
			mCurMovePointF.set(event.getX() + mViewPaddingLeft, event.getY() + mViewPaddingTop);
			if (mStatus == STATUS_ROTATE_ZOOM) {
				float scale = 1f;
				
				int halfBitmapWidth = mBitmap.getWidth() / 2;
				int halfBitmapHeight = mBitmap.getHeight() /2 ;
				
				float bitmapToCenterDistance = FloatMath.sqrt(halfBitmapWidth * halfBitmapWidth + halfBitmapHeight * halfBitmapHeight);
				
				float moveToCenterDistance = distance4PointF(mCenterPoint, mCurMovePointF);
				scale = moveToCenterDistance / bitmapToCenterDistance;
				
				if (scale <= MIN_SCALE) {
					scale = MIN_SCALE;
				} else if (scale >= MAX_SCALE) {
					scale = MAX_SCALE;
				}
				mScale = scale;
				
				transformDraw();
			}
			else if (mStatus == STATUS_DRAG) {
				mCenterPoint.x += mCurMovePointF.x - mPreMovePointF.x;
				mCenterPoint.y += mCurMovePointF.y - mPreMovePointF.y;
		
				stop_x = (int) event.getRawX();
				stop_y = (int) event.getRawY();
				
				adjustLayout();
			}
			
			mPreMovePointF.set(mCurMovePointF);
			break;
		}
		return true;
	}
	
	
	private void computeRect(int left, int top, int right, int bottom, float degree){
		Point lt = new Point(left, top);
		Point rt = new Point(right, top);
		Point rb = new Point(right, bottom);
		Point lb = new Point(left, bottom);
		Point cp = new Point((left + right) / 2, (top + bottom) / 2);
		mLTPoint = obtainRoationPoint(cp, lt, degree);
		mRTPoint = obtainRoationPoint(cp, rt, degree);
		mRBPoint = obtainRoationPoint(cp, rb, degree);
		mLBPoint = obtainRoationPoint(cp, lb, degree);
		
		int maxCoordinateX = getMaxValue(mLTPoint.x, mRTPoint.x, mRBPoint.x, mLBPoint.x);
		int minCoordinateX = getMinValue(mLTPoint.x, mRTPoint.x, mRBPoint.x, mLBPoint.x);;
		
		mViewWidth = maxCoordinateX - minCoordinateX ;
		
		
		int maxCoordinateY = getMaxValue(mLTPoint.y, mRTPoint.y, mRBPoint.y, mLBPoint.y);
		int minCoordinateY = getMinValue(mLTPoint.y, mRTPoint.y, mRBPoint.y, mLBPoint.y);

		mViewHeight = maxCoordinateY - minCoordinateY ;
		
		
		Point viewCenterPoint = new Point((maxCoordinateX + minCoordinateX) / 2, (maxCoordinateY + minCoordinateY) / 2);
		
		offsetX = mViewWidth / 2 - viewCenterPoint.x;
		offsetY = mViewHeight / 2 - viewCenterPoint.y;
		
		
		
		int halfDrawableWidth = mDrawableWidth / 2;
		int halfDrawableHeight = mDrawableHeight /2;
		
		mLTPoint.x += (offsetX + halfDrawableWidth);
		mRTPoint.x += (offsetX + halfDrawableWidth);
		mRBPoint.x += (offsetX + halfDrawableWidth);
		mLBPoint.x += (offsetX + halfDrawableWidth);
		
		mLTPoint.y += (offsetY + halfDrawableHeight);
		mRTPoint.y += (offsetY + halfDrawableHeight);
		mRBPoint.y += (offsetY + halfDrawableHeight);
		mLBPoint.y += (offsetY + halfDrawableHeight);
		
		mControlPoint = LocationToPoint(controlLocation);
	}
	
	private Point LocationToPoint(int location){
		switch(location){
		case LEFT_TOP:
			return mLTPoint;
		case RIGHT_TOP:
			return mRTPoint;
		case RIGHT_BOTTOM:
			return mRBPoint;
		case LEFT_BOTTOM:
			return mLBPoint;
		}
		return mLTPoint;
	}
	
	public int getMaxValue(Integer...array){
		List<Integer> list = Arrays.asList(array);
		Collections.sort(list);
		return list.get(list.size() -1);
	}
	
	public int getMinValue(Integer...array){
		List<Integer> list = Arrays.asList(array);
		Collections.sort(list);
		return list.get(0);
	}
	
	public static Point obtainRoationPoint(Point center, Point source, float degree) {
		Point disPoint = new Point();
		disPoint.x = source.x - center.x;
		disPoint.y = source.y - center.y;
		
		double originRadian = 0;

		double originDegree = 0;
		
		double resultDegree = 0;
		
		double resultRadian = 0;
		
		Point resultPoint = new Point();
		
		double distance = Math.sqrt(disPoint.x * disPoint.x + disPoint.y * disPoint.y);
		if (disPoint.x == 0 && disPoint.y == 0) {
			return center;
		} else if (disPoint.x >= 0 && disPoint.y >= 0) {
			originRadian = Math.asin(disPoint.y / distance);
			
		} else if (disPoint.x < 0 && disPoint.y >= 0) {
			originRadian = Math.asin(Math.abs(disPoint.x) / distance);
			originRadian = originRadian + Math.PI / 2;
			
		} else if (disPoint.x < 0 && disPoint.y < 0) {
			originRadian = Math.asin(Math.abs(disPoint.y) / distance);
			originRadian = originRadian + Math.PI;
		} else if (disPoint.x >= 0 && disPoint.y < 0) {
			originRadian = Math.asin(disPoint.x / distance);
			originRadian = originRadian + Math.PI * 3 / 2;
		}
		
		originDegree = radianToDegree(originRadian);
		resultDegree = originDegree + degree;
		
		resultRadian = degreeToRadian(resultDegree);
		
		resultPoint.x = (int) Math.round(distance * Math.cos(resultRadian));
		resultPoint.y = (int) Math.round(distance * Math.sin(resultRadian));
		resultPoint.x += center.x;
		resultPoint.y += center.y;

		return resultPoint;
	}

	public static double radianToDegree(double radian) {
		return radian * 180 / Math.PI;
	}

	public static double degreeToRadian(double degree) {
		return degree * Math.PI / 180;
	}
	
	private int JudgeStatus(float x, float y){
		PointF touchPoint = new PointF(x, y);
		PointF controlPointF = new PointF(mControlPoint);
		
		float distanceToControl = distance4PointF(touchPoint, controlPointF);
		
		if(distanceToControl < Math.min(mDrawableWidth/2, mDrawableHeight/2)){
			return STATUS_ROTATE_ZOOM;
		}
		
		return STATUS_DRAG;
		
	}
	
	
	public float getImageDegree() {
		return mDegree;
	}

	public void setImageDegree(float degree) {
		if(this.mDegree != degree){
			this.mDegree = degree;
			transformDraw();
		}
	}

	public float getImageScale() {
		return mScale;
	}

	public void setImageScale(float scale) {
		if(this.mScale != scale){
			this.mScale = scale;
			transformDraw();
		};
	}
	

	public Drawable getControlDrawable() {
		return controlDrawable;
	}

	public void setControlDrawable(Drawable drawable) {
		this.controlDrawable = drawable;
		mDrawableWidth = drawable.getIntrinsicWidth();
		mDrawableHeight = drawable.getIntrinsicHeight();
		transformDraw();
	}

	public int getFramePadding() {
		return framePadding;
	}

	public void setFramePadding(int framePadding) {
		if(this.framePadding == framePadding)
			return;
		this.framePadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, framePadding, metrics);
		transformDraw();
	}

	public int getFrameColor() {
		return frameColor;
	}

	public void setFrameColor(int frameColor) {
		if(this.frameColor == frameColor)
			return;
		this.frameColor = frameColor;
		mPaint.setColor(frameColor);
		invalidate();
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		if(this.frameWidth == frameWidth) 
			return;
		this.frameWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, frameWidth, metrics);
		mPaint.setStrokeWidth(frameWidth);
		invalidate();
	}
	
	public void setControlLocation(int location) {
		if(this.controlLocation == location)
			return;
		this.controlLocation = location;
		transformDraw();
	}

	public int getControlLocation() {
		return controlLocation;
	}
	
	

	public PointF getCenterPoint() {
		return mCenterPoint;
	}

	public void setCenterPoint(PointF mCenterPoint) {
		this.mCenterPoint = mCenterPoint;
		adjustLayout();
	}
	

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
		invalidate();
	}

	private float distance4PointF(PointF pf1, PointF pf2) {
		float disX = pf2.x - pf1.x;
		float disY = pf2.y - pf1.y;
		return FloatMath.sqrt(disX * disX + disY * disY);
	}
	
	
	public static class NotSupportedException extends RuntimeException{
		private static final long serialVersionUID = 1674773263868453754L;

		public NotSupportedException() {
			super();
		}

		public NotSupportedException(String detailMessage) {
			super(detailMessage);
		}
		
	}

}
