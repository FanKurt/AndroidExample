package com.cephapp.ItemView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.IMAC.FrameWork.WH;

public class OSDItemView extends RelativeLayout {
	private WH WH ;
	private LinearLayout container;
	private RelativeLayout CardView  ,mBar;
	private TextView title ;
	public OSDItemView(Context context){
		super(context);
		WH = new WH(context);
		init();
	}
	private void init(){
		AbsListView.LayoutParams LP = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
		this.setLayoutParams(LP);
		CardView();
	}
	
	private void CardView() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			LP.bottomMargin = WH.getH(2);
			CardView = new RelativeLayout(getContext());
			CardView.setPadding(0, 0, 0, WH.getH(2));
			CardView.setBackgroundColor(Color.WHITE);
			CardView.setId(getRandomId());
			CardView.setLayoutParams(LP);
			this.addView(CardView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(4));
			mBar = new RelativeLayout(getContext());
			mBar.setBackgroundColor(Color.rgb(0, 166, 90));
			mBar.setId(getRandomId());
			mBar.setLayoutParams(LP);
			CardView.addView(mBar);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			title = new TextView(getContext());
			title.setText("node-58");
			title.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			title.setTextColor(Color.WHITE);
			title.setId(getRandomId());
			title.setLayoutParams(LP);
			mBar.addView(title);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			LP.addRule(RelativeLayout.BELOW ,mBar.getId() );
			container = new LinearLayout(getContext());
			container.setOrientation(LinearLayout.HORIZONTAL);
			container.setPadding(WH.getH(2), WH.getH(2), WH.getH(2), WH.getH(2));
			container.setId(getRandomId());
			container.setLayoutParams(LP);
			CardView.addView(container);
		}
	}
	
	private int getRandomId() {
		return (int) (Math.random() * 1000000);
	}
	
	
	public RelativeLayout getBar(){
		return mBar;
	}
	
	public LinearLayout getContainer(){
		return container;
	}
	
}
