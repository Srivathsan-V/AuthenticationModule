package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserLoginRespDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.dto.UserRegisterRespDto;
import com.example.demo.exception.EmailNotFoundException;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.exception.PasswordMismatchException;
import com.example.demo.exception.UserFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.LoginServiceImpl;
import com.example.demo.service.UserServiceImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginServiceTest {
	@Autowired
	LoginServiceImpl loginService;
	
	@Autowired
	UserServiceImpl userService;
	
	@Test
	@Order(1)
	void userRegistrationTest() throws UserFoundException, PasswordMismatchException {
		UserRegisterDto regDto  = new UserRegisterDto();
		regDto.setName("user1");
		regDto.setEmail("user1@gmail.com");
		regDto.setAddress("user1@address");
		regDto.setPassword("user1@pass");
		regDto.setConfirmPassword("user1@pass");
		UserRegisterRespDto respDto = userService.userRegistration(regDto);
		assertEquals("user1",respDto.getName());
		assertEquals("user1@gmail.com",respDto.getEmail());

	}
	
	
	@Test
	@Order(2)
	void login() throws InvalidCredentialsException{
		UserLoginDto loginDto = new UserLoginDto();
		loginDto.setEmail("user1@gmail.com");
		loginDto.setPassword("user1@pass");
		UserLoginRespDto loginResp = loginService.login(loginDto);
		assertEquals("user1@gmail.com",loginResp.getEmail());
		assertEquals("LoggedIn",loginResp.getLoginStatus());
		
	}
	@Test
	@Order(3)
	void logout() throws EmailNotFoundException {
		UserLoginDto loginDto = new UserLoginDto();
		String email = "user1@gmail.com";
		UserLoginRespDto loginResp = loginService.logout(email);
		assertEquals("user1@gmail.com",loginResp.getEmail());
		assertEquals("LoggedOut",loginResp.getLoginStatus());
		
	}
	
	

}
