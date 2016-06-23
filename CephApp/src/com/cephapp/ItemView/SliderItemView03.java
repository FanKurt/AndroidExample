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

public class SliderItemView03 extends RelativeLayout{
	private WH WH ;
	private RelativeLayout bacLayout;
	private View lineView;
	private TextView mtTextView ;
	public SliderItemView03(Context context){
		super(context);
		WH = new WH(context);
		init();
	}
	private void init(){
		AbsListView.LayoutParams LP = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
		this.setLayoutParams(LP);
		this.setPadding(WH.getH(3), 0, 0, 0);
		Bar();
	}
	private void Bar() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(9));
			bacLayout = new RelativeLayout(getContext());
			bacLayout.setId(getRandomId());
			bacLayout.setLayoutParams(LP);
			this.addView(bacLayout);
		}
		
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			LP.leftMargin = WH.getH(1);
			mtTextView = new TextView(getContext());
			mtTextView.setText("ÂO¶°MDS¸ê°T");
			mtTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(36));
			mtTextView.setTextColor(Color.WHITE);
			mtTextView.setGravity(Gravity.CENTER_VERTICAL);
			mtTextView.setId(getRandomId());
			mtTextView.setLayoutParams(LP);
			bacLayout.addView(mtTextView);
		}
		
	}
	
	private int getRandomId() {
		return (int) (Math.random() * 1000000);
	}
	
	public TextView getTextView (){
		return mtTextView;
	}
}
