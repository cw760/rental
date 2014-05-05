package com.rp.rentalcar.activity.template;

import com.rp.rentalcar.http.Response;


public interface IEvent {

	public void handleEvent(Response response);

}
