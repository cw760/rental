package com.rp.rentalcar.activity.template;
 
import java.util.Hashtable;
 

public final class EventType {
 
 private static final Hashtable<Integer,String> actionsMapping=new Hashtable<Integer,String>();
 
 public static String getActionObjectByKey(int key){
	
	return actionsMapping.get(key);
	
}
 
 

//////////////////////////////////////////////////////
public static final int ACTION_LOGIN=1;
public static final int ACTION_REGISTER=2;
public static final int PASSWORD_CHANGE=3;
public static final int ORDER_LIST=4;
public static final int GPS=5;
public static final int FEEDBACK=6;
public static final int UPDATE=7;
public static final int DOWNLOAD=8;
/*
 * 
static{
	 actionsMapping.put(ACTION_LOGIN, LoginAction.class.getName());
	 actionsMapping.put(ACTION_REGISTER, LoginAction.class.getName());

}
 */
}


 