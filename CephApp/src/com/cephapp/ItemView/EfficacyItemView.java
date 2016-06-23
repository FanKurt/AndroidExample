package com.cephapp.ItemView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.IMAC.FrameWork.WH;

public class EfficacyItemView extends RelativeLayout {
	private WH WH ;
	private RelativeLayout CardView  ,mBar , LineChartBox;
	private TextView title;
	private LineChartView chart;
	public EfficacyItemView(Context context){
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
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, WH.getH(40));
			LP.addRule(RelativeLayout.BELOW , mBar.getId());
			LineChartBox = new RelativeLayout(getContext());
			
			LineChartBox.setBackgroundColor(Color.WHITE);
			LineChartBox.setId(getRandomId());
			LineChartBox.setLayoutParams(LP);
			CardView.addView(LineChartBox);
		}
		{
			LayoutParams LP = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			chart = new LineChartView(getContext());
			chart.setBackgroundColor(Color.WHITE);
			chart.setPadding(WH.getH(2), WH.getH(2), WH.getH(2), WH.getH(2));
			chart.setInteractive(true);
			chart.setId(getRandomId());
			chart.setLayoutParams(LP);
			LineChartBox.addView(chart);
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
	
	public RelativeLayout getLineChartBox(){
		return LineChartBox;
	}

	
	public LineChartView getLineChartView(){
		return chart;
	}

 }
