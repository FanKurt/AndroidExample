package com.cephapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.IMAC.FrameWork.MainController;
import com.IMAC.FrameWork.MainFragment;
import com.cephapp.Controller.MONController;
import com.cephapp.Layout.MONLayout;

public class MONFragment extends MainFragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new MONLayout(getActivity());
	}

	protected MainController onControllerCreated() {
		return new MONController(this);
		
	}
}
