package com.aroc.system;

import java.io.File;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.os.StatFs;

public class Storage {
	/**
	 * 获取SD卡总容量
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
	 * 获取SD卡可用空间
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
	 * 获取SD卡自由空间大小
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
	 * 保存一项
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
	 * 保存序列项
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
