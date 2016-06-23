package com.example.Activity;

import com.example.Fragment.BottomLayerFragment;
import com.example.Fragment.TopLayerFragment;
import com.example.Layout.MainFrameLayout;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class MainActivity extends Activity {
	private MainFrameLayout Layout;
	public static boolean isSecond = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Layout =new MainFrameLayout(this));
        getFragmentManager().beginTransaction()
     	  .add(Layout.getFrame01().getId(), new TopLayerFragment(), "TOP").commit();
        
        getFragmentManager().beginTransaction()
   	  	.add(Layout.getFrame02().getId(), new BottomLayerFragment(), "Bottom").commit();
    }
    public MainFrameLayout getMainFrameLayout(){
    	return Layout;
    }
    
    public Fragment getFragmentTag(String tag){
    	return getFragmentManager().findFragmentByTag(tag);
    }
}
