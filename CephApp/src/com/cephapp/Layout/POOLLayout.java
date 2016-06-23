package com.cephapp.Layout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.IMAC.FrameWork.WH;

public class POOLLayout extends RelativeLayout {
	private ListView mListView ;
	private WH WH;
	public POOLLayout(Context context){
		super(context);
		WH = new WH(context);
		init();
	}
	private void init(){
		ListView();
	}
	private void ListView() {
		
		LayoutParams LP =new LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT );
		mListView  = new ListView(getContext());
		mListView.setPadding(WH.getH(2), WH.getH(2), WH.getH(2), WH.getH(2));
		mListView.setVerticalScrollBarEnabled(false);
		mListView.setDivider(new ColorDrawable(Color.TRANSPARENT));
		mListView.setDividerHeight(1);
		mListView.setBackgroundColor(Color.rgb(200, 204, 215));
		mListView.setLayoutParams(LP);
		this.addView(mListView);
		
	}
	
	public ListView getListView(){
		return mListView;
	}
	
}
