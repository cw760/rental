package com.rp.rentalcar.activity.template;

import com.rp.rentalcar.http.Response;


public interface IEvent {

	/**
	 * EventBus�ص�����
	 * 
	 * @param result
	 *            �������󷵻ص���Ϣ
	 */
	public abstract void onEvent(Response result);

}
