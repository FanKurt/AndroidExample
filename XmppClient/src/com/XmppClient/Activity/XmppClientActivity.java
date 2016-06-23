package com.XmppClient.Activity;

import org.jivesoftware.smack.XMPPConnection;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

import com.XmppClient.Controller.XmppClientController;
import com.XmppClient.Layout.XmppClientLayout;


public class XmppClientActivity extends Activity {
	private XmppClientLayout layout;
	private XMPPConnection connection;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(layout =new XmppClientLayout(this));
        new XmppClientController(this , layout);
        
    }


}
