package com.rp.rentalcar.activity.template;

import com.rp.rentalcar.http.Response;


public interface IEvent {

	/**
	 * EventBus回调函数
	 * 
	 * @param result
	 *            网络请求返回的信息
	 */
	public abstract void onEvent(Response result);

}
