package com.aroc.system;

import java.util.ArrayList;

import android.telephony.SmsManager;

public class SMS {
	/**
	 * ·¢ËÍ¶ÌÏûÏ¢
	 * @param telNumber
	 * @param msg
	 * @throws Exception
	 */
	public static void sendMessage(String telNumber, String msg)
			throws Exception {
		if (telNumber == null) {
			throw new Exception("The telNumber not null");
		}
		SmsManager smsManager = SmsManager.getDefault();
		if (msg.length() > 69) {
			ArrayList<String> lstMsg = smsManager.divideMessage(msg);
			for (String partMsg : lstMsg) {
				smsManager.sendTextMessage(telNumber, null, partMsg, null,
						null);
			}
		} else {
			smsManager.sendTextMessage(telNumber, null, msg, null, null);
		}
	}
}
