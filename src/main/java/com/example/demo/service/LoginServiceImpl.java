package com.example.demo.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserLoginRespDto;
import com.example.demo.entity.Login;
import com.example.demo.exception.EmailNotFoundException;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.repository.ILoginRepository;

@Service
public class LoginServiceImpl implements ILoginService {
	private static Logger logger = LogManager.getLogger();

	@Autowired
	ILoginRepository loginRepo;

	/*
	 * @Override public Login login(Login credentials) throws
	 * InvalidCredentialsException { Optional<Login> loginCred =
	 * loginRepo.findById(credentials.getEmail());
	 * 
	 * if (loginCred.isPresent()) { // compare db password with user provided
	 * password // if password matching return credentials else throw exception
	 * Login loginData = loginCred.get(); if
	 * (loginData.getPassword().equals(credentials.getPassword())) {
	 * loginData.setLoginStatus(true); return loginRepo.save(loginData);
	 * 
	 * } else { throw new InvalidCredentialsException("Invalid credentials!"); } }
	 * else { // throw exception if given email not present in the db. throw new
	 * InvalidCredentialsException("User not found with email: "+credentials.
	 * getEmail()); }
	 * 
	 * }
	 */

	@Override
	public UserLoginRespDto login(UserLoginDto loginDto) throws InvalidCredentialsException {
		logger.info("Getting the Optional object of type login from the database");
		Optional<Login> dbLoginOpt = loginRepo.findById(loginDto.getEmail());
		logger.info("checking for the login object in the optional object");
		if (dbLoginOpt.isPresent()) {
			// Comparing the password in the database with user entered password.
			// If password matches, the user will be loggedin successfully otherwise it will
			// throw exception.
			Login login = dbLoginOpt.get();
			if (login.getPassword().equals(loginDto.getPassword())) {

				// if credentials matches, set loginstatus as LoggedIn and save it in the
				// database.
				login.setLoginStatus("LoggedIn");
				Login updatedLogin = loginRepo.save(login);

				// converting UserLogin entity to UserLoginRespDto
				UserLoginRespDto resDto = new UserLoginRespDto();
				resDto.setEmail(login.getEmail());
				resDto.setLoginStatus("LoggedIn");
				logger.info("User has been LoggedIn");

				return resDto;

			} else {
				throw new InvalidCredentialsException("Invalid credentials!");
			}
		} else {
			// throw exception if given emailId is not present in the database.
			throw new InvalidCredentialsException("User not found with email: " + loginDto.getEmail());
		}
	}

	@Override
	public UserLoginRespDto logout(String email) throws EmailNotFoundException {
		logger.info("Getting the optional object of type loginfrom the database.");
		Optional<Login> dbLoginOpt = loginRepo.findById(email);
		logger.info("checking for the login object in the optional object");
		if (dbLoginOpt.isPresent()) {
			// Getting the login object from the optional object
			Login login = dbLoginOpt.get();

			// Updating the LoginStatus as false and save in the database
			login.setLoginStatus("LoggedOut");
			Login updatedLogin = loginRepo.save(login);

			// Converting UserLogin object to UserLoginRespDto
			UserLoginRespDto resDto = new UserLoginRespDto();
			resDto.setEmail(login.getEmail());
			resDto.setLoginStatus("LoggedOut");
			logger.info("LoggedOut Successfully");
			// returning UserLoginRespDto object
			return resDto;
		} else {
			throw new EmailNotFoundException("User not found with email: " + email);
		}

	}

}
