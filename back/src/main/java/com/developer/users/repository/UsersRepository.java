package com.developer.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.users.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
	//TUTOR + USERS 동시 출력 
	@EntityGraph(attributePaths = "tutor")
	public Optional<Users> findByUserId(String userId);
	
	
	@Query(value="SELECT *"
			+ "	FROM users"
			+ "	WHERE"
			+ "	user_id= :userId and pwd= :pwd", nativeQuery = true)
	public Users usersLogin(@Param("userId")String userId, @Param("pwd") String pwd);
}
