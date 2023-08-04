package com.example.demo.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserLoginRespDto;
import com.example.demo.entity.Login;
import com.example.demo.exception.EmailNotFoundException;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.ILoginService;

@RestController
@CrossOrigin
public class LoginController {
	private static Logger logger = LogManager.getLogger();

	@Autowired
	ILoginService loginServ;

	/*
	 * /@PostMapping("/login") ResponseEntity<Login> login(@RequestBody Login
	 * credentials) throws InvalidCredentialsException { Login login=
	 * loginServ.login(credentials); return new ResponseEntity<>(login,
	 * HttpStatus.OK); }
	 */

	@PostMapping("/login/dto")
	ResponseEntity<UserLoginRespDto> login(@Valid @RequestBody UserLoginDto loginDto)
			throws InvalidCredentialsException {
		logger.info("Request has been received for logging in the user");
		UserLoginRespDto login = loginServ.login(loginDto);
		logger.info("LoggedIn Successfully");
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

	@PatchMapping("/logout/{email}")
	ResponseEntity<UserLoginRespDto> logout(@PathVariable String email)
			throws UserNotFoundException, EmailNotFoundException {
		logger.info("Request has been received for logout");
		UserLoginRespDto resp = loginServ.logout(email);
		logger.info("LoggedOut Successfully");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
