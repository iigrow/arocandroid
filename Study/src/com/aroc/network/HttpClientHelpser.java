package com.aroc.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientHelpser {

	public static String commitWebData(String address, String data) {
		String result = "";
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", data.length() + "");
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(data.getBytes());
			int code = conn.getResponseCode();
			if (code == 200) {
				InputStream inputs = conn.getInputStream();
				ByteArrayOutputStream arrayos = new ByteArrayOutputStream();
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = inputs.read()) != -1) {
					arrayos.write(buffer, 0, len);
				}
				inputs.close();
				arrayos.close();
				result = new String(arrayos.toByteArray());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String ServiceData(String address) {
		String result="";
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet hget = new HttpGet(address);
			HttpResponse response = client.execute(hget);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				InputStream inputs = response.getEntity().getContent();
				ByteArrayOutputStream arrayos = new ByteArrayOutputStream();
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = inputs.read()) != -1) {
					arrayos.write(buffer, 0, len);
				}
				inputs.close();
				arrayos.close();
				result = new String(arrayos.toByteArray());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
