package com.guidercare.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class ReminderDialog extends AlertDialog.Builder {
	public interface OnDialogListener {
		public void onClick(int which);
	}

	private OnDialogListener mOnDialogListener;
	private String mtMessage ,mNegativeButton ,mPositiveButton , mTilte;

	public ReminderDialog(Context context) {
		super(context);
	}

	public void saveSetting() {
		this.setTitle(mTilte);
		this.setMessage(mtMessage);
		this.setPositiveButton(mPositiveButton,new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (mOnDialogListener != null) {
							mOnDialogListener.onClick(which);
						}
					}
				});
		this.setNegativeButton(mNegativeButton,new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (mOnDialogListener != null) {
							mOnDialogListener.onClick(which);
						}
					}
				});
	}

	public void setOnDialogListener(OnDialogListener mOnDialogListener) {
		this.mOnDialogListener = mOnDialogListener;
	}

	public void setDialogMessage(String message) {
		this.mtMessage = message;
	}

	public void setPositiveButton(String message) {
		this.mPositiveButton = message;
	}
	
	public void setNegativeButton(String message){
		this.mNegativeButton = message;
	}
	
	public void setDialogTitle(String message){
		this.mTilte = message;
	}
}
