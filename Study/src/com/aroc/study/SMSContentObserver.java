package com.aroc.study;

import android.database.ContentObserver;
import android.os.Handler;

public class SMSContentObserver extends ContentObserver {

	public SMSContentObserver(Handler handler) {
		super(handler);
	}
	
	@Override
	public void onChange(boolean selfChange){
		super.onChange(selfChange);
//		Toast.makeText(context, text, duration)
	}
}
