package com.HeadSet.Layout;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HeadSetLayout extends RelativeLayout{
	private Context context;
	private TextView tv ,tv1,tv2;
	private Button btn;
	private LinearLayout LL;
	public HeadSetLayout(Context context) {
		super(context);
		this.context = context;
		init();
	}
	private void init(){
		Numer();
		Hor();
		Button();
	}
	private void Numer(){
		LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
		LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
		tv = new TextView(context);
		tv.setId(1);
		tv.setText("123");
		tv.setLayoutParams(LP);
		this.addView(tv);
	}
	private void Hor(){
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.BELOW , tv.getId());
			LL = new LinearLayout(context);
			LL.setId(2);
			LL.setOrientation(LinearLayout.HORIZONTAL);
			LL.setLayoutParams(LP);
			this.addView(LL);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			tv1 = new TextView(context);
			tv1.setText("456");
			tv1.setLayoutParams(LP);
			LL.addView(tv1);
		}	
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			tv2 = new TextView(context);
			tv2.setText("789");
			tv2.setLayoutParams(LP);
			LL.addView(tv2);
		}
	}
	private void Button(){
		LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		LP.addRule(RelativeLayout.BELOW , LL.getId());
		btn = new Button(context);
		btn.setText("456");
		btn.setLayoutParams(LP);
		this.addView(btn);
	}
	
	public Button getButton(){
		return btn;
	}
}
