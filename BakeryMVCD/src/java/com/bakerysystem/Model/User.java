package com.bakerysystem.Model;

public class User {
	
	private int userId;
	private String email;
	private String password;
	private String userType;
	
	
	public User(){}
        public User( String email, String password, String userType) {
		//this.userName = userName;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	public User(int userId, String email, String password, String userType) {
		this(email, password, userType);
                this.userId = userId;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId +  ", email=" + email + ", password=" + password
				+ ", userType=" + userType + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
