package com.aroc.system;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.text.TextUtils;
import android.util.Xml;

public class XMLExtension {
	/**
	 * 保存为XML
	 * 
	 * @param filePath
	 * @param lstObj
	 * @throws Exception
	 */
	public static void saveXML(String filePath, List<Object> lstObj)
			throws Exception {
		if (TextUtils.isEmpty(filePath)) {
			throw new Exception("File path must be not null");
		}
		XmlSerializer serializer = Xml.newSerializer();
		FileOutputStream os = new FileOutputStream(filePath);
		serializer.setOutput(os, "utf-8");
		serializer.startDocument("utf-8", true);
		// TODO:应该用反射去做
		serializer.startTag(null, "test");
		serializer.attribute(null, "id", "1");
		serializer.text("test");
		serializer.endTag(null, "test");
		serializer.endDocument();
		os.close();
	}

	public static void parseXML(InputStream is) throws Exception {

		XmlPullParser xmlParser = Xml.newPullParser();
		xmlParser.setInput(is, "utf-8");
		int type = xmlParser.getEventType(); 
		while (type != XmlPullParser.END_DOCUMENT) {
			//...
			type = xmlParser.next();
		}
	}
}
