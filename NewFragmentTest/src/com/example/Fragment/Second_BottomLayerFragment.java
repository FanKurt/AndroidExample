package com.example.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Activity.MainActivity;
import com.example.Controller.Second_BottomController;
import com.example.IMAC.MainController;
import com.example.IMAC.MainFragment;
import com.example.Layout.Second_BottomLayout;

public class Second_BottomLayerFragment  extends MainFragment{
	private View v;
	private MainController MC;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new Second_BottomLayout(getActivity());
	}

	protected MainController onControllerCreated() {
		return new Second_BottomController(this);
	}
	public View getV() {
		return v;
	}
	public MainActivity getA() {
		return ((MainActivity) this.getActivity());
	}
	public MainController getMC() {
		return MC;
	}
}
