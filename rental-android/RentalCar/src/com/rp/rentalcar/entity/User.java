package com.rp.rentalcar.entity;

public class User {

	private String userID;
	private String userName;
	private String password;
	private String userLevel;
	private int pswExpire;

	public User() {
		super();
	}

	public User(String userID, String userName, String password, String userLevel, int pswExpire) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.userLevel = userLevel;
		this.pswExpire = pswExpire;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPswExpire() {
		return pswExpire;
	}

	public void setPswExpire(int pswExpire) {
		this.pswExpire = pswExpire;
	}

	public boolean isValidUser() {

		if (this.userID == null || this.userID.trim().equals("")) {
			return false;
		}
		if (this.password == null || this.password.trim().equals("")) {
			return false;
		}
		if (this.userID.trim().length() != 11) {
			return false;
		}

		return true;
	}

	public boolean isValidUserID() {

		if (this.userID == null || this.userID.trim().equals("")
				|| this.userID.trim().length() != 11) {
			return false;
		}
		return true;
	}
}
