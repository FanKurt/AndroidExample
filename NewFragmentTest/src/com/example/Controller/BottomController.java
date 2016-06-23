package com.example.Controller;

import android.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.Fragment.BottomLayerFragment;
import com.example.Fragment.Second_BottomLayerFragment;
import com.example.Fragment.TopLayerFragment;
import com.example.IMAC.MainController;
import com.example.Layout.BottomLayout;
import com.example.Layout.TopLayerLayout;

public class BottomController extends MainController  {
	private BottomLayout v;
	public BottomController(Fragment frag) {
		super(frag);
	}
	protected void init() {
		this.v = (BottomLayout)frag.getView();
		v.getButton().setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				TopLayerFragment TF =(TopLayerFragment)((BottomLayerFragment)frag).getA().getFragmentTag("TOP");
				TF.getV().getTitle().setText("¥D­¶");
				Second_BottomLayerFragment  SB = new Second_BottomLayerFragment();
				frag.getFragmentManager().beginTransaction()
					.replace(((BottomLayerFragment)frag).getA().getMainFrameLayout().getFrame02().getId()
							, SB, "Bottom").commit();
			}
		});
	}

}
