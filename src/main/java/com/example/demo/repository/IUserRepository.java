package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	@Query(value = "SELECT * FROM USER  WHERE username = ?1", nativeQuery = true)
	User findByName(String name);
	@Query(value = "SELECT * FROM USER  WHERE user_email = ?1", nativeQuery = true)
	User findByEmailAddress(String email);

}
