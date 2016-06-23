package com.cephapp.Activity;

import android.os.Bundle;

import com.IMAC.FrameWork.FTag;
import com.IMAC.FrameWork.IMACActivity;
import com.cephapp.Fragment.OverallFragment;
import com.cephapp.Fragment.TopFragment;
import com.cephapp.Layout.FrameType;

public class FragmentActivity extends IMACActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new FrameType(this));

		add(((FrameType)getFrameType()).getTopFrame().getId(), new TopFragment(),
						FTag.FRAGMENT_TOPFRAGMENT);
		
		add(((FrameType)getFrameType()).getBottomFrame().getId(), new OverallFragment(),
				FTag.FRAGMENT_BOTTOMFRAGMENT);
	}

}
