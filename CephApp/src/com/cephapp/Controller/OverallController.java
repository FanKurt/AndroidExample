package com.cephapp.Controller;

import java.util.ArrayList;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;

import com.IMAC.FrameWork.FTag;
import com.IMAC.FrameWork.MainController;
import com.IMAC.FrameWork.MainFragment;
import com.cephapp.Activity.FragmentActivity;
import com.cephapp.Adapter.ListViewAdapter;
import com.cephapp.DialogView.HealthDialogView;
import com.cephapp.DialogView.HealthDialogView.OnReportListener;
import com.cephapp.Fragment.MONFragment;
import com.cephapp.Fragment.OSDFragment;
import com.cephapp.Fragment.POOLFragment;
import com.cephapp.ItemView.MainCardItemView;
import com.cephapp.ItemView.SubCardItemView;
import com.cephapp.Layout.FrameType;
import com.cephapp.Layout.OverallLayout;
import com.cephapp.Module.CustomAnimation;
import com.example.cephapp.R;

public class OverallController extends MainController implements OnClickListener {
	
	private OverallLayout mLayout;
	private ArrayList<View> mArrayList ;
	private String[] introduce, message, state;
	private int[] state_view ;
	private int[] check ;
	
	private CustomAnimation mAnimation;
	private HealthDialogView mDialogView;
	private AnimationSet mAnimationSet;
	
	public OverallController(MainFragment frag) {
		super(frag);
	}
	
	protected void init() {
		mLayout = (OverallLayout) frag.getView();
		((FrameType)getFrameType()).getTitle().setText("Overall");
		newComponent();
		setArrayValue();
		CardList();
		
		mDialogView.setOnReportListener(new OnReportListener() {
			public void onClick(HealthDialogView mHealthDialogView, View v) {
					mAnimation.setAnimationAlpha(1, 0, 500, 0, true, 0);
					mDialogView.startAnimation(mAnimation.getAlpha());
					mHealthDialogView.Dismiss();
					mDialogView.setTag("dismiss");
			}
		});

	}

	


	private void CardList() {
		
		mAnimation.setAnimationAlpha(0, 1, 500, 0, true, 500);
		
		MainCardItemView mainCardItemView =new MainCardItemView(context) ;
		mainCardItemView.startAnimation(mAnimation.getAlpha());
		mArrayList.add(mainCardItemView);
		mainCardItemView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mAnimation.setAnimationAlpha(0, 1, 500, 0, true, 0);
				mDialogView.startAnimation(mAnimation.getAlpha());
				mDialogView.Show();
				mDialogView.setTag("show");
			}
		});
		
		
		for (int i = 0; i < state.length; i = i + 2) {
			
			mAnimation.setAnimationAlpha(0, 1, 800, 0, true, 500);
			mAnimation.setAnimationTranslate(0, 0, 100, 0, 300, 0, true, 800);
			mAnimationSet.addAnimation(mAnimation.getAlpha());
			mAnimationSet.addAnimation(mAnimation.getTranslate());
			
			SubCardItemView mCardItemView = new SubCardItemView(context);
			
			mCardItemView.startAnimation(mAnimationSet);
			
			mCardItemView.getCardView1().setOnClickListener(this);
			mCardItemView.getCardView2().setOnClickListener(this);

			mCardItemView.getCardView1().setTag("" + i);
			mCardItemView.getCardView2().setTag("" + (i + 1));

			mCardItemView.getStateL().setText(state[i]);
			mCardItemView.getStateL().setTag("" + i);
			mCardItemView.getStateR().setText(state[i + 1]);
			mCardItemView.getStateR().setTag("" + (i + 1));

			mCardItemView.getStateViewL().setBackgroundResource(state_view[i]);
			mCardItemView.getStateViewR().setBackgroundResource(state_view[i + 1]);

			mCardItemView.getCheckL().setBackgroundResource(check[i]);
			mCardItemView.getCheckR().setBackgroundResource(check[i + 1]);

			mCardItemView.getIntroduceL().setText(introduce[i]);
			mCardItemView.getIntroduceR().setText(introduce[i + 1]);

			mCardItemView.getMessageL().setText(message[i]);
			mCardItemView.getMessageR().setText(message[i + 1]);

			mArrayList.add(mCardItemView);
		}
		
		mLayout.getListView().setAdapter(new ListViewAdapter(mArrayList));
	}

	private void setArrayValue() {
		introduce = context.getResources().getStringArray(R.array.introduce);
		message = context.getResources().getStringArray(R.array.message);
		state = context.getResources().getStringArray(R.array.state);
		
		int[] state_view = { R.drawable.osd2_128, R.drawable.monitor_128,
					R.drawable.pool_128, R.drawable.pg_status_128,
					R.drawable.usage_128, R.drawable.host_128 };
		int[] check = { R.drawable.ok_128, R.drawable.ok_128,
				R.drawable.ok_128, R.drawable.warning_128, R.drawable.error_128,
				R.drawable.ok_128 };
	
		this.state_view = state_view;
		this.check = check;
	
		
	}
	
	private void newComponent() {
		
		mAnimation = new CustomAnimation(context);
		mArrayList = new ArrayList<View>();
		
		mDialogView = new HealthDialogView(context);
		
		mAnimationSet = new AnimationSet(false);
	}

	@Override
	public void onClick(View v) {
		int index = Integer.parseInt(v.getTag().toString());
		switch (index) {
		case 0:
			replace(((FrameType)getFrameType()).getBottomFrame().getId(),new OSDFragment(),
					FTag.FRAGMENT_BOTTOMFRAGMENT);
			break;
		case 1:
			replace(((FrameType)getFrameType()).getBottomFrame().getId(),new MONFragment(),
					FTag.FRAGMENT_BOTTOMFRAGMENT);
			break;
		case 2:
			replace(((FrameType)getFrameType()).getBottomFrame().getId(),new POOLFragment(),
					FTag.FRAGMENT_BOTTOMFRAGMENT);
			break;
		case 3:
			Log.e("", "PG");
			break;
		case 4:
			Log.e("", "USAGE");
			break;
		case 5:
			Log.e("", "HOSTS");
			break;

		}
	}

	public boolean onBack() {
		if(mDialogView.getTag().toString().equals("show")){
			mAnimation.setAnimationAlpha(1, 0, 500, 0, true, 0);
			mDialogView.startAnimation(mAnimation.getAlpha());
			mDialogView.Dismiss();
			mDialogView.setTag("dismiss");
		}else if(((FrameType)getFrameType()).getDrawerLayout().isDrawerOpen(Gravity.RIGHT)){
			((FrameType)getFrameType()).getDrawerLayout().closeDrawer(Gravity.RIGHT);
		}
		else{
			((FragmentActivity)context).finish();
		}
		
		return false;
	}

	
}
