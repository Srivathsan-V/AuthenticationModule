package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "login")
public class Login {
	@Id
	@Column(length = 50)
	@Email(message = "Enter valid email address")
	private String email;
	@Size(max = 30, min = 3, message = "Password should be minimum 3 characters and maximum 30 characters")
	//@Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,15}$",
            //message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String password;
	@Column(length = 50)
	private String loginStatus = "LoggedOut";

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(@Email(message = "Enter valid email address") String email, String password, String loginStatus) {
		super();
		this.email = email;
		this.password = password;
		this.loginStatus = loginStatus;
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

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

}
