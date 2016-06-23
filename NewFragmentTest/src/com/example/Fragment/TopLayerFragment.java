package com.example.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Controller.TopLayerController;
import com.example.IMAC.MainController;
import com.example.IMAC.MainFragment;
import com.example.Layout.TopLayerLayout;

public class TopLayerFragment extends MainFragment {
	private TopLayerLayout v;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return v = new TopLayerLayout(getActivity());
	}

	public TopLayerLayout getV() {
		return v;
	}
	protected MainController onControllerCreated() {
		return 	new TopLayerController(this);
	}
}
