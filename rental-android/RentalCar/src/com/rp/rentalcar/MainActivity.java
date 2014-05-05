package com.rp.rentalcar;

import android.os.Bundle;

import com.rp.rentalcar.activity.template.EventType;
import com.rp.rentalcar.activity.template.TemplateActivity;
import com.rp.rentalcar.entity.User;
import com.rp.rentalcar.http.RequestParams.HttpMethond;
import com.rp.rentalcar.http.Response;
import com.rp.rentalcar.http.URLParams;
import com.rp.rentalcar.util.ToastUtils;

public class MainActivity extends TemplateActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ToastUtils.show(this, "∆Ù∂Ø¡À");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		URLParams param = new URLParams("user");
		User userInfo = new User();

		MainActivity.this.sendEvent(this, EventType.ACTION_REGISTER,
				param.toString(), userInfo, User.class, HttpMethond.HTTP_POST);

	}

	@Override
	public void onEvent(Response result) {
		// TODO Auto-generated method stub

	}

}
