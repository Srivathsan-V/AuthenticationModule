package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterDto {
	@NotNull(message = "Name shouldn't be empty")
	private String name;
	@Email(message = "Enter valid email address")
	// @NotNull(message="Email shouldn't be empty")
	private String email;
	// @NotNull(message="Password shouldn't be empty")
	// @Min(value = 6,message = "password should be minimum 6 characters long")
	// @Max(value = 15,message = "password should be maximum 15 characters long")
	@Size(max = 15, min = 6, message = "Password should be minimum 6 characters and maximum 15 characters")
	//@Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,15}$",
            //message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String password;
	@Size(max = 15, min = 6, message = "Password should be minimum 6 characters and maximum 15 characters")
	//@Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,15}$",
            //message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@NotNull(message = "address shouldn't be empty")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserRegisterDto(String name, String email, String password, String address) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
	}

	public UserRegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
