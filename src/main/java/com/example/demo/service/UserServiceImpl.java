package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.dto.UserRegisterRespDto;
import com.example.demo.entity.Login;
import com.example.demo.entity.User;
import com.example.demo.exception.PasswordMismatchException;
import com.example.demo.exception.UserFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.ILoginRepository;
import com.example.demo.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {
	private static Logger logger = LogManager.getLogger();

	@Autowired
	IUserRepository userRepo;

	@Autowired
	ILoginRepository loginRepo;

	@Override
	public List<UserDto> getAllUsers() {
		List<User> userList = userRepo.findAll();
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		for(User n:userList) {
		UserDto userDto = new UserDto();
		userDto.setAddress(n.getAddress());
		userDto.setEmail(n.getUserEmail());
		userDto.setUsername(n.getUsername());
		userDto.setUserId(n.getUserId());
		userDtoList.add(userDto);
		
		
		}
		return userDtoList;

	}

	@Override
	public UserDto getUserById(int userId) throws UserNotFoundException {
		logger.info("Getting the optional object of type user based on the userId from the database");
		Optional<User> user = userRepo.findById(userId);
		logger.info("Checking for the presence of user object ");
		if (user.isPresent()) {
			logger.info("User Object returned successfully");
			User nuser = user.get();
			UserDto userdto = new UserDto();
			userdto.setAddress(nuser.getAddress());
			userdto.setEmail(nuser.getUserEmail());
			userdto.setUsername(nuser.getUsername());
			userdto.setUserId(nuser.getUserId());
			return userdto;
		} else {
			throw new UserNotFoundException("User not found with user id " + userId);
		}

	}

	@Override
	public User addUser(User user) {
		logger.info("Adding the user to the database");
		return userRepo.save(user);
	}

	@Override
	public UserDto deleteUser(int userId) throws UserNotFoundException {
		logger.info("Finding the optional object using userId from the database");
		Optional<User> user = userRepo.findById(userId);
		logger.info("checking for the presence of user object");
		if (user.isPresent()) {
			logger.info("User has been deleted");
			userRepo.deleteById(userId);
		} else {
			throw new UserNotFoundException("User not found with user id " + userId);
		}
		logger.info("Returning deleted user object");
		User deletedUser = user.get();
		UserDto nUserDto = new UserDto();
		nUserDto.setAddress(deletedUser.getAddress());
		nUserDto.setEmail(deletedUser.getUserEmail());
		nUserDto.setUserId(deletedUser.getUserId());
		nUserDto.setUsername(deletedUser.getUsername());
		return nUserDto;
	}

	@Override
	public UserDto updateUserById(int userId, UserDto nUser) throws UserNotFoundException {
		logger.info("Finding the optional object using userId from the database");
		Optional<User> user = userRepo.findById(userId);
		logger.info("checking for the presence of user object");
		if (user.isPresent()) {
			logger.info("Saving the updated details of the user");
			User newUser = user.get();
			//Login updateLogin = loginRepo.;
			//updateLogin.setEmail(nUser.getEmail());
			
			
			newUser.setAddress(nUser.getAddress());
			newUser.setUserId(nUser.getUserId());
			newUser.setUserEmail(nUser.getEmail());
			newUser.setUsername(nUser.getUsername());
			userRepo.save(newUser);
			UserDto userdto = new UserDto();
			userdto.setAddress(newUser.getAddress());
			userdto.setEmail(newUser.getUserEmail());
			userdto.setUsername(newUser.getUsername());
			userdto.setUserId(newUser.getUserId());
			return userdto;
		} else {
			throw new UserNotFoundException("User not found with user id " + userId);
		}
	}

	@Override
	public UserRegisterRespDto userRegistration(UserRegisterDto regDto)
			throws UserFoundException, PasswordMismatchException {
		logger.info("checking for the presence of Login object");
		Optional<Login> loginOpt = loginRepo.findById(regDto.getEmail());
		logger.info("checking for the presence of user object with the given name");
		User userOpt = userRepo.findByName(regDto.getName());
		if (loginOpt.isPresent()) {
			throw new UserFoundException(
					"Given email address " + regDto.getEmail() + " present already! Choose different one");
		} else if (userOpt != null) {
			throw new UserFoundException(
					"Given user name " + regDto.getName() + " present already! Choose different one");
		}

		// Converting UserRegisterDto to User object
		// Create User object
		User user = new User();

		// Update User object details
		user.setUsername(regDto.getName());
		user.setAddress(regDto.getAddress());
		user.setUserEmail(regDto.getEmail());
		user.setPassword(regDto.getPassword());
		user.setConfirmPassword(regDto.getConfirmPassword());

		// Creating new login object and setting up the details
		if (user.getPassword().equals(user.getConfirmPassword())) {
			Login login = new Login();
			login.setEmail(regDto.getEmail());
			login.setPassword(regDto.getPassword());
			// Updating the LoginStatus as LoggedOut
			login.setLoginStatus("LoggedOut");

			user.setLogin(login);

			// Saving User Object in database
			User newUser = userRepo.save(user);

			// converting the User object to RegRespDto object

			UserRegisterRespDto resDto = new UserRegisterRespDto();
			resDto.setName(newUser.getUsername());
			resDto.setEmail(newUser.getLogin().getEmail());
			resDto.setLoginStatus(newUser.getLogin().getLoginStatus());

			// returning the dto object
			return resDto;
		} else {
			throw new PasswordMismatchException("Entered Password is not matching with comfirmpassword");
		}
	}

	@Override
	public UserDto updateUserPassword(int userId, String newPassword) throws UserNotFoundException {
		logger.info("Getting the optional object using userId provided");
		Optional<User> user = userRepo.findById(userId);
		logger.info("checking for the presence of user object in the container");
		if (user.isPresent()) {
			User dbUser = user.get();
			Login updateLogin = loginRepo.findById(dbUser.getUserEmail()).get();
			updateLogin.setPassword(newPassword);
			logger.info("updating and saving the updated password in the database");
			dbUser.setPassword(newPassword);
			userRepo.save(dbUser);
			UserDto userDto = new UserDto();
			userDto.setUserId(dbUser.getUserId());
			userDto.setAddress(dbUser.getAddress());
			userDto.setEmail(dbUser.getUserEmail());
			userDto.setUsername(dbUser.getUsername());
			return userDto;
		} else {
			// if User not found in the database then it will throw exception.
			throw new UserNotFoundException("User not found with user id " + userId);

		}

	}

	@Override
	public UserDto getUserByName(String name) throws UserNotFoundException {
		User user = userRepo.findByName(name);
		if(user!=null) {
			UserDto userdto = new UserDto();
			userdto.setAddress(user.getAddress());
			userdto.setEmail(user.getUserEmail());
			userdto.setUsername(user.getUsername());
			userdto.setUserId(user.getUserId());
			return userdto;
		}
		else {
			throw new UserNotFoundException("User not found with user name " + name);

		}
	}

	@Override
	public UserDto getUserByEmail(String email) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmailAddress(email);
		if(user!=null) {
			UserDto userdto = new UserDto();
			userdto.setAddress(user.getAddress());
			userdto.setEmail(user.getUserEmail());
			userdto.setUsername(user.getUsername());
			userdto.setUserId(user.getUserId());
			return userdto;
		}
		else {
			throw new UserNotFoundException("User not found with user name " + email);

		}
	}
	
	

}
