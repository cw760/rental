package com.rp.rentalcar.http;

import java.util.Enumeration;
import java.util.Hashtable;

import com.rp.rentalcar.util.Encode;

public class URLParams {

	private Hashtable<String, String> hashtable = new Hashtable<String, String>();

	private static final String url = "http://192.168.1.66/1hai/orderService/";

	private String path = "";

	public void addParam(String key, String value) {
		value = Encode.encode(value);
		hashtable.put(key, value);
	}

	public URLParams(String path) {

		this.path = url + path;
		// + "?channelID=DBC647308C99664DD7720041CBDB96C3"
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.path);
		Enumeration<String> keys = hashtable.keys();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = hashtable.get(key);
			sb.append("&").append(key).append("=").append(value);
		}

		return sb.toString();
	}

}
