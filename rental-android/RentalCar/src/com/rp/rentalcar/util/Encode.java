package com.rp.rentalcar.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encode {

	
	public static String encode(String msg){
		String encodedString="";
		try {
			encodedString=URLEncoder.encode(msg, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			 
		}
		
		return encodedString;
	}
}
