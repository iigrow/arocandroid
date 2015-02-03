package com.aroc.study;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
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
	public void callNumber() {
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

	/**
	 * 保存文件
	 */
	public void saveCache(String text) throws Exception {
		// google在data/data目录下专门按照包名建立了一个缓存目录，用于存储文件，不允许在data/app存储文件
		try {
			// Context context=(Context)this;
			// this.getFilesDir() //data/data/files
			// this.openFileInput(name);
			// this.openFileOutput(name, mode);
			File file = new File(this.getFilesDir(), "tmp.dat");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(text.getBytes());
		} catch (Exception e) {
			throw e;
		}
	}

	public void saveSDCard(String text) throws Exception {
		try {
			// sd卡在mnt/sdcard目录下 也可以直接存sdcard（是对mnt/sdcard的引用）
			// 需要写权限信息（开发者选项中有选项可以设置读sd卡要权限）
			String filepath = Environment.getExternalStorageDirectory().getPath();
			if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
				// 有SD卡
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getSDCardSize() {
		StatFs statFs = new StatFs(Environment.getExternalStorageDirectory()
				.getPath());
		
		return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
	}

}
