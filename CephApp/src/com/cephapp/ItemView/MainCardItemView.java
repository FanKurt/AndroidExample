package com.cephapp.ItemView;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.IMAC.FrameWork.WH;
import com.example.cephapp.R;

public class MainCardItemView extends RelativeLayout{
	private WH WH ;
	private RelativeLayout CardView;
	private TextView message , seconds , health ;
	private View state_view , check_view;
	public MainCardItemView(Context context){
		super(context);
		WH = new WH(context);
		init();
	}
	private void init(){
		AbsListView.LayoutParams LP = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
//		LP.topMargin = WH.getH(2);
//		LP.leftMargin = WH.getH(2);
//		LP.rightMargin = WH.getH(2);
		this.setLayoutParams(LP);
		Bar();
		Image();
		CHECK();
	}
	private void CHECK() {
		{
			LayoutParams LP = new LayoutParams(WH.getH(5), WH.getH(5));
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			LP.rightMargin =WH.getH(1);
			LP.topMargin =WH.getH(1);
			check_view = new View(getContext());
			check_view.setBackgroundResource(R.drawable.ok_128);
			check_view.setId(getRandomId());
			check_view.setLayoutParams(LP);
			CardView.addView(check_view);
		}
	}
	private void Image() {
		{
			LayoutParams LP = new LayoutParams(WH.getH(5), WH.getH(5));
			LP.leftMargin =WH.getH(1);
			LP.topMargin =WH.getH(1);
			state_view = new View(getContext());
			state_view.setBackgroundResource(R.drawable.health_128);
			state_view.setId(getRandomId());
			state_view.setLayoutParams(LP);
			CardView.addView(state_view);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, WH.getH(5));
			LP.addRule(RelativeLayout.RIGHT_OF , state_view.getId());
			LP.addRule(RelativeLayout.ALIGN_BOTTOM , state_view.getId());
			LP.leftMargin = WH.getH(1);
			health = new TextView(getContext());
			health.setText("HEALTH");
			health.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(44));
			health.setTextColor(Color.BLACK);
			health.setGravity(Gravity.CENTER_VERTICAL);
			health.setId(getRandomId());
			health.setLayoutParams(LP);
			CardView.addView(health);
		}
	}
	private void Bar() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(30));
			CardView = new RelativeLayout(getContext());
			CardView.setBackgroundColor(Color.WHITE);
			CardView.setId(getRandomId());
			CardView.setLayoutParams(LP);
			this.addView(CardView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.CENTER_IN_PARENT);
			message = new TextView(getContext());
			message.setText("OK");
			message.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(44));
			message.setTextColor(Color.BLACK);
			message.setId(getRandomId());
			message.setLayoutParams(LP);
			CardView.addView(message);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.topMargin = WH.getH(4);
			LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
			LP.addRule(RelativeLayout.BELOW , message.getId());
			seconds = new TextView(getContext());
			seconds.setText("42 secs ago");
			seconds.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			seconds.setTextColor(Color.BLACK);
			seconds.setLayoutParams(LP);
			CardView.addView(seconds);
		}
	}
	
	private int getRandomId() {
		return (int) (Math.random() * 1000000);
	}
}
