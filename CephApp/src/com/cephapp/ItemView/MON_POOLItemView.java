package com.cephapp.ItemView;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.IMAC.FrameWork.WH;

public class MON_POOLItemView extends RelativeLayout {
	private WH WH ;
	private RelativeLayout CardView  ,mBar ,idRelativeLayout , addrRelativeLayout , latencyRelativeLayout , skewRelativeLayout;
	private TextView title , idTitle ,idContent , addrTitle , addrContent 
					,latencyTitle , latencyContent , skewTitle , skewContent;
	private View greenView , lineView1 ,lineView2,lineView3;
	public MON_POOLItemView(Context context){
		super(context);
		WH = new WH(context);
		init();
	}
	private void init(){
		AbsListView.LayoutParams LP = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
		this.setLayoutParams(LP);
		CardView();
		ID();
		ADDR();
		LATENCY();
		SKEW();
	}
	
	private void SKEW() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(10));
			LP.addRule(RelativeLayout.BELOW ,latencyRelativeLayout.getId());
			skewRelativeLayout = new RelativeLayout(getContext());
			skewRelativeLayout.setBackgroundColor(Color.WHITE);
			skewRelativeLayout.setId(getRandomId());
			skewRelativeLayout.setLayoutParams(LP);
			CardView.addView(skewRelativeLayout);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(1.5), LayoutParams.MATCH_PARENT);
			LP.topMargin = WH.getH(0.5);
			LP.bottomMargin = WH.getH(0.5);
			greenView = new View(getContext());
			greenView.setBackgroundColor(Color.rgb(65, 166, 87));
			greenView.setId(getRandomId());
			greenView.setLayoutParams(LP);
			skewRelativeLayout.addView(greenView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.RIGHT_OF ,greenView.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			skewTitle = new TextView(getContext());
			skewTitle.setText("skew");
			skewTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			skewTitle.setTextColor(Color.rgb(39, 95, 180));
			skewTitle.setId(getRandomId());
			skewTitle.setLayoutParams(LP);
			skewRelativeLayout.addView(skewTitle);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.CENTER_IN_PARENT);
			skewContent = new TextView(getContext());
			skewContent.setText("0");
			skewContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			skewContent.setTextColor(Color.BLACK);
			skewContent.setId(getRandomId());
			skewContent.setLayoutParams(LP);
			skewRelativeLayout.addView(skewContent);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(0.1));
			LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			LP.addRule(RelativeLayout.ALIGN_LEFT , skewTitle.getId());
			lineView3 = new View(getContext());
			lineView3.setBackgroundColor(Color.GRAY);
			lineView3.setLayoutParams(LP);
			skewRelativeLayout.addView(lineView3);
		}
	}
	private void LATENCY() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(10));
			LP.addRule(RelativeLayout.BELOW ,addrRelativeLayout.getId());
			latencyRelativeLayout = new RelativeLayout(getContext());
			latencyRelativeLayout.setBackgroundColor(Color.WHITE);
			latencyRelativeLayout.setId(getRandomId());
			latencyRelativeLayout.setLayoutParams(LP);
			CardView.addView(latencyRelativeLayout);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(1.5), LayoutParams.MATCH_PARENT);
			LP.topMargin = WH.getH(0.5);
			LP.bottomMargin = WH.getH(0.5);
			greenView = new View(getContext());
			greenView.setBackgroundColor(Color.rgb(65, 166, 87));
			greenView.setId(getRandomId());
			greenView.setLayoutParams(LP);
			latencyRelativeLayout.addView(greenView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.RIGHT_OF ,greenView.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			latencyTitle = new TextView(getContext());
			latencyTitle.setText("latency");
			latencyTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			latencyTitle.setTextColor(Color.rgb(39, 95, 180));
			latencyTitle.setId(getRandomId());
			latencyTitle.setLayoutParams(LP);
			latencyRelativeLayout.addView(latencyTitle);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.CENTER_IN_PARENT);
			latencyContent = new TextView(getContext());
			latencyContent.setText("0");
			latencyContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			latencyContent.setTextColor(Color.BLACK);
			latencyContent.setId(getRandomId());
			latencyContent.setLayoutParams(LP);
			latencyRelativeLayout.addView(latencyContent);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(0.1));
			LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			LP.addRule(RelativeLayout.ALIGN_LEFT , latencyTitle.getId());
			lineView3 = new View(getContext());
			lineView3.setBackgroundColor(Color.GRAY);
			lineView3.setLayoutParams(LP);
			latencyRelativeLayout.addView(lineView3);
		}
	}
	private void ADDR() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(10));
			LP.addRule(RelativeLayout.BELOW ,idRelativeLayout.getId());
			addrRelativeLayout = new RelativeLayout(getContext());
			addrRelativeLayout.setBackgroundColor(Color.WHITE);
			addrRelativeLayout.setId(getRandomId());
			addrRelativeLayout.setLayoutParams(LP);
			CardView.addView(addrRelativeLayout);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(1.5), LayoutParams.MATCH_PARENT);
			LP.topMargin = WH.getH(0.5);
			LP.bottomMargin = WH.getH(0.5);
			greenView = new View(getContext());
			greenView.setBackgroundColor(Color.rgb(65, 166, 87));
			greenView.setId(getRandomId());
			greenView.setLayoutParams(LP);
			addrRelativeLayout.addView(greenView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.RIGHT_OF ,greenView.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			addrTitle = new TextView(getContext());
			addrTitle.setText("addr");
			addrTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			addrTitle.setTextColor(Color.rgb(39, 95, 180));
			addrTitle.setId(getRandomId());
			addrTitle.setLayoutParams(LP);
			addrRelativeLayout.addView(addrTitle);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.CENTER_IN_PARENT);
			addrContent = new TextView(getContext());
			addrContent.setText("10.21.20.161:6789/0");
			addrContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			addrContent.setTextColor(Color.BLACK);
			addrContent.setId(getRandomId());
			addrContent.setLayoutParams(LP);
			addrRelativeLayout.addView(addrContent);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(0.1));
			LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			LP.addRule(RelativeLayout.ALIGN_LEFT , addrTitle.getId());
			lineView2 = new View(getContext());
			lineView2.setBackgroundColor(Color.GRAY);
			lineView2.setLayoutParams(LP);
			addrRelativeLayout.addView(lineView2);
		}
	}
	private void ID() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(10));
			LP.addRule(RelativeLayout.BELOW ,mBar.getId());
			idRelativeLayout = new RelativeLayout(getContext());
			idRelativeLayout.setBackgroundColor(Color.WHITE);
			idRelativeLayout.setId(getRandomId());
			idRelativeLayout.setLayoutParams(LP);
			CardView.addView(idRelativeLayout);
		}
		{
			LayoutParams LP = new LayoutParams(WH.getH(1.5), LayoutParams.MATCH_PARENT);
			LP.topMargin = WH.getH(0.5);
			LP.bottomMargin = WH.getH(0.5);
			greenView = new View(getContext());
			greenView.setBackgroundColor(Color.rgb(65, 166, 87));
			greenView.setId(getRandomId());
			greenView.setLayoutParams(LP);
			idRelativeLayout.addView(greenView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.RIGHT_OF ,greenView.getId());
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			idTitle = new TextView(getContext());
			idTitle.setText("id");
			idTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			idTitle.setTextColor(Color.rgb(39, 95, 180));
			idTitle.setId(getRandomId());
			idTitle.setLayoutParams(LP);
			idRelativeLayout.addView(idTitle);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.addRule(RelativeLayout.CENTER_IN_PARENT);
			idContent = new TextView(getContext());
			idContent.setText("0");
			idContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(35));
			idContent.setTextColor(Color.BLACK);
			idContent.setId(getRandomId());
			idContent.setLayoutParams(LP);
			idRelativeLayout.addView(idContent);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(0.1));
			LP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			LP.addRule(RelativeLayout.ALIGN_LEFT , idTitle.getId());
			lineView1 = new View(getContext());
			lineView1.setBackgroundColor(Color.GRAY);
			lineView1.setLayoutParams(LP);
			idRelativeLayout.addView(lineView1);
		}
	}
	private void CardView() {
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			LP.bottomMargin = WH.getH(2);
			CardView = new RelativeLayout(getContext());
			CardView.setBackgroundColor(Color.WHITE);
			CardView.setId(getRandomId());
			CardView.setLayoutParams(LP);
			this.addView(CardView);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(6));
			mBar = new RelativeLayout(getContext());
			mBar.setBackgroundColor(Color.rgb(39, 95, 180));
			mBar.setId(getRandomId());
			mBar.setLayoutParams(LP);
			CardView.addView(mBar);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			LP.leftMargin = WH.getH(1);
			LP.addRule(RelativeLayout.CENTER_VERTICAL);
			title = new TextView(getContext());
			title.setText("ceph-node1");
			title.setTextSize(TypedValue.COMPLEX_UNIT_PX, WH.getTextSize(32));
			title.setTextColor(Color.WHITE);
			title.setId(getRandomId());
			title.setLayoutParams(LP);
			mBar.addView(title);
		}
	}
	
	private int getRandomId() {
		return (int) (Math.random() * 1000000);
	}
	
	public TextView getMessageL(){
		return title;
	}
	
	public RelativeLayout getCardView(){
		return CardView;
	}
	
	
}
