package com.imac.ViewGroup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.imac.Framework.WH;

public class BlockViewGroup extends ViewGroup implements OnTouchListener {
	private WH WH;
	private ImageView imageView;
	private int initX = 30, initY = 30;
	private int Width = 175;
	private int Height = 175;
	private Boolean isClick = false;
	private float mScale = 0f;
	private static final int NONE = 0;// 初始狀態
	private static final int DRAG = 1;// 拖曳狀態
	private static final int ZOOM = 2;// 縮放狀態
	private int mode = NONE;
	private float dist = 1f;
 	private PointF mid = new PointF();
	public BlockViewGroup(Context context) {
		super(context);
		WH = new WH(context);
		imageView = new ImageView(this.getContext());
		this.setOnTouchListener(this);
		imageView.setTag("image");
		imageView.setBackgroundColor(Color.RED);
		addView(imageView);
	}

	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		imageView.measure(0, 0);
		imageView.layout(initX, initY, initX + Width, initY + Height);
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setX(int X) {
		this.initX = X;
	}

	public void setY(int Y) {
		this.initY = Y;
	}

	public void setWidth(int width) {
		this.Width = width;
	}

	public void setHeight(int height) {
		this.Height = height;
	}

	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {

		case MotionEvent.ACTION_DOWN:
			if (initX < event.getX() && event.getX() < initX + Width
					&& initY < event.getY() && event.getY() < initY + Height) {
				isClick = true;
				mode = DRAG;
			}
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			dist = spacing(event);
			// 如果兩點距離超過10, 就判斷為多點觸控模式 即為縮放模式
			if (spacing(event) > 10f) {
				isClick = true;
				midPoint(mid, event);
				mode = ZOOM;
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
	            mode = NONE;
	            break;
		case MotionEvent.ACTION_MOVE:
			if (isClick) {
				if(mode == DRAG){
					initX = (int) event.getX()-(Width/2);
					initY = (int) event.getY()-(Height/2);
					this.requestLayout();
				}
				else if (mode == ZOOM) {
	                float newDist = spacing(event);//偵測兩根手指移動的距離
	                if (newDist > 10f) {
	                    float tScale = newDist / dist;
	                    if(tScale!=mScale){
	                    	initX =(int)(mid.x)-(Width/2);
	  	                    initY =(int)(mid.y)-(Height/2);
	  	                    Width=(int)(mid.x)+(Width/2);
	  	                    Height=(int)(mid.y)+(Height/2);
	  	                    
	  	                    Log.e("", "initX :"+initX +"\ninitY : "+initY
	  	                    		 +"\nWidth : "+Width +"\nHeight : "+Height);
	  	                    this.requestLayout();
	                    }
	                }
	            }
			}
			break;
		case MotionEvent.ACTION_UP:
			isClick = false;
			break;
		}

		return true;
	}

	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}
	//兩點的中點
	private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
	
}
