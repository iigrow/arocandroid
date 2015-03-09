package com.aroc.study;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.aroc.system.SMS;
import com.aroc.system.SMSInfo;

import android.R.anim;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Study extends Activity implements OnClickListener {

	private static final int SHOW_MSGBOX = 1;
	/*
	 * 子线程利用handler发送一条消息，消息被放在主线程的消息队列里面
	 * 主线程里面有一个looper消息的轮询器
	 * 如果轮询器发现了新的消息，调用handlemessage的方法，处理消息
	 * 
	 * */
	private Handler _handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == SHOW_MSGBOX) {
				Toast.makeText(Study.this, msg.obj.toString(), 0).show();
			}
		}
	};

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
	 * 
	 */
	public void sendMessage() {

		EditText telnumber = (EditText) findViewById(R.id.etTelnumber);
		EditText message = (EditText) findViewById(R.id.etMessage);
		try {
			SMS.sendMessage(telnumber.getText().toString(), message.getText()
					.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 拨打电话
	 */
	public void callNumber() {
		EditText txtTelNumber = (EditText) this.findViewById(R.id.etTelnumber);
		String telNumber = txtTelNumber.getText().toString();
		if (TextUtils.isEmpty(telNumber)) {
			Toast.makeText(Study.this, "Not empaty telnumber",
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
			String filepath = Environment.getExternalStorageDirectory()
					.getPath();
			if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
				// 有SD卡
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void useContentProvider() {
		ContentResolver cResolver = getContentResolver();
		Uri uri = Uri.parse("content://com.aroc.datasource/insert");
		ContentValues values = new ContentValues();
		values.put("test", "testValue");
		cResolver.insert(uri, values);
	}

	/**
	 * 备份短信
	 */
	public void BackupSMS() {
		List<SMSInfo> lstSMS = SMS.getMessage(getContentResolver());
	}

	public void ListenSMS() {
		ContentResolver resolver = getContentResolver();
		// resolver.notify();//发出消息
		// resolver.notifyChange(uri, observer)
		Uri uri = Uri.parse("content://sms");
		resolver.registerContentObserver(uri, true, new SMSContentObserver(
				new Handler()));
	}

	public void AsyncShowToast() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = SHOW_MSGBOX;
				msg.obj = "test";
				_handler.sendMessage(msg);
			}
		};
		thread.start();
	}
}
