package com.aroc.study;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class SendMessage extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendmessage);
		// Button btnSend = (Button)findViewById(R.id.btnSend);
		// btnSend.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// sendMessage();
		// }
		// });
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSend: {
			sendMessage();
		}
			break;
		default:
			break;
		}

	}

	/**
	 * 发送消息
	 */
	public void sendMessage() {

		EditText telnumber = (EditText) findViewById(R.id.etTelnumber);
		EditText message = (EditText) findViewById(R.id.etMessage);

		SmsManager sms = SmsManager.getDefault();
		ArrayList<String> lstMessage = sms.divideMessage(message.getText()
				.toString());
		for (String msg : lstMessage) {
			sms.sendTextMessage(telnumber.getText().toString(), null, msg,
					null, null);
		}
	}
	
	/**
	 * 拨打电话
	 */
	public void callNumber(){
		EditText txtTelNumber = (EditText) this.findViewById(R.id.etTelnumber);
		String telNumber = txtTelNumber.getText().toString();
		if (TextUtils.isEmpty(telNumber)) {
			Toast.makeText(SendMessage.this, "Not empaty telnumber",
					Toast.LENGTH_SHORT).show();
			return;
		}
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" + telNumber));
		startActivity(intent);		
	}
}
