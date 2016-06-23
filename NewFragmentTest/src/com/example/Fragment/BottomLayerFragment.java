package com.example.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.Activity.MainActivity;
import com.example.Controller.BottomController;
import com.example.IMAC.MainController;
import com.example.IMAC.MainFragment;
import com.example.Layout.BottomLayout;

public class BottomLayerFragment extends MainFragment {
	private View v;
	private MainController MC;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new BottomLayout(getActivity());
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
	protected MainController onControllerCreated() {
		return new BottomController(this);
	}

}
