package com.cephapp.Controller;

import java.util.ArrayList;

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
import com.cephapp.ItemView.MON_POOLItemView;
import com.cephapp.ItemView.POOLParameterItemView;
import com.cephapp.Layout.FrameType;
import com.cephapp.Layout.POOLParameterLayout;
import com.cephapp.Module.CustomAnimation;

public class POOLParameterController extends MainController {
	private POOLParameterLayout mtLayout;
	private ArrayList<View >POOLParameterList;
	private CustomAnimation mAnimation;
	private OSDDialogView mDialogView;
	private AnimationSet mAnimationSet;
	public POOLParameterController(MainFragment frag) {
		super(frag);
	}

	protected void init() {
		mtLayout = (POOLParameterLayout)frag.getView();
		mDialogView = new OSDDialogView(context);
		mAnimationSet = new AnimationSet(false);
		mAnimation = new CustomAnimation(context);
		POOLParameterList = new ArrayList<View>();
		((FrameType)getFrameType()).getTitle().setText("叢集POOL參數");
		MONList();
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

	private void MONList() {
		for(int i=0 ; i<5 ; i++){
			POOLParameterItemView mItemView =new POOLParameterItemView(context);
			mAnimation.setAnimationTranslate(0, 0, 100, 0, 500, 0, true,0);
			mAnimationSet.addAnimation(mAnimation.getTranslate());
			mItemView.startAnimation(mAnimationSet);
			POOLParameterList.add(mItemView);
		}
	
		mtLayout.getListView().setAdapter(new ListViewAdapter(POOLParameterList));
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
