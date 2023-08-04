package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.dto.UserRegisterRespDto;
import com.example.demo.entity.User;
import com.example.demo.exception.PasswordMismatchException;
import com.example.demo.exception.UserFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserServiceImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
	@Autowired
	UserServiceImpl userService;

	@Test
	@Order(1)
	void userRegistrationTest() throws UserFoundException, PasswordMismatchException {
		UserRegisterDto regDto  = new UserRegisterDto();
		regDto.setName("User1");
		regDto.setEmail("User1@gmail.com");
		regDto.setAddress("User1address");
		regDto.setPassword("user1@password");
		regDto.setConfirmPassword("user1@password");
		UserRegisterRespDto respDto = userService.userRegistration(regDto);
		assertEquals("User1",respDto.getName());
		assertEquals("User1@gmail.com",respDto.getEmail());

	}
	
	
	@Test
	@Order(2)
	void getAllUsersTest() {
		UserRegisterDto regDto  = new UserRegisterDto();
		List<UserDto> usersList = userService.getAllUsers();
		assertEquals(1,usersList.size());
		assertEquals("User1",usersList.get(0).getUsername());

		
	}
	{/*@Test
	@Order(3)
	void updateUserById() throws UserNotFoundException {
		User nuser  = new User();
		nuser.setAddress("User1@address");
		nuser.setUsername("newUser1");
		nuser.setConfirmPassword("user1@password");
		nuser.se
		UserDto user = userService.updateUserById(1, nuser);
		assertEquals("User1@address",user.getAddress());
		assertEquals("newUser1",user.getUsername());
	
	*/}
	@Test
	@Order(3)
	void getUserById() throws UserNotFoundException{
		UserDto user = userService.getUserById(1);
		assertEquals("User1address",user.getAddress());
		assertEquals("User1",user.getUsername());
		assertEquals("User1@gmail.com",user.getEmail());
	}
	@Test
	@Order(4)
	void updateUserPassword() throws UserNotFoundException{
		UserDto user = userService.updateUserPassword(1, "user1@newpass");
		assertEquals("User1address",user.getAddress());
		assertEquals("User1",user.getUsername());
		assertEquals("User1@gmail.com",user.getEmail());
		assertEquals(1,user.getUserId());
	}
	@Test
	@Order(5)
	void getUserByName() throws UserNotFoundException{
		UserDto user = userService.getUserByName("User1");
		assertEquals("User1address",user.getAddress());
		assertEquals("User1",user.getUsername());
		assertEquals("User1@gmail.com",user.getEmail());
		assertEquals(1,user.getUserId());
	}
	@Test
	@Order(6)
	void DeleteUser() throws UserNotFoundException{
		UserDto user = userService.deleteUser(1);
		assertEquals("User1address",user.getAddress());
		assertEquals("User1",user.getUsername());
		assertEquals("User1@gmail.com",user.getEmail());
	}
	
}
