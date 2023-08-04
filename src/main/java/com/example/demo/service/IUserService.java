package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.dto.UserRegisterRespDto;
import com.example.demo.entity.User;
import com.example.demo.exception.PasswordMismatchException;
import com.example.demo.exception.UserFoundException;
import com.example.demo.exception.UserNotFoundException;

public interface IUserService {
	List<UserDto> getAllUsers();

	UserDto getUserById(int userId) throws UserNotFoundException;

	User addUser(User user);

	UserDto updateUserById(int userId, @Valid UserDto user) throws UserNotFoundException;

	UserDto deleteUser(int userId) throws UserNotFoundException;

	UserRegisterRespDto userRegistration(UserRegisterDto emp) throws UserFoundException, PasswordMismatchException;

	UserDto updateUserPassword(int userId, String newPassword) throws UserNotFoundException;
	
	UserDto getUserByName(String name) throws UserNotFoundException;
	
	UserDto getUserByEmail(String email) throws UserNotFoundException;
	

}
