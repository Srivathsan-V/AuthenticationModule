package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserLoginDto {
	@Email(message = "Enter valid email address")
	private String email;
	@Size(max = 15, min = 6, message = "Password should be minimum 6 characters and maximum 15 characters")
	//@Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,15}$",
            //message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String password;

	public UserLoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

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

}
