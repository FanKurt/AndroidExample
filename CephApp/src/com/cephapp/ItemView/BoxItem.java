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

public class BoxItem extends RelativeLayout {
	private WH WH ;
	private RelativeLayout CardView ;
	private TextView title ;
	private int padding;
	public BoxItem(Context context){
		super(context);
		WH = new WH(context);
		init();
	}
	private void init(){
		AbsListView.LayoutParams LP = new AbsListView.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(LP);
		CardView();
	}
	
	private void CardView() {
		{
			LayoutParams LP = new LayoutParams(WH.getH(10), WH.getH(10));
			LP.rightMargin = WH.getH(2);
			CardView = new RelativeLayout(getContext());
			CardView.setBackgroundColor(Color.WHITE);
			CardView.setId(getRandomId());
			CardView.setLayoutParams(LP);
			this.addView(CardView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			title = new TextView(getContext());
			title.setText("node-58");
			title.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			title.setTextColor(Color.BLACK);
			title.setGravity(Gravity.CENTER);
			title.setId(getRandomId());
			title.setLayoutParams(LP);
			CardView.addView(title);
		}
		
	}
	
	private int getRandomId() {
		return (int) (Math.random() * 1000000);
	}
	
	public RelativeLayout getCardView(){
		return CardView;
	}
	
	public TextView getTitlTextView(){
		return title;
	}
}
