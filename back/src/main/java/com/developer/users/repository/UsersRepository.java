package com.developer.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.users.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
	//TUTOR + USERS 동시 출력 
	@EntityGraph(attributePaths = "tutor")
	public Optional<Users> findByUserId(String userId);
	
	//TUTOR + USERS 동시 출력 
	@EntityGraph(attributePaths = "tutor")
	public Optional<Users> findByUserId(String userId);
 
}
