package com.aroc.study;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.aroc.system.SMS;
import com.aroc.system.SMSInfo;

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
import android.os.StatFs;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Study extends Activity implements OnClickListener {

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
	 * ������Ϣ
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
	 * ����绰
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
	 * �����ļ�
	 */
	public void saveCache(String text) throws Exception {
		// google��data/dataĿ¼��ר�Ű��հ���������һ������Ŀ¼�����ڴ洢�ļ�����������data/app�洢�ļ�
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
			// sd����mnt/sdcardĿ¼�� Ҳ����ֱ�Ӵ�sdcard���Ƕ�mnt/sdcard�����ã�
			// ��ҪдȨ����Ϣ��������ѡ������ѡ��������ö�sd��ҪȨ�ޣ�
			String filepath = Environment.getExternalStorageDirectory()
					.getPath();
			if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
				// ��SD��
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void useContentProvider() {
		ContentResolver cResolver = getContentResolver();
		Uri uri = Uri.parse("content://com.aroc.datasource/insert");
		ContentValues values=new ContentValues();
		values.put("test", "testValue");
		cResolver.insert(uri, values);
	}

	/**
	 * ���ݶ���
	 */
	public void BackupSMS() {
		List<SMSInfo> lstSMS = SMS.getMessage(getContentResolver());
	}
}