package com.example.Controller;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.Fragment.BottomLayerFragment;
import com.example.Fragment.Second_BottomLayerFragment;
import com.example.Fragment.TopLayerFragment;
import com.example.IMAC.MainController;
import com.example.Layout.Second_BottomLayout;
import com.example.Layout.TopLayerLayout;

public class Second_BottomController extends MainController  {
	private Second_BottomLayout v;
	public Second_BottomController(Fragment frag) {
		super(frag);
	}
	protected void init() {
		this.v = (Second_BottomLayout)frag.getView();
		TopLayerFragment TF =(TopLayerFragment)((Second_BottomLayerFragment)frag).getA().getFragmentTag("TOP");
		TF.getV().getTitle().setText("¤l­¶­±");
		
		v.getButton().setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				final ProgressDialog PD = new ProgressDialog(context);
				PD.setCancelable(false);
				PD.setMessage("Loading...");
				PD.setProgress(0);
				PD.show();
				Handler mhandler = new Handler();
				mhandler.postDelayed(new Runnable() {
					public void run() {
						PD.dismiss();
					}
				}, 10000);
			}
		});
	}
	public boolean onBack() {
		BottomLayerFragment  SB = new BottomLayerFragment();
		frag.getFragmentManager().beginTransaction()
			.replace(
					((BottomLayerFragment)frag).getA().getMainFrameLayout().getFrame02().getId()
					, SB
					, "Bottom").commit();
		return true;
	
	}


}
