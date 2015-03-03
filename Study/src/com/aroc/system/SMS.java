package com.aroc.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.ListView;

public class SMS {
	/**
	 * 发送短消息
	 * 
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
				smsManager
						.sendTextMessage(telNumber, null, partMsg, null, null);
			}
		} else {
			smsManager.sendTextMessage(telNumber, null, msg, null, null);
		}
	}

	/**
	 * 获取短信内容
	 * @param resolver
	 * @return
	 */
	public static List<SMSInfo> getMessage(ContentResolver resolver) {
		List<SMSInfo> lstResult = new LinkedList<SMSInfo>();
		Uri uri = Uri.parse("content://sms/");
		Cursor cursor = resolver.query(uri, new String[] { "address", "date",
				"type", "body" }, null, null, null);
		SMSInfo info = null;
		while (cursor.moveToNext()) {
			info = new SMSInfo();
			info.setAddress(cursor.getString(0));
			info.setCurrentDate(new Date(cursor.getLong(1)));
			info.setInfoType(cursor.getInt(2));
			info.setBody(cursor.getString(3));
			lstResult.add(info);
		}
		return lstResult;
	}
}
