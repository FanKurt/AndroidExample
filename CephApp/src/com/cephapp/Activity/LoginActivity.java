package com.cephapp.Activity;

import android.os.Bundle;

import com.IMAC.FrameWork.IMACActivity;
import com.cephapp.Controller.LoginController;
import com.cephapp.Layout.LoginLayout;


public class LoginActivity extends IMACActivity {
	private LoginLayout mLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mLayout =new LoginLayout(this));
        new LoginController(this ,mLayout );
    }

}
