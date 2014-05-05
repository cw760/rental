package com.rp.rentalcar.http;

import android.content.Context;

public class RequestParams {
	private Context context;
	private int msgType;
	private Object body;
	private Class<?> responseClass;
	private String resourcePath;
	private HttpMethond httpMethond;

	public enum HttpMethond {
		HTTP_GET, HTTP_POST, HTTP_PUT, HTTP_DELETE
	}

	public RequestParams(int msgType, String resourcePath, Object body,
			Class<?> responseClass, HttpMethond httpMethond) {

		this.msgType = msgType;
		this.setResourcePath(resourcePath);
		this.setBody(body);
		this.responseClass = responseClass;
		this.httpMethond = httpMethond;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public Class<?> getResponseClass() {
		return responseClass;
	}

	public void setResponseClass(Class<?> responseClass) {
		this.responseClass = responseClass;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public HttpMethond getHttpMethond() {
		return httpMethond;
	}

	public void setHttpMethond(HttpMethond httpMethond) {
		this.httpMethond = httpMethond;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
