package com.rp.rentalcar.activity.template;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.rp.rentalcar.http.HttpRestRequest;
import com.rp.rentalcar.http.RequestParams;
import com.rp.rentalcar.http.RequestParams.HttpMethond;
import com.rp.rentalcar.http.Response;
import com.rp.rentalcar.util.LogUtil;

import de.greenrobot.event.EventBus;

public abstract class TemplateActivity extends Activity implements IEvent {
	private ProgressDialog pd = null;

	protected static final EventBus sEventBus;
	static {
		sEventBus = EventBus.getDefault();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sEventBus.register(this);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		sEventBus.unregister(this);
	}

	/**
	 * 请求网络接口调用的方法
	 * 
	 * @param context
	 * @param type
	 * @param resourcePath
	 * @param obj
	 * @param responseClass
	 * @param httpMethond
	 */
	protected final <T> void sendEvent(Context context, int type,
			String resourcePath, T obj, Class<?> responseClass,
			HttpMethond httpMethond) {

		pd = ProgressDialog.show(context, null, "正在连接,请稍后...");

		RequestParams requestObj = new RequestParams(type, resourcePath, obj,
				responseClass, httpMethond);
		requestObj.setContext(context);

		final RequestAsyncTask asyncRequest = new RequestAsyncTask();

		asyncRequest.execute(requestObj);

		pd.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {

				if (keyCode == KeyEvent.KEYCODE_BACK
						&& event.getAction() == KeyEvent.ACTION_DOWN) {

					asyncRequest.cancel(false);
					dialog.dismiss();
				}
				return false;
			}
		});
	}

	private class RequestAsyncTask extends
			AsyncTask<RequestParams, Integer, Response> {

		@Override
		protected void onPostExecute(Response result) {

			if (pd != null) {
				pd.dismiss();
				pd = null;
			}
			if (result == null)
				return;

			if (result.getStatusCode() != 200) {
				Toast.makeText(TemplateActivity.this, result.getErrorMsg(),
						Toast.LENGTH_LONG).show();
				return;
			} else {
				sEventBus.post(result);
			}

		}

		@Override
		protected Response doInBackground(RequestParams... params) {
			// TODO Auto-generated method stub

			Response response = new Response();
			RequestParams requestParams = params[0];
			try {

				HttpRestRequest request = new HttpRestRequest(
						requestParams.getResourcePath());

				switch (requestParams.getHttpMethond()) {
				case HTTP_GET:
					response = request.getForObject(Response.class);
					break;

				case HTTP_POST:
					response = request.postForObject(requestParams.getBody(),
							Response.class);
					break;

				case HTTP_PUT:
					response = request.putForObject(requestParams.getBody(),
							Response.class);
					break;

				case HTTP_DELETE:
					response = request.deleteForObject(requestParams.getBody(),
							Response.class);
					break;

				}
				response.setEventID(requestParams.getMsgType());

			} catch (Exception e) {

				response.setEventID(requestParams.getMsgType());
				response.setStatusCode(0);
				response.setErrorMsg("连接超时,请稍后再试!");
				LogUtil.addLog(e);

			}

			return response;

		}

	};

}
