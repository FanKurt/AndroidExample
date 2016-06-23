package com.example.testsurfaceview;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MySurfaceLayout extends RelativeLayout {
	Context context;
	MySurfaceView MSV;
	Button btn;
	public MySurfaceLayout(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init(){
		{
			LayoutParams LP = new LayoutParams(500 ,500);
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			MSV = new MySurfaceView(context);
			float[] per = {1f,2f,3f,5f};
			for(float percent :per ){
				MSV.setPercent(percent);
			}
			MSV.setLayoutParams(LP);
			this.addView(MSV);
		}
		{
			LayoutParams LP = new LayoutParams(200 ,200);
			btn = new Button(context);
			btn.setLayoutParams(LP);
			this.addView(btn);
		}
	}
	public MySurfaceView getMSV(){
		return MSV;
	}
	public Button getButton(){
		return btn;
	}
}
