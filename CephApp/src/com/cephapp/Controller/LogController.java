package com.cephapp.Controller;

import java.util.ArrayList;

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
import com.cephapp.ItemView.LogItem;
import com.cephapp.Layout.FrameType;
import com.cephapp.Layout.LogLayout;
import com.cephapp.Module.CustomAnimation;

public class LogController extends MainController {
	private LogLayout mtLayout;
	private ArrayList<View >OSDList;
	private CustomAnimation mAnimation;
	private OSDDialogView mDialogView;
	private AnimationSet mAnimationSet;
	public LogController(MainFragment frag) {
		super(frag);
	}

	protected void init() {
		mtLayout = (LogLayout)frag.getView();
		mDialogView = new OSDDialogView(context);
		mAnimationSet = new AnimationSet(false);
		mAnimation = new CustomAnimation(context);
		OSDList = new ArrayList<View>();
		((FrameType)getFrameType()).getTitle().setText("Log");
		LogList();
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

	private void LogList() {
		for(int i=0 ; i<5 ; i++){
			mAnimation.setAnimationTranslate(0, 0, 100, 0, 500, 0, true,0);
			mAnimationSet.addAnimation(mAnimation.getTranslate());
			LogItem mItemView =new LogItem(context);
			mItemView.startAnimation(mAnimationSet);
			OSDList.add(mItemView);
		
		}
	
		mtLayout.getListView().setAdapter(new ListViewAdapter(OSDList));
	}

	public void onDestroyController() {
		super.onDestroyController();
	}
	public boolean onBack() {
		if(mDialogView.getTag().toString().equals("show")){
			mDialogView.Dismiss();
			mDialogView.setTag("dismiss");
		}else{
			replace(((FrameType)getFrameType()).getBottomFrame().getId(),new OverallFragment(),
					FTag.FRAGMENT_BOTTOMFRAGMENT);
		}
	
		return false;
	}
	
}
