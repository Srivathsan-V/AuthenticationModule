package com.example.demo.service;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserLoginRespDto;
import com.example.demo.entity.Login;
import com.example.demo.exception.EmailNotFoundException;
import com.example.demo.exception.InvalidCredentialsException;

public interface ILoginService {
	// Login login(Login credentials) throws InvalidCredentialsException ;

	UserLoginRespDto login(UserLoginDto loginDto) throws InvalidCredentialsException;

	UserLoginRespDto logout(String email) throws EmailNotFoundException;

}
