package com.labutin.barman.controller;

public enum UserType {
	GUEST("Guest"), USER("User"), BARMAN("Barman"), ADMIN("Admin");
	private String value;

	private UserType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
