package com.IMAC.FrameWork;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;

public class IMACActivity extends Activity{
	private View FrameView;
	public void setContentView(View view) {
		super.setContentView(view);
		this.FrameView = view;
	}
	
	public MainFragment getFragmentByTag(FTag Tag) {
		return (MainFragment) getFragmentManager().findFragmentByTag(Tag.toString());
	}
	
	public View getFrameType() {
		return FrameView;
	}
	// 覆蓋
	public void replace(int containerViewId, MainFragment fragment, FTag tag) {
		getFragmentManager().beginTransaction()
				.replace(containerViewId, fragment, tag.toString()).commit();
	}

	// 新增
	public void add(int containerViewId, MainFragment fragment, FTag tag) {
		getFragmentManager().beginTransaction()
				.add(containerViewId, fragment, tag.toString()).commit();
	}

	// 移除
	public void remove(MainFragment fragment) {
		getFragmentManager().beginTransaction().remove(fragment).commit();
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean f =super.onKeyDown(keyCode, event);
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MainFragment MF;
			for(FTag FTagss:FTag.values())
				if(FTagss.isOnBack() && (MF = getFragmentByTag(FTagss))!=null){
					f = MF.getController().onBack();
					break;
				}
		}
		return f;
	}
}










