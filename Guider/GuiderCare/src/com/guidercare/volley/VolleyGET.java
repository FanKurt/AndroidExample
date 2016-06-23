package com.guidercare.volley;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.guidercare.Module.BeaconTag;

public class VolleyGET {

	public interface OnResponse {
		public void response(VolleyGET POSt, int state, String Data);
	}

	public static int REPONSE_STATE = 1, ERROR_STATE = -1;
	private StringRequest SR;
	private String url;
	private OnResponse OR;
	private Context context;
	public VolleyGET(Context context, String url) {
		this.url = url;
		this.context = context;
	}

	public void Queue(OnResponse OR) {
		this.OR = OR;
		setStringRequest();
		SR.setRetryPolicy(new DefaultRetryPolicy(60000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		BeaconTag.queue.add(SR);
	}

	public void close() {
		
		if (SR != null)
			SR.cancel();
		BeaconTag.queue.getCache().clear();
		BeaconTag.queue.getCache().initialize();
		BeaconTag.queue.stop();
		BeaconTag.queue.start();
		SR = null;
	}

	private void setStringRequest() {
		if (SR != null) {
			SR.cancel();
			SR = null;
		}
		SR = new StringRequest(Request.Method.GET, url, listener, Error) {
		};
	}

	private Response.ErrorListener Error = new Response.ErrorListener() {
		public void onErrorResponse(VolleyError error) {
			if (OR != null)
				OR.response(VolleyGET.this, ERROR_STATE, error.toString());
		}
	};

	private Response.Listener<String> listener = new Listener<String>() {
		public void onResponse(String response) {
			if (OR != null){
				OR.response(VolleyGET.this, REPONSE_STATE, response);
			}

		}
	};
}
