package com.example.Layout;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

import com.example.IMAC.MainParentLayout;

public class MainFrameLayout extends MainParentLayout{
	private RelativeLayout  Frame01 ,Frame02;
	public MainFrameLayout(Context context) {
		super(context);
	}
	protected void init() {
		Frame01();
		Frame02();
	}
	private void Frame01(){
		LayoutParams LP = getLayoutParams(MATCH_PARENT, WH.getH(10));
		LP.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		Frame01 = new RelativeLayout(context);
		Frame01.setId(getRandomId());
		Frame01.setLayoutParams(LP);
		this.addView(Frame01);
	}
	private void Frame02(){
		LayoutParams LP = getLayoutParams(MATCH_PARENT, MATCH_PARENT);
		LP.addRule(RelativeLayout.BELOW , Frame01.getId());
		Frame02 = new RelativeLayout(context);
		Frame02.setId(getRandomId());
		Frame02.setBackgroundColor(Color.RED);
		Frame02.setLayoutParams(LP);
		this.addView(Frame02);
	}
	
	public RelativeLayout getFrame01(){
		return Frame01;
	}
	public RelativeLayout getFrame02(){
		return Frame02;
	}
}
