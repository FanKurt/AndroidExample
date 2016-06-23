package com.cephapp.Controller;

import java.util.ArrayList;

import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.IMAC.FrameWork.FTag;
import com.IMAC.FrameWork.MainController;
import com.IMAC.FrameWork.MainFragment;
import com.cephapp.Activity.FragmentActivity;
import com.cephapp.Adapter.ListViewAdapter;
import com.cephapp.Fragment.EfficacyFragment;
import com.cephapp.Fragment.LogFragment;
import com.cephapp.Fragment.MONFragment;
import com.cephapp.Fragment.OSDFragment;
import com.cephapp.Fragment.OverallFragment;
import com.cephapp.Fragment.POOLFragment;
import com.cephapp.Fragment.POOLParameterFragment;
import com.cephapp.ItemView.EfficacyItemView;
import com.cephapp.ItemView.SliderItemView01;
import com.cephapp.ItemView.SliderItemView02;
import com.cephapp.ItemView.SliderItemView03;
import com.cephapp.Layout.FrameType;
import com.example.cephapp.R;

public class TopController extends MainController {
	private FrameType mtLayout;
	private int[] slider_title ;
	private String [] slider;
	private int slider_title_count;
	private ArrayList<View >sliderArrayList;
	public TopController(MainFragment frag) {
		super(frag);
	}

	protected void init() {
		mtLayout = (FrameType)getFrameType();
		slider_title_count = 0;
		sliderArrayList = new ArrayList<View>();
		slider = context.getResources().getStringArray(R.array.slider);
	    int[] slider_title = { R.drawable.cluser_monitor_128,
				R.drawable.cluser_managing_128, R.drawable.settings_128 };
	    this.slider_title = slider_title;
		setButton();
		SliderList();
		setOnItemListener();
	}

	private void setOnItemListener() {
		mtLayout.getDrawerListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position== 0 || position == 7 || position == 12) {
					return;
				}
				switch (position) {
				//Overall
				case 1:
					replace(((FrameType)getFrameType()).getBottomFrame().getId(),new OverallFragment(),
							FTag.FRAGMENT_BOTTOMFRAGMENT);
					break;
				//OSD
				case 2:
					replace(((FrameType)getFrameType()).getBottomFrame().getId(),new OSDFragment(),
							FTag.FRAGMENT_BOTTOMFRAGMENT);			
					break;
				//MON
				case 3:
					replace(((FrameType)getFrameType()).getBottomFrame().getId(),new MONFragment(),
							FTag.FRAGMENT_BOTTOMFRAGMENT);
					break;
				//POOLS
				case 4:
					replace(((FrameType)getFrameType()).getBottomFrame().getId(),new POOLFragment(),
							FTag.FRAGMENT_BOTTOMFRAGMENT);
					break;
				//MDS
				case 5:
									
					break;
				//叢集效能分析
				case 6:
					replace(((FrameType)getFrameType()).getBottomFrame().getId(),new EfficacyFragment(),
							FTag.FRAGMENT_BOTTOMFRAGMENT);			
					break;
				//PG參數
				case 8:
					
					break;
				//OSD參數
				case 9:
					
					break;
				//MON參數
				case 10:
					
					break;
				//POOLS參數
				case 11:
					replace(((FrameType)getFrameType()).getBottomFrame().getId(),new POOLParameterFragment(),
							FTag.FRAGMENT_BOTTOMFRAGMENT);				
					break;
				//Log
				case 13:
					replace(((FrameType)getFrameType()).getBottomFrame().getId(),new LogFragment(),
							FTag.FRAGMENT_BOTTOMFRAGMENT);
					break;
				//登出
				case 14:
					((FragmentActivity)context).finish();
					break;
				}
				mtLayout.getDrawerLayout().closeDrawer(Gravity.RIGHT);
			}
		});
	}

	private void setButton() {
		mtLayout.getSliderButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mtLayout.getDrawerLayout().openDrawer(
						Gravity.RIGHT);
			}
		});
	}
	

	private void SliderList() {
		for (int i = 0; i < slider.length; i++) {
			SliderItemView01 mItemView01;
			SliderItemView02 mItemView02;
			SliderItemView03 mItemView03;
			if (i == 0 || i == 7 || i == 12) {
				mItemView01 = new SliderItemView01(context);
				mItemView01.getTextView().setText(slider[i]);
				mItemView01.getIconView().setBackgroundResource(
						slider_title[slider_title_count++]);
				sliderArrayList.add(mItemView01);
			} else if (i == 6 || i == 11 || i == 14) {
				mItemView03 = new SliderItemView03(context);
				mItemView03.getTextView().setText(slider[i]);
				sliderArrayList.add(mItemView03);
			} else {
				mItemView02 = new SliderItemView02(context);
				mItemView02.getTextView().setText(slider[i]);
				sliderArrayList.add(mItemView02);
			}
		}

		mtLayout.getDrawerListView().setAdapter(
				new ListViewAdapter(sliderArrayList));
	}

	public void onDestroyController() {
		super.onDestroyController();
	}
	public boolean onBack() {
		return true;
	}
}
