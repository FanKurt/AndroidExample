package com.example.Controller;

import android.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.Activity.MainActivity;
import com.example.Fragment.TopLayerFragment;
import com.example.IMAC.MainController;
import com.example.Layout.TopLayerLayout;

public class TopLayerController extends MainController  {
	private TopLayerLayout v;
	public TopLayerController(Fragment frag) {
		super(frag);
	}
	
	protected void init() {
		this.v = (TopLayerLayout)frag.getView();
		v.getTitle().setText("¥D­¶");
		v.getDrawer().setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if(MainActivity.isSecond){
					
				}else{
					System.exit(0);
				}
				
			}
		});
	}


}
