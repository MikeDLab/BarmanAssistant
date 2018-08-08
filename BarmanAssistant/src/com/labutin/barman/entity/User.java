package com.labutin.barman.entity;

public class User {
	private int userId;
	private String userLogin;
	private String userName;
	private String userPassword;
	private String userEmail;
	private boolean isAvaible;
	private int userRole;
	public User(int userId, String userLogin, String userName, String userPassword, String userEmail,
			int userRole,boolean isAvaible) {
		super();
		this.userId = userId;
		this.userLogin = userLogin;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userRole = userRole;
		this.isAvaible = isAvaible;
	}	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		result = prime * result + userRole;
		return result;
	}
	
	public boolean isAvaible() {
		return isAvaible;
	}
	public void setAvaible(boolean isAvaible) {
		this.isAvaible = isAvaible;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userId != other.userId)
			return false;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		if (userRole != other.userRole)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userLogin=" + userLogin + ", userName=" + userName + ", userPassword="
				+ userPassword + ", userEmail=" + userEmail + ", userRole=" + userRole + "]";
	}
	
}
