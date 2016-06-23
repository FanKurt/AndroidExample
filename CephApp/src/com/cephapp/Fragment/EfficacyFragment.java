package com.cephapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.IMAC.FrameWork.MainController;
import com.IMAC.FrameWork.MainFragment;
import com.cephapp.Controller.EfficacyController;
import com.cephapp.Layout.EfficacyLayout;

public class EfficacyFragment extends MainFragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new EfficacyLayout(getActivity());
	}

	protected MainController onControllerCreated() {
		return new EfficacyController(this);
		
	}
}
