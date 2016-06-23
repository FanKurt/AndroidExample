package com.cephapp.Controller;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.IMAC.FrameWork.FTag;
import com.IMAC.FrameWork.MainController;
import com.IMAC.FrameWork.MainFragment;
import com.cephapp.Adapter.ListViewAdapter;
import com.cephapp.DialogView.OSDDialogView;
import com.cephapp.DialogView.OSDDialogView.OnReportListener;
import com.cephapp.Fragment.OverallFragment;
import com.cephapp.ItemView.EfficacyItemView;
import com.cephapp.ItemView.MON_POOLItemView;
import com.cephapp.Layout.EfficacyLayout;
import com.cephapp.Layout.FrameType;
import com.cephapp.Module.CustomAnimation;

public class EfficacyController extends MainController {
	private EfficacyLayout mtLayout;
	private ArrayList<View >EfficacyList;
	private CustomAnimation mAnimation;
	private OSDDialogView mDialogView;
	private AnimationSet mAnimationSet;
	public EfficacyController(MainFragment frag) {
		super(frag);
	}

	protected void init() {
		mtLayout = (EfficacyLayout)frag.getView();
		mDialogView = new OSDDialogView(context);
		mAnimationSet = new AnimationSet(false);
		mAnimation = new CustomAnimation(context);
		EfficacyList = new ArrayList<View>();
		((FrameType)getFrameType()).getTitle().setText("叢集效能分析");
		EfficacyList();
		setOnItemListener();
	}

	private void setOnItemListener() {
		mDialogView.setOnReportListener(new OnReportListener() {
			public void onClick(OSDDialogView mOSDDialogView, View v) {
				mAnimation.setAnimationAlpha(1, 0, 500, 0, true, 0);
				mDialogView.startAnimation(mAnimation.getAlpha());
				mDialogView.setTag("dismiss");	 		
				mOSDDialogView.Dismiss();
			}
		});

		mtLayout.getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mAnimation.setAnimationAlpha(0, 1, 500, 0, true, 0);
				mDialogView.startAnimation(mAnimation.getAlpha());
				mDialogView.setTag("show");
				mDialogView.Show();
			}
		});
	}

	private void EfficacyList() {
		for(int i=0 ; i<5 ; i++){
			EfficacyItemView mItemView =new EfficacyItemView(context);
			generateData(mItemView.getLineChartView());
			mAnimation.setAnimationTranslate(0, 0, 100, 0, 500, 0, true,0);
			mAnimationSet.addAnimation(mAnimation.getTranslate());
			mItemView.startAnimation(mAnimationSet);
			EfficacyList.add(mItemView);
		}
	
		mtLayout.getListView().setAdapter(new ListViewAdapter(EfficacyList));
	}
	
	private void generateData(LineChartView lineChartView){


        List<Line> lines = new ArrayList<Line>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0; i < 80; ++i) {
            values.add(new PointValue(i, i));
        }

        Line line = new Line(values);
        line.setColor(Color.rgb(102, 165, 202));
        line.setHasPoints(false);
        line.setFilled(true);
        line.setStrokeWidth(1);
        lines.add(line);


    	LineChartData data = new LineChartData(lines);

        List<AxisValue> xAxisValue = new ArrayList<AxisValue>();
        String [] xArray = {"0","20","40","80"};
        for (int i = 0; i < xArray.length; i++) {
       	 xAxisValue.add(new AxisValue(i).setLabel(xArray[i]));
        }
        Axis xAxis = new Axis(xAxisValue).setHasLines(true).setMaxLabelChars(4)
                .setTextColor(Color.rgb(130, 130, 130));
        data.setAxisXBottom(xAxis);

      
        List<AxisValue> yAxisValue = new ArrayList<AxisValue>();
        String [] yArray = {"0","10","20","30"};
        for (int i = 0; i < yArray.length; i++) {
            yAxisValue.add(new AxisValue(i).setLabel(yArray[i]));
        }
        Axis yAxis = new Axis(yAxisValue).setHasLines(true).setMaxLabelChars(4)
                .setTextColor(Color.rgb(130, 130, 130));
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        
        Viewport mViewport = lineChartView.getMaximumViewport();
        mViewport.set(0, yAxisValue.size(), xAxisValue.size(), 0);
        lineChartView.setMaximumViewport(mViewport);
        lineChartView.setCurrentViewport(mViewport);
    
	}

	public void onDestroyController() {
		super.onDestroyController();
	}
	public boolean onBack() {
		if(mDialogView.getTag().toString().equals("show")){
			mDialogView.Dismiss();
			mDialogView.setTag("dismiss");
		}if(((FrameType)getFrameType()).getDrawerLayout().isDrawerOpen(Gravity.RIGHT)){
			((FrameType)getFrameType()).getDrawerLayout().closeDrawer(Gravity.RIGHT);
		}else{
			replace(((FrameType)getFrameType()).getBottomFrame().getId(),new OverallFragment(),
					FTag.FRAGMENT_BOTTOMFRAGMENT);
		}
	
		return false;
	}
	
}
