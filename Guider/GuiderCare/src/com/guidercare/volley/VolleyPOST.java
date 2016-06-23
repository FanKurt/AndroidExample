package com.guidercare.volley;

import java.util.Map;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.guidercare.Module.BeaconTag;

public class VolleyPOST {
	public interface OnResponse {
		public void response(VolleyPOST POSt, int state, String Data);
	}

	public static int REPONSE_STATE = 1, ERROR_STATE = -1;

	private Map<String, String> params;
	private StringRequest SR;
	private String url;
	private OnResponse OR;
	public VolleyPOST(Context context,Map<String, String> params, String url) {
		this.params = params;
		this.url = url;
	}

	public void Queue(OnResponse OR) {
		this.OR = OR;
		setStringRequest();
		BeaconTag.queue.add(SR);
	}

	public void close() {
		if(SR !=null)
			SR.cancel();
		BeaconTag.queue.getCache().clear();
		BeaconTag.queue.getCache().initialize();
		BeaconTag.queue.stop();
		BeaconTag.queue.start();
		SR = null;
		System.gc();
	}

	private void setStringRequest() {
		SR = new StringRequest(Request.Method.POST, url, listener, Error) {

			protected Map<String, String> getParams() throws AuthFailureError {
				if (params != null)
					return params;
				return super.getParams();
			}

		};
	}

	private Response.ErrorListener Error = new Response.ErrorListener() {
		public void onErrorResponse(VolleyError error) {
			if(OR !=null)
				OR.response(VolleyPOST.this, ERROR_STATE, error.toString());
		}
	};

	private Response.Listener<String> listener = new Listener<String>() {
		public void onResponse(String response) {
			if(OR !=null)
				OR.response(VolleyPOST.this, REPONSE_STATE, response);
		}
	};
}
