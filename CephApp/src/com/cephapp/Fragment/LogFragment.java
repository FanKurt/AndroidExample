package com.cephapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.IMAC.FrameWork.MainController;
import com.IMAC.FrameWork.MainFragment;
import com.cephapp.Controller.LogController;
import com.cephapp.Layout.LogLayout;

public class LogFragment extends MainFragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new LogLayout(getActivity());
	}

	protected MainController onControllerCreated() {
		return new LogController(this);
		
	}
}
