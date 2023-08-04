package com.example.demo.dto;

public class UserRegisterRespDto {
	private String name;
	private String loginStatus;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginstatus) {
		this.loginStatus = loginstatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRegisterRespDto(String name, String loginStatus, String email) {
		super();
		this.name = name;
		this.loginStatus = loginStatus;
		this.email = email;
	}

	public UserRegisterRespDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
