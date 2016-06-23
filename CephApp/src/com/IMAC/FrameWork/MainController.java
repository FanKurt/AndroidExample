package com.IMAC.FrameWork;


import android.content.Context;
import android.view.View;

public abstract class MainController {
	protected MainFragment frag;
	protected Context context;

	public MainController(MainFragment frag) {
		this.frag = frag;
		this.context = frag.getActivity();
		init();
	}

	protected abstract void init();

	public boolean onBack() {
		return true;
	}

	public MainFragment findFragByTag(FTag tag) {
		return (MainFragment) frag.getFragmentManager().findFragmentByTag(tag.toString());
	}

	public void replace(int containerViewId, MainFragment fragment, FTag tag) {
		frag.getFragmentManager().beginTransaction()
				.replace(containerViewId, fragment, tag.toString()).commit();
	}

	public void add(int containerViewId, MainFragment fragment, FTag tag) {
		frag.getFragmentManager().beginTransaction()
				.add(containerViewId, fragment, tag.toString()).commit();
	}

	public void remove(MainFragment fragment) {
		frag.getFragmentManager().beginTransaction().remove(fragment).commit();
	}

	public View getFrameType(){
		return ((IMACActivity)frag.getActivity()).getFrameType();
	}
	
	public void onDestroyController() {
	}
}
