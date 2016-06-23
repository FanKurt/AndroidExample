package com.cephapp.Layout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.IMAC.FrameWork.MainParentLayout;
import com.example.cephapp.R;

public class TopLayout extends MainParentLayout {
	private TextView mTextView;
	private ListView sliderListView;
	private RelativeLayout actionBar , main;
	private DrawerLayout mDrawerLayout;
	private Button sliderButton;
	public TopLayout(Context context) {
		super(context);
	}

	protected void init() {
		Bar();
	}
	
	private void Bar() {
		{
			DrawerLayout.LayoutParams LP = new DrawerLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			mDrawerLayout = new DrawerLayout(getContext());
			mDrawerLayout.setLayoutParams(LP);
			mDrawerLayout.setId(getRandomId());
			this.addView(mDrawerLayout);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			main = new RelativeLayout(getContext());
			main.setBackgroundColor(Color.rgb(170, 37, 38));
			main.setId(getRandomId());
			main.setLayoutParams(LP);
			mDrawerLayout.addView(main);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(10));
			actionBar = new RelativeLayout(getContext());
			actionBar.setBackgroundColor(Color.rgb(170, 37, 38));
			actionBar.setId(getRandomId());
			actionBar.setLayoutParams(LP);
			main.addView(actionBar);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.CENTER_IN_PARENT);
			mTextView = new TextView(getContext());
			mTextView.setText("Overall");
			mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(40));
			mTextView.setTextColor(Color.WHITE);
			mTextView.setLayoutParams(LP);
			actionBar.addView(mTextView);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(5), WH.getH(5));
			LP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			LP.rightMargin = WH.getH(2);
			sliderButton = new Button(getContext());
			sliderButton.setBackgroundResource(R.drawable.slider_logo);
			sliderButton.setId(getRandomId());
			sliderButton.setLayoutParams(LP);
			actionBar.addView(sliderButton);
		}
		
		{
			DrawerLayout.LayoutParams LP = new DrawerLayout.LayoutParams(
					WH.getW(85), LayoutParams.MATCH_PARENT, Gravity.RIGHT);
			sliderListView  = new ListView(getContext());
			sliderListView.setBackgroundColor(Color.rgb(34, 46, 51));
			sliderListView.setDivider(new ColorDrawable(Color.TRANSPARENT));
			sliderListView.setDividerHeight(1);
			sliderListView.setLayoutParams(LP);
			mDrawerLayout.addView(sliderListView);
		}
		
	}
	
	public DrawerLayout getDrawerLayout(){
		return mDrawerLayout;
	}
	
	public ListView getDrawerListView(){
		return sliderListView;
	}
	public Button getSliderButton(){
		return sliderButton;
	}
}
