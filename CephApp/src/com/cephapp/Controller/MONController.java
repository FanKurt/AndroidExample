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
import com.cephapp.ItemView.BoxItem;
import com.cephapp.ItemView.MON_POOLItemView;
import com.cephapp.ItemView.OSDItemView;
import com.cephapp.Layout.FrameType;
import com.cephapp.Layout.MONLayout;
import com.cephapp.Module.CustomAnimation;

public class MONController extends MainController {
	private MONLayout mtLayout;
	private ArrayList<View >MONList;
	private CustomAnimation mAnimation;
	private OSDDialogView mDialogView;
	private AnimationSet mAnimationSet;
	public MONController(MainFragment frag) {
		super(frag);
	}

	protected void init() {
		mtLayout = (MONLayout)frag.getView();
		mDialogView = new OSDDialogView(context);
		mAnimationSet = new AnimationSet(false);
		mAnimation = new CustomAnimation(context);
		MONList = new ArrayList<View>();
		((FrameType)getFrameType()).getTitle().setText("叢集MON資訊");
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
			MON_POOLItemView mItemView =new MON_POOLItemView(context);
			mAnimation.setAnimationTranslate(0, 0, 100, 0, 500, 0, true,0);
			mAnimationSet.addAnimation(mAnimation.getTranslate());
			mItemView.startAnimation(mAnimationSet);
			MONList.add(mItemView);
		}
	
		mtLayout.getListView().setAdapter(new ListViewAdapter(MONList));
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
