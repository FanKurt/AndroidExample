package com.cephapp.ItemView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Shader.TileMode;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.IMAC.FrameWork.WH;

public class LogItem extends RelativeLayout {
	private WH WH ;
	private RelativeLayout CardView1 ,CardView2 ;
	private TextView title1  ,title2;
	public LogItem(Context context){
		super(context);
		WH = new WH(context);
		init();
	}
	private void init(){
		LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT , WH.getH(30));
		LP.topMargin = WH.getH(1);
		this.setLayoutParams(LP);
		CardView1();
		CardView2();
		LineView();
	}
	
	private void LineView() {
		LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(0.1));
		LP.topMargin = WH.getH(1);
		LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		LP.addRule(RelativeLayout.ALIGN_LEFT , title2.getId());
		View view = new View(getContext());
		view.setBackgroundColor(Color.GRAY);
		view.setLayoutParams(LP);
		this.addView(view);
	}
	private void CardView1() {
		{
			LayoutParams LP = new LayoutParams(WH.getH(1), WH.getH(7));
			LP.rightMargin = WH.getH(1);
			LP.topMargin = WH.getH(1);
			CardView1  = new RelativeLayout(getContext());
			CardView1 .setBackgroundColor(Color.rgb(0, 166, 90));
			CardView1 .setId(getRandomId());
			CardView1 .setLayoutParams(LP);
			this.addView(CardView1 );
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.RIGHT_OF , CardView1 .getId());
			LP.addRule(RelativeLayout.ALIGN_TOP , CardView1 .getId());
			title1 = new TextView(getContext());
			title1.setText("node-58\nnode-58");
			title1.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			title1.setTextColor(Color.BLACK);
			title1.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
			title1.setId(getRandomId());
			title1.setLayoutParams(LP);
			this .addView(title1);
		}
		
	}
	
	private void CardView2() {
		{
			LayoutParams LP = new LayoutParams(WH.getH(1), LayoutParams.MATCH_PARENT);
			LP.addRule(RelativeLayout.BELOW , CardView1.getId());
			LP.rightMargin = WH.getH(1);
			LP.topMargin = WH.getH(0.5);
			LP.bottomMargin = WH.getH(1);
			CardView2  = new RelativeLayout(getContext());
			CardView2 .setBackgroundColor(Color.rgb(50, 117, 193));
			CardView2 .setId(getRandomId());
			CardView2 .setLayoutParams(LP);
			this.addView(CardView2 );
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.RIGHT_OF , CardView2 .getId());
			LP.addRule(RelativeLayout.ALIGN_TOP , CardView2 .getId());
			title2 = new TextView(getContext());
			title2.setText("node-58\nnode-58");
			title2.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			title2.setTextColor(Color.BLACK);
			title2.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
			title2.setId(getRandomId());
			title2.setLayoutParams(LP);
			this .addView(title2);
		}
		
	}
	
	private int getRandomId() {
		return (int) (Math.random() * 1000000);
	}
	
	public RelativeLayout getCardView(){
		return CardView1 ;
	}
	
	public TextView getTitlTextView(){
		return title1;
	}
}
