package com.IMAC.FrameWork;

import android.app.Fragment;
import android.os.Bundle;

public abstract class MainFragment extends Fragment {
	protected MainController MC;

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		MC = onControllerCreated();
	}

	protected abstract MainController onControllerCreated();

	public MainController getController() {
		return MC;
	}

	public void onDestroyController() {
		if (MC != null)
			MC.onDestroyController();
	}

	public void onDestroyView() {
		onDestroyController();
		super.onDestroyView();
	}
}
