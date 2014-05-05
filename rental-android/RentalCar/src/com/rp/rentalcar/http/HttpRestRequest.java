package com.rp.rentalcar.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rp.rentalcar.util.MD5;

public class HttpRestRequest extends RestTemplate {

	private URI uri = null;

	private static final String channelKey = "A47D55816BD0616C09F387F8E209BB68";

	private static SimpleClientHttpRequestFactory init() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(30 * 1000);
		requestFactory.setReadTimeout(60 * 1000);
		return requestFactory;
	}

	public HttpRestRequest(String path) throws URISyntaxException {
		super(init());

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new GsonHttpMessageConverter());
		super.setMessageConverters(messageConverters);
		/*
		 * if(path!=null&&path.endsWith("?"))this.uri = new
		 * URI(url+path+"channelID="+channelID); else this.uri = new
		 * URI(url+path+"&channelID="+channelID);
		 */
		this.uri = new URI(path);
	}

	private void setHeaders(ClientHttpRequest request) throws IOException {
		if (request == null)
			return;
		HttpHeaders headers = request.getHeaders();
		headers.add("Content-Type", "application/json;charset=UTF-8");

		String contentMD5 = MD5.getMD5Str(channelKey
				+ request.getURI().getRawQuery());
		headers.add("Content-MD5", contentMD5);

	}

	public <T> T getForObject(Class<T> responseType)
			throws RestClientException, URISyntaxException {

		AcceptHeaderRequestCallback requestCallback = new AcceptHeaderRequestCallback(
				responseType);
		HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(
				responseType, getMessageConverters());
		return execute(this.uri, HttpMethod.GET, requestCallback,
				responseExtractor);

	}

	public <T> T postForObject(Object request, Class<T> responseType)
			throws RestClientException, URISyntaxException {

		HttpEntityRequestCallback requestCallback = new HttpEntityRequestCallback(
				request, responseType);
		HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(
				responseType, getMessageConverters());
		return execute(this.uri, HttpMethod.POST, requestCallback,
				responseExtractor);

	}

	public <T> T putForObject(Object request, Class<T> responseType)
			throws RestClientException, URISyntaxException {

		HttpEntityRequestCallback requestCallback = new HttpEntityRequestCallback(
				request, responseType);
		HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(
				responseType, getMessageConverters());
		return execute(this.uri, HttpMethod.PUT, requestCallback,
				responseExtractor);
	}

	public <T> T deleteForObject(Object request, Class<T> responseType)
			throws RestClientException, URISyntaxException {

		HttpEntityRequestCallback requestCallback = new HttpEntityRequestCallback(
				request, responseType);
		HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<T>(
				responseType, getMessageConverters());
		return execute(this.uri, HttpMethod.DELETE, requestCallback,
				responseExtractor);
	}

	/**
	 * Request callback implementation that prepares the request's accept
	 * headers.
	 */
	private class AcceptHeaderRequestCallback implements RequestCallback {

		private final Class<?> responseType;

		private AcceptHeaderRequestCallback(Class<?> responseType) {
			this.responseType = responseType;
		}

		public void doWithRequest(ClientHttpRequest request) throws IOException {

			setHeaders(request);

			if (responseType != null) {

				List<MediaType> allSupportedMediaTypes = new ArrayList<MediaType>();
				for (HttpMessageConverter<?> messageConverter : getMessageConverters()) {
					if (messageConverter.canRead(responseType,
							MediaType.APPLICATION_JSON)) {
						List<MediaType> supportedMediaTypes = messageConverter
								.getSupportedMediaTypes();
						for (MediaType supportedMediaType : supportedMediaTypes) {
							if (supportedMediaType.getCharSet() != null) {
								supportedMediaType = new MediaType(
										supportedMediaType.getType(),
										supportedMediaType.getSubtype());
							}
							allSupportedMediaTypes.add(supportedMediaType);
						}
					}
				}
				if (!allSupportedMediaTypes.isEmpty()) {
					MediaType.sortBySpecificity(allSupportedMediaTypes);
					/*
					 * if (Log.isLoggable(TAG, Log.DEBUG)) { Log.d(TAG,
					 * "Setting request Accept header to " +
					 * allSupportedMediaTypes); }
					 */
					request.getHeaders().setAccept(allSupportedMediaTypes);
				}
			}
		}
	}

	/**
	 * Request callback implementation that writes the given object to the
	 * request stream.
	 */
	private class HttpEntityRequestCallback extends AcceptHeaderRequestCallback {

		private final HttpEntity<Object> requestEntity;

		private HttpEntityRequestCallback(Object requestBody) {
			this(requestBody, null);
		}

		@SuppressWarnings("unchecked")
		private HttpEntityRequestCallback(Object requestBody,
				Class<?> responseType) {
			super(responseType);
			if (requestBody instanceof HttpEntity) {
				this.requestEntity = (HttpEntity<Object>) requestBody;
			} else if (requestBody != null) {
				this.requestEntity = new HttpEntity<Object>(requestBody);
			} else {
				this.requestEntity = HttpEntity.EMPTY;
			}
		}

		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void doWithRequest(ClientHttpRequest httpRequest)
				throws IOException {
			super.doWithRequest(httpRequest);
			if (!requestEntity.hasBody()) {
				HttpHeaders httpHeaders = httpRequest.getHeaders();
				HttpHeaders requestHeaders = requestEntity.getHeaders();
				if (!requestHeaders.isEmpty()) {
					httpHeaders.putAll(requestHeaders);
				}
				if (httpHeaders.getContentLength() == -1) {
					httpHeaders.setContentLength(0L);
				}
			} else {
				Object requestBody = requestEntity.getBody();
				Class<?> requestType = requestBody.getClass();
				HttpHeaders requestHeaders = requestEntity.getHeaders();
				MediaType requestContentType = requestHeaders.getContentType();
				for (HttpMessageConverter messageConverter : getMessageConverters()) {
					if (messageConverter.canWrite(requestType,
							requestContentType)) {
						if (!requestHeaders.isEmpty()) {
							httpRequest.getHeaders().putAll(requestHeaders);
						}
						/*
						 * if (Log.isLoggable(TAG, Log.DEBUG)) { if
						 * (requestContentType != null) { Log.d(TAG, "Writing ["
						 * + requestBody + "] as \"" + requestContentType +
						 * "\" using [" + messageConverter + "]"); } else {
						 * Log.d(TAG, "Writing [" + requestBody + "] using [" +
						 * messageConverter + "]"); }
						 * 
						 * }
						 */

						GsonBuilder gsonBuilder = new GsonBuilder();
						Gson gson = gsonBuilder.disableHtmlEscaping().create();
						String body = gson.toJson(requestBody);

						String contentMD5 = MD5.getMD5Str(channelKey
								+ httpRequest.getURI().getRawQuery() + body);
						httpRequest.getHeaders().set("Content-MD5", contentMD5);

						messageConverter.write(requestBody, requestContentType,
								httpRequest);
						return;
					}
				}
				String message = "Could not write request: no suitable HttpMessageConverter found for request type ["
						+ requestType.getName() + "]";
				if (requestContentType != null) {
					message += " and content type [" + requestContentType + "]";
				}
				throw new RestClientException(message);
			}
		}
	}

}
