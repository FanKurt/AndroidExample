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

public class SliderItemView01 extends RelativeLayout{
	private WH WH ;
	private RelativeLayout bacLayout;
	private View iconView;
	private TextView mtTextView ;
	public SliderItemView01(Context context){
		super(context);
		WH = new WH(context);
		init();
	}
	private void init(){
		AbsListView.LayoutParams LP = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
		this.setLayoutParams(LP);
		Bar();
	}
	private void Bar() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(10));
			bacLayout = new RelativeLayout(getContext());
			bacLayout.setBackgroundColor(Color.rgb(27, 35, 38));
			bacLayout.setId(getRandomId());
			bacLayout.setLayoutParams(LP);
			this.addView(bacLayout);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(4), WH.getH(4));
			LP.leftMargin = WH.getH(2);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			iconView = new View(getContext());
			iconView.setBackgroundResource(R.drawable.slider_logo);
			iconView.setId(getRandomId());
			iconView.setLayoutParams(LP);
			bacLayout.addView(iconView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			LP.addRule(RelativeLayout.RIGHT_OF , iconView.getId());
			LP.leftMargin = WH.getH(1);
			mtTextView = new TextView(getContext());
			mtTextView.setText("ÂO¶°Monitoring");
			mtTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(40));
			mtTextView.setTextColor(Color.WHITE);
			mtTextView.setGravity(Gravity.CENTER_VERTICAL);
			mtTextView.setId(getRandomId());
			mtTextView.setLayoutParams(LP);
			bacLayout.addView(mtTextView);
		}
	}
	
	private int getRandomId(){
		return (int) (Math.random() * 1000000);
	}
	
	public TextView getTextView (){
		return mtTextView;
	}
	public View getIconView (){
		return iconView;
	}
}
