package com.example.demo.dto;

public class UserLoginRespDto {
	private String email;
	private String loginStatus;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public UserLoginRespDto(String email, String loginStatus) {
		super();
		this.email = email;
		this.loginStatus = loginStatus;
	}

	public UserLoginRespDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
