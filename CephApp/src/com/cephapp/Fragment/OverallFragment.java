package com.cephapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.IMAC.FrameWork.MainController;
import com.IMAC.FrameWork.MainFragment;
import com.cephapp.Controller.OverallController;
import com.cephapp.Layout.OverallLayout;

public class OverallFragment extends MainFragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new OverallLayout(getActivity());
	}

	protected MainController onControllerCreated() {
		return new OverallController(this);
		
	}
}
