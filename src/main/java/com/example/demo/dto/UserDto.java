package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDto {
	private int userId;
	private String username;
	private String address;
	private String email;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserDto(int userId, String username, String address, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.address = address;
		this.email = email;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
