package com.labutin.barman.entity;

public class User {
	private int user_id;
	private String user_login;
	private String user_name;
	private String user_password;
	private String user_email;
	private int user_role;
	public User(int user_id, String user_login, String user_name, String user_password, String user_email,
			int user_role) {
		super();
		this.user_id = user_id;
		this.user_login = user_login;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_email = user_email;
		this.user_role = user_role;
	}	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_login() {
		return user_login;
	}
	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getUser_role() {
		return user_role;
	}
	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_login=" + user_login + ", user_name=" + user_name
				+ ", user_password=" + user_password + ", user_email=" + user_email + ", user_role=" + user_role + "]";
	}

}
