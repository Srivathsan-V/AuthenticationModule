package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.dto.UserRegisterRespDto;
import com.example.demo.entity.User;
import com.example.demo.exception.PasswordMismatchException;
import com.example.demo.exception.UserFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	IUserService userServ;

	@GetMapping("/Allusers")
	ResponseEntity<List<UserDto>> getAllUsers() {
		logger.info("Getting Request to get all the user details");
		List<UserDto> users = userServ.getAllUsers();
		logger.info("User details have been returned for your reference");
		return new ResponseEntity<>(users, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	ResponseEntity<UserDto> getUserById(@PathVariable("id") int userId) throws UserNotFoundException {
		logger.info("Getting Request to get the user details with respect to the id provided");
		UserDto user = userServ.getUserById(userId);
		logger.info("Request has been received appropriate action performed and returned the user object");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/addUser")
	ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		logger.info("Request has been received to add the user into database");
		User newUser = userServ.addUser(user);
		logger.info("user has been added into database");
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@PutMapping("/update{id}")
	ResponseEntity<UserDto> updateUserById(@Valid @RequestBody UserDto user, @PathVariable("id") int userId)
			throws UserNotFoundException {
		logger.info("Getting request to update the user details based on the id provided");
		UserDto updated = userServ.updateUserById(userId, user);
		logger.info("User details are updated");
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping("/delete{id}")
	ResponseEntity<UserDto> deleteUser(@PathVariable("id") int userId) throws UserNotFoundException {
		logger.info("Request has been received to delete the user based on userId");
		UserDto user = userServ.deleteUser(userId);
		logger.info("User details have been deleted");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/userRegistration")
	ResponseEntity<UserRegisterRespDto> userRegistration(@Valid @RequestBody UserRegisterDto user)
			throws UserFoundException, PasswordMismatchException {
		logger.info("Request has been received to perform user registration process");
		UserRegisterRespDto newUser = userServ.userRegistration(user);
		logger.info("Successfully completed the user registration");
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@PatchMapping("/updatepassword{id}")
	ResponseEntity<UserDto> updateUserPassword(@PathVariable("id") int userId, @Valid @RequestBody String newPassword)
			throws UserNotFoundException {
		logger.info("Request has been received to update the user password based on userId");
		UserDto updatedUser = userServ.updateUserPassword(userId, newPassword);
		logger.info("Updated the password");
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	@PatchMapping("/getuserbyname/{name}")
	ResponseEntity<UserDto> getUserByName(@PathVariable("name") String userName)
			throws UserNotFoundException {
		logger.info("Request has been received to get user based on userName");
		UserDto userDto = userServ.getUserByName(userName);
		logger.info("Returned the User");
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	@PatchMapping("/getuserbyemail/{email}")
	ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email)
			throws UserNotFoundException {
		logger.info("Request has been received to get user based on email");
		UserDto userDto = userServ.getUserByEmail(email);
		logger.info("Returned the User");
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

}
