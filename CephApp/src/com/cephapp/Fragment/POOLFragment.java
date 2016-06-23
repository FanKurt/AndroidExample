package com.cephapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.IMAC.FrameWork.MainController;
import com.IMAC.FrameWork.MainFragment;
import com.cephapp.Controller.POOLController;
import com.cephapp.Layout.POOLLayout;

public class POOLFragment extends MainFragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new POOLLayout(getActivity());
	}

	protected MainController onControllerCreated() {
		return new POOLController(this);
		
	}
}
