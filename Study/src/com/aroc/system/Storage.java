package com.aroc.system;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Xml;

public class Storage {
	/**
	 * ��ȡSD��������
	 * 
	 * @return SD Card size
	 */
	public static long getSDSize() {
		long size = 0;
		File file = Environment.getExternalStorageDirectory();
		if (file != null) {
			StatFs statFs = new StatFs(file.getPath());
			size = statFs.getTotalBytes(); // statFs.getBlockCountLong()*statFs.getBlockSizeLong();
		}
		return size;
	}

	/**
	 * ��ȡSD�����ÿռ�
	 * 
	 * @return
	 */
	public static long getSDAvaliableSize() {
		long size = 0;
		File file = Environment.getExternalStorageDirectory();
		if (file != null) {
			StatFs statFs = new StatFs(file.getPath());
			size = statFs.getAvailableBytes();
		}
		return size;
	}

	/**
	 * ��ȡSD�����ɿռ��С
	 * 
	 * @return
	 */
	public static long getSDFreeSize() {
		long size = 0;
		File file = Environment.getExternalStorageDirectory();
		if (file != null) {
			StatFs statFs = new StatFs(file.getPath());
			size = statFs.getFreeBytes();
		}
		return size;
	}

	/**
	 * ����һ��
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void addConfigItem(Context context, String key, String value) {
		SharedPreferences spPreferences = context.getSharedPreferences(
				"Config", Context.MODE_PRIVATE);
		Editor editor = spPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * ����������
	 * 
	 * @param context
	 * @param map
	 */
	public static void addConfigItem(Context context, Map<String, String> map) {
		SharedPreferences spPreferences = context.getSharedPreferences(
				"Config", Context.MODE_PRIVATE);
		Editor editor = spPreferences.edit();
		for (String key : map.keySet()) {
			editor.putString(key, map.get(key));
		}
		editor.commit();
	}
}
